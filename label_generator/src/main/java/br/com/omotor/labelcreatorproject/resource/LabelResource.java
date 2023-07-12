package br.com.omotor.labelcreatorproject.resource;

import br.com.omotor.labelcreatorproject.model.*;
import br.com.omotor.labelcreatorproject.model.dto.*;
import br.com.omotor.labelcreatorproject.service.LabelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
@CrossOrigin("*")
public class LabelResource {

    @Autowired
    private LabelService service;

    @PostMapping
    public ResponseEntity<ReturnMessage> createLabel(@RequestBody @Valid Quotes quotesList) {
        return service.createLabel(quotesList);
    }

    @GetMapping
    public ResponseEntity<List<SystemTranslateDto>> findAllLabels() {
        return service.findAllLabels();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReturnMessage> deleteLabel(@PathVariable Long id) {
        return service.deleteLabel(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOneLabel(@PathVariable Long id) {
        return service.findOneLabel(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnMessage> editLabel(@PathVariable Long id, @RequestBody LabelDto labelDto) {
        return service.editLabel(id, labelDto);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchLabel(@RequestParam String value) {
        return service.searchLabel(value);
    }

    @PostMapping("/replace")
    public ResponseEntity<?> replaceLabel(@RequestBody Html html){
        return service.replaceLabel(html);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<SystemTranslateDto>> searchLabelProject(@PathVariable Long id){
        return service.searchLabelProject(id);
    }

    @GetMapping("/sql/{id}")
    public ResponseEntity<ReturnMessage> generateSql(@PathVariable(value = "id") Long projectId, @RequestParam Integer systemLocaleId){
        return service.generateSql(projectId, systemLocaleId);
    }
}
