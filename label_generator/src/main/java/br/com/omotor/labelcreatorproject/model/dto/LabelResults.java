package br.com.omotor.labelcreatorproject.model.dto;

import br.com.omotor.labelcreatorproject.model.SystemTranslate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabelResults {
    private List<SystemTranslate> approved;
    private List<SystemTranslate> reproved;
}
