package ru.thebestsoft.webvpn.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.thebestsoft.webvpn.services.UserService;
import ru.thebestsoft.webvpn.services.emploee.Include;
import ru.thebestsoft.webvpn.services.emploee.User;

@Controller
@RequestMapping("/admin/user/")
public class UserControl {

    @PostMapping("/add/")
    public String doPostAdd(@Valid @ModelAttribute("user") User user, BindingResult binding, RedirectAttributes RedirectAttr) {

        if (!binding.hasErrors()) {
            UserService userService = new UserService();
            userService.addUser(user);
        } else {
            RedirectAttr.addFlashAttribute("org.springframework.validation.BindingResult.user", binding);
            RedirectAttr.addFlashAttribute("user", user);
        }

        return "redirect:/admin/";
    }

    @GetMapping( "/del/")
    public String doGetDel (@RequestParam(name = "login") String login, Model model ) {
            UserService userService = new UserService();
            userService.delUser(login);
            model.addAttribute("userList", userService.getUserList());
        return "redirect:/admin/";
    }

    @GetMapping("/info/")
    public String doGetInfo (@RequestParam(name = "login") String login, Model model ) {
        UserService uService = new UserService();

        model.addAttribute("user",uService.getUser(login));
        model.addAttribute("include", new Include("admin/info","info"));
        return "index";
    }

}
