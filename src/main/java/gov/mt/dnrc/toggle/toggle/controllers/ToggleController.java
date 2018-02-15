package gov.mt.dnrc.toggle.toggle.controllers;

import com.google.common.collect.Lists;
import gov.mt.dnrc.toggle.toggle.form.ToggleForm;
import gov.mt.dnrc.toggle.toggle.service.ToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller that manages the views for enabling/disabling features in the application.
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
public class ToggleController {

    private static final String TOGGLES_VIEW = "toggles";

    @Autowired
    ToggleService toggleService;

    /**
     * View responsible for displaying all of the toggleable features for the application.
     * @param model the view model object that contains all of the form information.
     * @return returns the toggles view
     */
    @RequestMapping(value = "/toggles", method = RequestMethod.GET)
    public String list(Model model) {

        ToggleForm toggleForm =  new ToggleForm();
        toggleForm.setToggles(Lists.newArrayList(toggleService.retrieveAll()));
        model.addAttribute("toggleForm", toggleForm);

        return TOGGLES_VIEW;
    }

    /**
     * View submission method to allow users to turn off features within the application.
     *
     * @param toggleForm the form for submission
     * @param model the view model object that contains all of the form information.
     * @return returns the toggles view
     */
    @RequestMapping(value = "/toggles", method = RequestMethod.POST)
    public String save(@ModelAttribute ToggleForm toggleForm, Model model) {

        toggleService.save(toggleForm.getToggles());
        toggleForm.setToggles(Lists.newArrayList(toggleService.retrieveAll()));

        model.addAttribute("toggleForm", toggleForm);
        return TOGGLES_VIEW;
    }
}
