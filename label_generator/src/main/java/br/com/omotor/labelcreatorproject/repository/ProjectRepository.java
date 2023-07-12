package br.com.omotor.labelcreatorproject.repository;

import br.com.omotor.labelcreatorproject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByName(String string);

    boolean existsByName(String name);
}
