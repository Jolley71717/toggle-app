package gov.mt.dnrc.toggle.toggle.repository;

import gov.mt.dnrc.toggle.toggle.model.Toggle;
import org.springframework.data.repository.CrudRepository;

/**
 * Crud style repository following the Spring Data model.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IToggleRepository extends CrudRepository<Toggle, Long> {
}
