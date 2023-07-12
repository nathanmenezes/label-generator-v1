package br.com.omotor.labelcreatorproject.model.dto;

import br.com.omotor.labelcreatorproject.model.SystemTranslate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemTranslateDto {

    private Long id;

    private String created_at;

    private String keyLabel;

    private String value;

    private Integer system_locale_id;

    public SystemTranslateDto(SystemTranslate st) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.id = st.getId();
        this.created_at = sdf.format(st.getCreated_at());
        this.keyLabel = st.getKeyLabel();
        this.value = st.getValue();
        this.system_locale_id = st.getSystem_locale_id();
    }
}
