package gov.mt.dnrc.toggle.software.controllers;

import gov.mt.dnrc.toggle.software.models.Software;
import gov.mt.dnrc.toggle.software.services.SoftwareService;
import gov.mt.dnrc.toggle.toggle.interceptors.annotations.FeatureToggle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Controller that will handle the software views
 *
 * @author Brad Villa
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller
public class SoftwareController {

    private static final String SOFTWARE_VIEW = "software";
    private static final String SOFTWARE_LIST_VIEW = "list";

    @Autowired
    private SoftwareService softwareService;

    /**
     * Retrieves all of the software and pushes it to the softwares page.
     *
     * @param model The model returned from the view.
     * @return Returns the softwares html view.
     */
    @RequestMapping("/software")
    @FeatureToggle(feature = "Software Tracking")
    public String retrieveAll(Model model) {

        model.addAttribute("softwares", softwareService.retrieveAll());
        return SOFTWARE_LIST_VIEW;
    }

    /**
     * Allows the user to save the software to the database.
     *
     * @param software The software object that is updated/saved from the view
     * @param model The view model used to handle the data from the view.
     * @return Returns the updated software list, returns the software view if there is an error.
     */
    @RequestMapping("/software/save")
    @FeatureToggle(feature = "Software Tracking")
    public String save(@Valid Software software, BindingResult bindingResult, Model model) {

        // Return the user to the view if the pojo validation failed.
        if (bindingResult.hasErrors()) {
            return SOFTWARE_VIEW;
        }

        softwareService.save(software);
        return retrieveAll(model);
    }

    /**
     * Allows the user to edit the software they are requesting.
     *
     * @param id The id of the software to be saved and queried.
     * @param model The view model used to handle the data from the view.
     * @return Returns the software that will be edited.
     */
    @RequestMapping("/software/edit/{id}")
    @FeatureToggle(feature = "Software Tracking")
    public String edit(@PathVariable("id") Long id, Model model) {

        // Cancel the process if the id is non-existent.
        if(id == null) {
            return retrieveAll(model);
        }

        model.addAttribute(SOFTWARE_VIEW, softwareService.retrieve(id));
        return SOFTWARE_VIEW;
    }
}
