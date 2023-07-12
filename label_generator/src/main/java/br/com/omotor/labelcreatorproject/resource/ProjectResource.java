package br.com.omotor.labelcreatorproject.resource;

import br.com.omotor.labelcreatorproject.model.Project;
import br.com.omotor.labelcreatorproject.model.dto.ProjectDto;
import br.com.omotor.labelcreatorproject.model.dto.ReturnMessage;
import br.com.omotor.labelcreatorproject.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@CrossOrigin("*")
public class ProjectResource {

    @Autowired
    private ProjectService service;

    @PostMapping
    public ResponseEntity<ReturnMessage> saveProject(@RequestBody @Valid ProjectDto projectDto){
        return service.saveProject(projectDto);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> listProjects(){
        return service.listProjects();
    }

    @DeleteMapping
    public ResponseEntity<ReturnMessage> deleteProject(@RequestBody ProjectDto projectDto){
        return service.deleteProject(projectDto);
    }

    @PutMapping
    public ResponseEntity<ReturnMessage> editProject(@RequestBody ProjectDto projectDto){
        return service.editProject(projectDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectDto> listProjectById(@PathVariable Long id){
        return service.listProjectById(id);
    }
}
