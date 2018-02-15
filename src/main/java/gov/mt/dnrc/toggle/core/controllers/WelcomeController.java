package gov.mt.dnrc.toggle.core.controllers;

import gov.mt.dnrc.toggle.toggle.service.spi.IToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller that will handle the software views
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
public class WelcomeController {

    private static final String INDEX_VIEW  = "index";

    @Autowired
    IToggleService toggleService;

    /**
     * Entry Point into the application. Grabs all of the toggled features.
     *
     * @param model The view model controlling the data on the view.
     * @return returns the index page view template.
     */
    @RequestMapping("/")
    public String welcomeController(Model model) {

        model.addAttribute("isSoftwareModuleEnabled", toggleService.isModuleEnabled("Software Tracking"));
        return INDEX_VIEW;
    }
}
