package com.sys.registspatient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RouteController {

    @GetMapping("/patientstable")
    public String redirectToTablePatient() {
        return "tablepatient"; 
    }

    @GetMapping("/register")
    public String redirectToregister() {
        return "register"; 
    }

}
