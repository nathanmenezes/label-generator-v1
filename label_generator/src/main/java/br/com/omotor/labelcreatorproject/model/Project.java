package br.com.omotor.labelcreatorproject.model;

import br.com.omotor.labelcreatorproject.model.dto.ProjectDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String devUrl;
    private String prodUrl;
    private String dataBaseName;

    public void editProject(ProjectDto projectDto){
        if(projectDto.getName() != null){
            this.name = projectDto.getName();
        }
        if(projectDto.getDevUrl() != null){
            this.devUrl = projectDto.getDevUrl();
        }
        if(projectDto.getProdUrl() != null){
            this.prodUrl = projectDto.getProdUrl();
        }
        if(projectDto.getDataBaseName() != null){
            this.dataBaseName = projectDto.getDataBaseName();
        }
    }
}
