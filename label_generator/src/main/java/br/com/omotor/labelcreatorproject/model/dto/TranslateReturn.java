package br.com.omotor.labelcreatorproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslateReturn {

    @JsonProperty("segment")
    private String segment;

    @JsonProperty("translation")
    private String translation;
}
