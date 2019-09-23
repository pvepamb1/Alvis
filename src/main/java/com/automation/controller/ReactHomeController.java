package com.automation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReactHomeController {

    @RequestMapping(value = "/")
    public String rootToIndex() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/{path:[^\\.]*}")
    public String reactRouting() {
        return "forward:/";
    }

}
