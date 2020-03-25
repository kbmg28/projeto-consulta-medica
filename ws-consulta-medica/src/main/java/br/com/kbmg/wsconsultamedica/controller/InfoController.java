package br.com.kbmg.wsconsultamedica.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/version")
public class InfoController {
    @Value("${build.version}")
    String version;

    @GetMapping
    public String getVersion() {
        return version;
    }
}