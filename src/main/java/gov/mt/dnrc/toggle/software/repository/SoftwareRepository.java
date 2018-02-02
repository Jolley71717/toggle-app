package gov.mt.dnrc.toggle.software.repository;

import gov.mt.dnrc.toggle.software.models.Software;
import org.springframework.data.repository.CrudRepository;

public interface SoftwareRepository  extends CrudRepository<Software, Long> {

}
