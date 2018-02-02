package gov.mt.dnrc.toggle.software.controllers;

import gov.mt.dnrc.toggle.software.models.Software;
import gov.mt.dnrc.toggle.software.services.SoftwareService;
import gov.mt.dnrc.toggle.toggle.FeatureToggle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;

    @RequestMapping("/software")
    @FeatureToggle(feature = "Software Tracking")
    public String getAllSoftware(Model model) {

        model.addAttribute("softwares", softwareService.getAllSoftware());

        return "list";
    }

    /**
     *
     * @param software
     * @param model
     * @return
     */
    @RequestMapping("/software/save")
    @FeatureToggle(feature = "Software Tracking")
    public String saveSoftware(@Valid Software software, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "software";
        }

        softwareService.saveSoftware(software);

        return getAllSoftware(model);
    }

    @RequestMapping("/software/edit/{id}")
    @FeatureToggle(feature = "Software Tracking")
    public String saveSoftware(@PathVariable("id") Long id, Model model) {

        if(id == null) {
            return getAllSoftware(model);
        }

        model.addAttribute("software", softwareService.getSoftware(id));

        return "software";
    }
}
