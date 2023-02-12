package ru.thebestsoft.webvpn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.thebestsoft.webvpn.services.emploee.Include;

@Controller
@RequestMapping("/manual/")
public class ManualControl {

    @GetMapping("/windows/")
    public String doGetWindows(Model model) {

        model.addAttribute("include", new Include("manual/windows","windows"));
        return "index";
    }

    @GetMapping("/android/")
    public String doGetAndroid(Model model) {
        model.addAttribute("include", new Include("manual/android","android"));
        return "index";
    }

    @GetMapping("/ios/")
    public String doGetIos(Model model) {
        model.addAttribute("include", new Include("manual/ios","ios"));
        return "index";
    }

    @GetMapping("/macos/")
    public String doGetMacos(Model model) {
        model.addAttribute("include", new Include("manual/macos","macos"));
        return "index";
    }
}
