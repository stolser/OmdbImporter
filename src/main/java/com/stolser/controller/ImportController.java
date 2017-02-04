package com.stolser.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "/")
public class ImportController {

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String formPage() {
        return "form";
    }
}
