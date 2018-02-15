package gov.mt.dnrc.toggle.software.services.spi;

import gov.mt.dnrc.toggle.core.service.spi.IGenericService;
import gov.mt.dnrc.toggle.software.models.Software;

/**
 * Software Service Interface that allows the application to follow the proper contract.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ISoftwareService extends IGenericService<Software, Long> {
}
