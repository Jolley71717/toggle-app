package gov.mt.dnrc.toggle.toggle;

import gov.mt.dnrc.toggle.toggle.form.ToggleForm;
import gov.mt.dnrc.toggle.toggle.service.ToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ToggleController {

    @Autowired
    ApplicationContext context;

    @Autowired
    ToggleService toggleService;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toggles", method = RequestMethod.GET)
    public String viewToggles(Model model) {

        ToggleForm toggleForm =  new ToggleForm();
        toggleForm.setToggles(toggleService.getAllToggles());
        model.addAttribute("toggleForm", toggleForm);

        return "toggles";
    }

    /**
     *
     * @param toggleForm
     * @param model
     * @return
     */
    @RequestMapping(value = "/toggles", method = RequestMethod.POST)
    public String saveToggles(@ModelAttribute ToggleForm toggleForm, Model model) {

        if(toggleForm.getToggles() != null) {

            for(Toggle formToggle: toggleForm.getToggles()) {
                for(Toggle toggle: toggleService.getAllToggles()) {
                    if(formToggle.getId() == toggle.getId()) {
                        toggle.setEnabled(formToggle.getEnabled());
                        toggleService.saveToggle(toggle);
                    }
                }
            }
        }

        toggleForm.setToggles(toggleService.getAllToggles());
        model.addAttribute("toggleForm", toggleForm);

        return "toggles";
    }
}
