package gov.mt.dnrc.toggle.toggle.service;

import com.google.common.collect.Lists;
import gov.mt.dnrc.toggle.toggle.Toggle;
import gov.mt.dnrc.toggle.toggle.repository.ToggleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToggleService {

    private ToggleRepository toggleRepository;

    /**
     *
     * @param toggleRepository
     */
    @Autowired
    public ToggleService(ToggleRepository toggleRepository) {
        this.toggleRepository = toggleRepository;
    }

    /**
     *
     * @return
     */
    public List<Toggle> getAllToggles() {
        return Lists.newArrayList(toggleRepository.findAll());
    }

    /**
     *
     * @param toggle
     * @return
     */
    public Toggle saveToggle(Toggle toggle) {
        return toggleRepository.save(toggle);
    }

    /**
     *
     * @param moduleName
     * @return
     */
    public Boolean isModuleEnabled(String moduleName) {

        for (Toggle toggle : getAllToggles()) {
            if (toggle.getModule().equalsIgnoreCase(moduleName)) {
                return toggle.getEnabled();
            }
        }

        return false;
    }
}
