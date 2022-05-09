package com.automation.butler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactHomeController {

    @GetMapping(value = "/")
    public String rootToIndex() {
        return "forward:/index.html";
    }

    @GetMapping(value = "/{path:[^\\.]*}")
    public String reactRouting() {
        return "forward:/";
    }

}
