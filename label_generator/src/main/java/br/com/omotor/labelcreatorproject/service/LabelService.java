package br.com.omotor.labelcreatorproject.service;

import br.com.omotor.labelcreatorproject.model.*;
import br.com.omotor.labelcreatorproject.model.dto.*;
import br.com.omotor.labelcreatorproject.repository.SystemTranslateRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class LabelService {

    @Autowired
    private SystemTranslateRepository repository;

    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Transactional
    public ResponseEntity<ReturnMessage> createLabel(Quotes quotesList) {
        RestTemplate template = new RestTemplate();
        List<SystemTranslate> approvedLabels = new ArrayList<>();
        List<SystemTranslate> reprovedLabels = new ArrayList<>();
        quotesList.getQuotes().forEach(quote ->{
            quote = quote.trim();
            String url = "https://api.mymemory.translated.net/get?q="+quote+"&langpair=pt|en";
            Matches matches = template.getForObject(url, Matches.class);
            assert matches != null;
            String translation = matches.getMatches().get(0).getTranslation();
            String labelNick = "label_" + translation.replace(" ", "_").toLowerCase();
            String typescript = "{{"+ "'" + labelNick + "'" + " | translate}}";
            SystemTranslate systemTranslate = new SystemTranslate(typescript, quote);
            if(repository.existsByValueAndKeyLabel(systemTranslate.getValue(), systemTranslate.getKeyLabel())){
               reprovedLabels.add(systemTranslate);
            }else{
                approvedLabels.add(systemTranslate);
            }
        });
        repository.saveAll(approvedLabels);
        return ResponseEntity.status(200).body(new ReturnMessage("Labels cadastrada com sucesso!", new LabelResults(approvedLabels, reprovedLabels)));
    }

    public ResponseEntity<List<SystemTranslate>> findAllLabels() {
        return ResponseEntity.status(200).body(repository.findAll());
    }

    public ResponseEntity<ReturnMessage> deleteLabel(Long id) {
        if(!repository.existsById(id)){
            return ResponseEntity.status(404).body(new ReturnMessage("Label Não Existe no Sistema!", null));
        }
        repository.deleteById(id);
        return ResponseEntity.status(200).body(new ReturnMessage("Label Deletada com sucesso!", id));
    }

    public ResponseEntity<?> findOneLabel(Long id) {
        return ResponseEntity.status(200).body(repository.findById(id).get());
    }

    public ResponseEntity<ReturnMessage> editLabel(Long id, LabelDto labelDto) {
        if(!repository.existsById(id)){
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

        labels.forEach(label ->{
            CharSequence charSequence = label.getValue();
            if(html.getHtml().contains(charSequence)){
                html.setHtml(html.getHtml().replace(label.getValue(), label.getKeyLabel()));
                replacedLabels.add(label.getValue());
            }
        });

        return ResponseEntity.status(200).body(html);
    }
}
