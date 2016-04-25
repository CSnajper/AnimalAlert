package spring.controller;

import spring.domain.Organisation;
import spring.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrganisationsController {
    @Autowired
    OrganisationRepository organisationRepository;

    @RequestMapping(value = "/addOrganisation", method = RequestMethod.GET)
    public String userPage(Model model) {
        model.addAttribute("user", new Organisation());
        return "addUser";
    }

    @RequestMapping(value = "/addOrganisation", method = RequestMethod.POST)
    public String addUser(@ModelAttribute Organisation organisation) {
        organisationRepository.save(organisation);
        return "users";
    }

    @RequestMapping(value = "/organisations", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        List<Organisation> organisations = new ArrayList<>();
        organisations.addAll(organisationRepository.findAll());
        model.addAttribute("organisations", organisations);
        return "users";
    }
}
