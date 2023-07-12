package br.com.omotor.labelcreatorproject.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    @NotBlank
    private String name;
    @NotBlank
    private String devUrl;
    private String prodUrl;
}
