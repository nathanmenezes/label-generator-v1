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
}
