package com.kun.hardreset.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskyOperationsController {

    @RequestMapping("/prueba")
    public String test() {
        return "testing";
    }


}
