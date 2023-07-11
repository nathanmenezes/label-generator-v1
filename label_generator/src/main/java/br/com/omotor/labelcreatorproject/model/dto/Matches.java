package br.com.omotor.labelcreatorproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Matches {
    private List<TranslateReturn> matches;
}
