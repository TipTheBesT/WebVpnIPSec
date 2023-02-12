package ru.thebestsoft.webvpn.controllers;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.thebestsoft.webvpn.services.ControlService.Answer;
import ru.thebestsoft.webvpn.services.ControlService.IPSecService;
import ru.thebestsoft.webvpn.services.emploee.Include;

@Controller
public class IndexControl {

    @GetMapping("/")
    public String doGet(Model model) {

        IPSecService IPSecService = new IPSecService();
        Answer answer = IPSecService.getInfo();

        boolean serverActive = false;
        if (answer!=null && !answer.getAnswer().isEmpty() && answer.getAnswer().contains("active (running)")) {
                serverActive = true;
        }

        model.addAttribute("serverActive",serverActive);
        model.addAttribute("include", new Include("main","main"));
        return "index";
    }
}
