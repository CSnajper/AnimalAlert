package spring.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.domain.Authority;
import spring.domain.User;
import spring.dto.UserDTO;
import spring.repository.UserRepository;
import spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String userPage(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "users";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String lofinForm(Model model) {
        model.addAttribute("user", new User());
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginProcess(@ModelAttribute User user) {
        return "loginForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerProcess(@ModelAttribute @Valid UserDTO userDTO, BindingResult results) {
        if(results.hasErrors())
            return "registerForm";

        Set<String> authorities = new HashSet<>();
        authorities.add("ROLE_USER");

        userDTO.setAuthorities(authorities);

        userService.createUser(userDTO);

        return "redirect:/users";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String showAllUsers(Model model) {
        List<User> users = new ArrayList<>();
        users.addAll(userRepository.findAll());
        model.addAttribute("users", users);
        return "users";
    }

    @RequestMapping(value = "/activate", method = RequestMethod.GET)
    public String activateUser(@RequestParam(value = "key") String key, Model model) {
        Optional<User> user = userService.activateRegistration(key);

        if(user.isPresent()) {
            model.addAttribute("message", "Konto zostalo aktywowane :)");
        }
        else
            model.addAttribute("message", "Konto nie zostało aktywowane. Być może kod aktywacyjny jest niepoprawny");

        return "activationForm";
    }
}
