package br.com.omotor.labelcreatorproject.service;

import br.com.omotor.labelcreatorproject.model.Project;
import br.com.omotor.labelcreatorproject.model.dto.ProjectDto;
import br.com.omotor.labelcreatorproject.model.dto.ReturnMessage;
import br.com.omotor.labelcreatorproject.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;


    public ResponseEntity<ReturnMessage> saveProject(@Valid ProjectDto projectDto) {
        if(repository.existsByName(projectDto.getName())){
            return ResponseEntity.status(400).body(new ReturnMessage("Projeto já cadastrado no sistema!!", projectDto));
        }

        Project project = new Project();
        BeanUtils.copyProperties(projectDto, project);
        repository.save(project);
        return ResponseEntity.status(201).body(new ReturnMessage("Projeto Cadastrado com Sucesso!", project));
    }

    public ResponseEntity<List<ProjectDto>> listProjects() {
        return ResponseEntity.status(200).body(repository.findAll().stream().map(ProjectDto :: new).toList());
    }

    public ResponseEntity<ReturnMessage> deleteProject(ProjectDto projectDto) {
        repository.deleteById(projectDto.getId());
        return ResponseEntity.status(200).body(new ReturnMessage("Projeto deletado com sucesso!", projectDto));
    }

    public ResponseEntity<ReturnMessage> editProject(ProjectDto projectDto) {
        Project project = repository.findById(projectDto.getId()).get();
        project.editProject(projectDto);
        repository.save(project);
        BeanUtils.copyProperties(project, projectDto);
        return ResponseEntity.status(200).body(new ReturnMessage("Projeto Alterado com Sucesso!", projectDto));
    }

    public ResponseEntity<ProjectDto> listProjectById(Long id) {
        ProjectDto dto = new ProjectDto();
        BeanUtils.copyProperties(repository.findById(id).get(), dto);
        return ResponseEntity.status(200).body(dto);
    }
}
