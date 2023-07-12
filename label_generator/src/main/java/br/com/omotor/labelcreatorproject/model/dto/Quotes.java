package br.com.omotor.labelcreatorproject.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quotes {
    @NotNull
    private List<String> quotes;
    
    @NotNull
    private Long idProject;
}
