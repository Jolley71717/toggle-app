package gov.mt.dnrc.toggle.toggle.form;

import gov.mt.dnrc.toggle.toggle.model.Toggle;

import java.util.List;

/**
 * Form view data model used to manage the toggle form.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
public class ToggleForm {

    private List<Toggle> toggles;

    /**
     * default Constructor
     */
    public ToggleForm() {
        // default Constructor
    }

    /**
     * Stores the toggle object lists
     *
     * @return returns the list of toggles.
     */
    public List<Toggle> getToggles() {
        return toggles;
    }

    /**
     * Sets the toggle lists to be viewable or saved for later.
     *
     * @param toggles The list of Toggle objects.
     */
    public void setToggles(List<Toggle> toggles) {
        this.toggles = toggles;
    }
}
