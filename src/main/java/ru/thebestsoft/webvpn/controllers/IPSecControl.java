package ru.thebestsoft.webvpn.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.thebestsoft.webvpn.services.ControlService.IPSecService;

@Controller
@RequestMapping("/admin/ipsec/")
public class IPSecControl {

    @GetMapping("/start/")
    public String doGetStart() {
        IPSecService ipsec = new IPSecService();
        ipsec.doStart();
        return "redirect:/admin/";
    }

    @GetMapping("/stop/")
    public String doGetStop() {
        IPSecService ipsec = new IPSecService();
        ipsec.doStop();
        return "redirect:/admin/";
    }

    @GetMapping("/restart/")
    public String doGetRestart() {
        IPSecService ipsec = new IPSecService();
        ipsec.doRestart();
        return "redirect:/admin/";
    }
}
