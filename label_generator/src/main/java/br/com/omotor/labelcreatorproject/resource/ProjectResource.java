package br.com.omotor.labelcreatorproject.resource;

import br.com.omotor.labelcreatorproject.model.dto.ProjectDto;
import br.com.omotor.labelcreatorproject.model.dto.ReturnMessage;
import br.com.omotor.labelcreatorproject.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectResource {
    
    @Autowired
    private ProjectService service;
    
    @PostMapping
    public ResponseEntity<ReturnMessage> saveProject(@RequestBody @Valid ProjectDto projectDto){
        return service.saveProject(projectDto);
    }
    
}
