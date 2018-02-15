package gov.mt.dnrc.toggle.toggle.service;

import gov.mt.dnrc.toggle.core.service.AbstractGenericService;
import gov.mt.dnrc.toggle.toggle.model.Toggle;
import gov.mt.dnrc.toggle.toggle.repository.IToggleRepository;
import gov.mt.dnrc.toggle.toggle.service.spi.IToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class that is responsible for the business logic managing toggles.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class ToggleService extends AbstractGenericService<Toggle, Long> implements IToggleService {

    private IToggleRepository toggleRepository;

    /**
     * Default Constructor to the service
     * @param toggleRepository The repository interface used to communicate with the domain layer.
     */
    @Autowired
    public ToggleService(IToggleRepository toggleRepository) {
        super(toggleRepository);

        this.toggleRepository = toggleRepository;
    }

    /**
     * Saves the toggle to the database.
     *
     * @param toggle the toggle to be saved.
     * @return returns the saved copy of the toggle object.
     */
    public Toggle save(Toggle toggle) {
        return toggleRepository.save(toggle);
    }

    /**
     * Service to save the state of all toggles within the application.
     * We want to ensure that each toggle in a list of toggles to be saved
     *
     * @param toggles The list of toggles to be saved to the database.
     * @return returns the saved list of toggles.
     */
    public List<Toggle> save(List<Toggle> toggles) {

        // Go through each toggle and save their status (disabled or enabled)
        if(toggles != null) {

            for(Toggle formToggle: toggles) {

                for(Toggle toggle: retrieveAll()) {

                    if(toggle.getId().equals(formToggle.getId())) {
                        toggle.setEnabled(formToggle.getEnabled());
                        save(toggle);
                    }
                }
            }
        }

        return toggles;
    }

    /**
     * Method to see if the state of a module is enabled.
     *
     * @param moduleName The name/key value of the module.
     * @return returns a boolean if the module is enabled
     */
    public Boolean isModuleEnabled(String moduleName) {

        for (Toggle toggle : retrieveAll()) {
            if (toggle.getModule().equalsIgnoreCase(moduleName)) {
                return toggle.getEnabled();
            }
        }

        return false;
    }
}
