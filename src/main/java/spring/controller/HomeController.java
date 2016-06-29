package spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spring.domain.User;
import spring.repository.UserRepository;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Inject
    UserRepository userRepository;

    @RequestMapping(value = { "", "/", "/home" }, method = RequestMethod.GET)
    public String hello(Model model, Principal principal) {
        User user = null;
        if (null != principal) {
            log.info("Principal not null");
            user = this.userRepository.findOneByUsername(principal.getName()).map(u -> u).orElse(new User());
        }
        else {
            log.info("Principal null");
            user = new User();
        }
        model.addAttribute("user", user);
        return "home";
    }

}
