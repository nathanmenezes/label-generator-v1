package br.com.omotor.labelcreatorproject.repository;

import br.com.omotor.labelcreatorproject.model.Project;
import br.com.omotor.labelcreatorproject.model.SystemTranslate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SystemTranslateRepository extends JpaRepository<SystemTranslate, Long> {
    boolean existsByValueAndKeyLabel(String value, String key);

    List<SystemTranslate> findByValueContainingOrKeyLabelContaining(String value, String keyLabel);
    List<SystemTranslate> findAllByProjectId(Long id);

    List<SystemTranslate> findAllByProjectIdAndSystemLocaleId(Long idProject, Integer systemLocaleId);
}
