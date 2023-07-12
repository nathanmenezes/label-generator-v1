package br.com.omotor.labelcreatorproject.model.dto;

import br.com.omotor.labelcreatorproject.model.Project;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String devUrl;
    private String prodUrl;
    private String dataBaseName;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.devUrl = project.getDevUrl();
        this.prodUrl = project.getProdUrl();
        this.dataBaseName = project.getDataBaseName();
    }
}
