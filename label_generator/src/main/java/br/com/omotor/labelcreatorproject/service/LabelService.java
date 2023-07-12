package br.com.omotor.labelcreatorproject.service;

import br.com.omotor.labelcreatorproject.feign.LabelClient;
import br.com.omotor.labelcreatorproject.model.*;
import br.com.omotor.labelcreatorproject.model.dto.*;
import br.com.omotor.labelcreatorproject.repository.ProjectRepository;
import br.com.omotor.labelcreatorproject.repository.SystemTranslateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LabelService {

    @Autowired
    private SystemTranslateRepository repository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    LabelClient labelClient;

    @Transactional
    public ResponseEntity<ReturnMessage> createLabel(Quotes quotesList) {
        RestTemplate template = new RestTemplate();
        List<SystemTranslate> approvedLabels = new ArrayList<>();
        List<SystemTranslate> reprovedLabels = new ArrayList<>();
        Project project = projectRepository.findById(quotesList.getIdProject()).get();
        HashMap<String, String> labels = labelClient.fetchLabels(URI.create(project.getDevUrl()));
        quotesList.getQuotes().forEach(quote -> {
            quote = quote.trim();
            String url = "https://api.mymemory.translated.net/get?q=" + quote + "&langpair=pt|en";
            Matches matches = template.getForObject(url, Matches.class);
            assert matches != null;
            String translation = matches.getMatches().get(0).getTranslation();
            String labelNick = "label_" + translation.replace(" ", "_").toLowerCase();
            SystemTranslate systemTranslatePt = new SystemTranslate(labelNick, quote, 1, project);
            SystemTranslate systemTranslateEn = new SystemTranslate(labelNick, translation, 2, project);
            if (repository.existsByValueAndKeyLabel(systemTranslatePt.getValue(), systemTranslatePt.getKeyLabel())) {
                reprovedLabels.add(systemTranslatePt);
            } else if (labels.containsKey(systemTranslatePt.getKeyLabel()) && labels.containsValue(systemTranslatePt.getValue())) {
                reprovedLabels.add(systemTranslatePt);
            }
            if (repository.existsByValueAndKeyLabel(systemTranslateEn.getValue(), systemTranslateEn.getKeyLabel())) {
                reprovedLabels.add(systemTranslateEn);
            } else if (labels.containsKey(systemTranslateEn.getKeyLabel()) && labels.containsValue(systemTranslateEn.getValue())) {
                reprovedLabels.add(systemTranslateEn);
            } else {
                repository.save(systemTranslatePt);
                repository.save(systemTranslateEn);
                approvedLabels.add(systemTranslatePt);
                approvedLabels.add(systemTranslateEn);
            }
        });
        return ResponseEntity.status(200).body(new ReturnMessage("Labels cadastrada com sucesso!", new LabelResults(approvedLabels, reprovedLabels)));
    }

    public ResponseEntity<List<SystemTranslateDto>> findAllLabels() {
        return ResponseEntity.status(200).body(repository.findAll().stream().map(SystemTranslateDto::new).toList());
    }

    public ResponseEntity<ReturnMessage> deleteLabel(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).body(new ReturnMessage("Label Não Existe no Sistema!", null));
        }
        repository.deleteById(id);
        return ResponseEntity.status(200).body(new ReturnMessage("Label Deletada com sucesso!", id));
    }

    public ResponseEntity<?> findOneLabel(Long id) {
        return ResponseEntity.status(200).body(repository.findById(id).get());
    }

    public ResponseEntity<ReturnMessage> editLabel(Long id, LabelDto labelDto) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).body(new ReturnMessage("Label Não Existe no Sistema!", null));
        }
        SystemTranslate label = repository.findById(id).get();
        label.edit(labelDto);
        repository.save(label);

        return ResponseEntity.status(200).body(new ReturnMessage("Label Alterada com Sucesso!", label));
    }

    public ResponseEntity<?> searchLabel(String value) {
        return ResponseEntity.status(200).body(repository.findByValueContainingOrKeyLabelContaining(value, value));
    }

    public ResponseEntity<?> replaceLabel(Html html) {
        List<SystemTranslate> labels = repository.findAll();
        List<String> replacedLabels = new ArrayList<>();

        labels.forEach(label -> {
            CharSequence charSequence = label.getValue();
            if (html.getHtml().contains(charSequence)) {
                html.setHtml(html.getHtml().replace(label.getValue(), "{{" + "'" + label.getKeyLabel() + "'" + " | translate}}"));
                replacedLabels.add(label.getValue());
            }
        });

        return ResponseEntity.status(200).body(html);
    }

    public ResponseEntity<List<SystemTranslateDto>> searchLabelProject(Long id) {
        return ResponseEntity.status(200).body(repository.findAllByProjectId(id).stream().map(SystemTranslateDto::new).toList());
    }

    public ResponseEntity<ReturnMessage> generateSql(Long projectId, Integer systemLocaleId) {
        Project project = projectRepository.findById(projectId).get();
        HashMap<String, String> labels = labelClient.fetchLabels(URI.create(project.getDevUrl()));

        List<SystemTranslate> labelList = repository.findAllByProjectIdAndSystemLocaleId(projectId, systemLocaleId);

        StringBuilder sqlCommand = new StringBuilder("INSERT INTO `" + project.getDataBaseName() + "`.`system_translate` (`created_at`, `key`, `value`, `system_locale_id`) VALUES \n");

        for (SystemTranslate label: labelList) {
            if (!labels.containsKey(label.getKeyLabel()) && !labels.containsValue(label.getValue())) {
                sqlCommand.append("(now(), ").append("'").append(label.getKeyLabel()).append("'").append(", ").append("'").append(label.getValue()).append("'").append(", '").append(label.getSystemLocaleId()).append("'),\n");
            }
        }
        sqlCommand.deleteCharAt(sqlCommand.lastIndexOf(","));
        sqlCommand.deleteCharAt(sqlCommand.lastIndexOf("\n"));
        sqlCommand.append(";");
        return ResponseEntity.status(200).body(new ReturnMessage("SQL Gerado com Sucesso!", sqlCommand));
    }
}
