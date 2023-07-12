package br.com.omotor.labelcreatorproject.model;

import br.com.omotor.labelcreatorproject.model.dto.LabelDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemTranslate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date created_at = new Date();

    private String keyLabel;

    private String value;

    private Integer system_locale_id = 1;

    @ManyToOne
    private Project project;

    public SystemTranslate(String key, String value) {
        this.keyLabel = key;
        this.value = value;
    }

    public void edit(LabelDto labelDto){
        if(labelDto.getKeyLabel() != null){
            this.keyLabel = labelDto.getKeyLabel();
        }
        if(labelDto.getValue() != null){
            this.value = labelDto.getValue();
        }
    }
}
