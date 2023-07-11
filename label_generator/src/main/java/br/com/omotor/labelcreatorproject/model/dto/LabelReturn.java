package br.com.omotor.labelcreatorproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelReturn {

    private String typeScript;

    private String ptLabel;

    private String enLabel;

    private String labelNick;
}
