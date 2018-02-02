package gov.mt.dnrc.toggle.core.controllers;

import gov.mt.dnrc.toggle.toggle.service.ToggleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WelcomeController {

    @Autowired
    ToggleService toggleService;

    @RequestMapping("/")
    public String welcomeController(Model model) {

        model.addAttribute("isSoftwareModuleEnabled", toggleService.isModuleEnabled("Software Tracking"));
        return "index";
    }
}
