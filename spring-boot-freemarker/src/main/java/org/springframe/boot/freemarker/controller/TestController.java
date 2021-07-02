package org.springframe.boot.freemarker.controller;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@RestController
public class TestController {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

//    @Autowired
//    private Configuration configuration;

    @GetMapping("/hello")
    public void hello(){
        System.out.println();
    }
}
