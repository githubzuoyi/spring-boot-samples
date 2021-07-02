package org.springframe.boot.freemarker;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * freemarker 自动装配类
 */
@SpringBootApplication
public class FreemarkerBootstrap {


    public static void main(String[] args) {
        SpringApplication.run(FreemarkerBootstrap.class, args);
    }


}
