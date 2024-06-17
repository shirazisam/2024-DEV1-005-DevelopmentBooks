package com.bnp.kata.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/public")
public class PublicController {

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/about")
    public String aboutInfo() {
        return "Public ingress configuration works for " + appName;
    }

}
