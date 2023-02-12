package ru.thebestsoft.webvpn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.thebestsoft.webvpn.services.ControlService.Answer;
import ru.thebestsoft.webvpn.services.ControlService.IPSecService;
import ru.thebestsoft.webvpn.services.UserService;
import ru.thebestsoft.webvpn.services.emploee.Include;
import ru.thebestsoft.webvpn.services.emploee.User;

@Controller
@RequestMapping("/admin/")
public class AdminControl {

    @GetMapping("/")
    public String doGet (Model model) {

        UserService userService = new UserService();
        IPSecService IPSecService = new IPSecService();
        Answer answer = IPSecService.getInfo();

        boolean serverActive = false;
        if (answer!=null && !answer.getAnswer().isEmpty()) {
            if (answer.getAnswer().contains("active (running)")) {
                serverActive = true;
                answer.setAnswer(answer.getAnswer().replace("active (running)","<server class=\"enable\">active (running)</server>"));
                answer.setAnswer(answer.getAnswer().replace("●","<server class=\"enable\">●</server>"));
            } else {
                answer.setAnswer(answer.getAnswer().replace("inactive (dead)","<server class=\"disable\">inactive (dead)</server>"));
                answer.setAnswer(answer.getAnswer().replace("○","<server class=\"disable\">●</server>"));
            }
        }

        model.addAttribute("userList", userService.getUserList());
        model.addAttribute("serverStatusError", answer.getError());
        model.addAttribute("serverStatusAnswer", answer.getAnswer());
        model.addAttribute("serverStatus", serverActive);
        model.addAttribute("include", new Include("admin/main","main",
                "(userList=${userList},serverStatusError=${serverStatusError},serverStatusAnswer=${serverStatusAnswer},serverStatus=${serverStatus})"));

        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }

        return "index";
    }

}
