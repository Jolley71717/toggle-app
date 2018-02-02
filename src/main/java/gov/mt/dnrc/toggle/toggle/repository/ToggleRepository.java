package gov.mt.dnrc.toggle.toggle.repository;

import gov.mt.dnrc.toggle.toggle.Toggle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToggleRepository extends CrudRepository<Toggle, Long> {
}
