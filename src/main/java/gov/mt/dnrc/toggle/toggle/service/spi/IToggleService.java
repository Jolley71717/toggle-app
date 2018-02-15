package gov.mt.dnrc.toggle.toggle.service.spi;

import gov.mt.dnrc.toggle.core.service.spi.IGenericService;
import gov.mt.dnrc.toggle.toggle.model.Toggle;

/**
 * Toggle Service Interface that allows the application to follow the proper contract.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IToggleService extends IGenericService<Toggle, Long> {

    /**
     * Method to see if the state of a module is enabled.
     *
     * @param moduleName The name/key value of the module.
     * @return returns a boolean if the module is enabled
     */
    Boolean isModuleEnabled(String moduleName);
}
