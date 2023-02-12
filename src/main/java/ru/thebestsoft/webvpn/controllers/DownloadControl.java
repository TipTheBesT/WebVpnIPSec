package ru.thebestsoft.webvpn.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/download/")
public class DownloadControl {
    final private String pathCA = "/etc/ipsec.d/cacerts/ca-cert.pem";
    @GetMapping("/cert/")
    public void doGetDownload(HttpServletResponse response) {

        Path file = Paths.get(pathCA);

        String publicFilename = "vpn-thebestsoft-ru";
        String contentType = "application/x-pem-file";
        String exp = ".pem";

        if (Files.exists(file)){
            response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(publicFilename, StandardCharsets.UTF_8) + exp);
            response.setContentType(contentType);
            response.setCharacterEncoding("UTF-8");

            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                throw new RuntimeException("IOError writing file to output stream");
            }
        }

    }
}
