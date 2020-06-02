package com.automation.butler;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private static final String UI_DIR = "classpath:/WEB-INF/view/react/build/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**")
                .addResourceLocations(UI_DIR + "static/");
        registry.addResourceHandler("/*.js")
                .addResourceLocations(UI_DIR);
        registry.addResourceHandler("/*.json")
                .addResourceLocations(UI_DIR);
        registry.addResourceHandler("/*.ico")
                .addResourceLocations(UI_DIR);
        registry.addResourceHandler("/index.html")
                .addResourceLocations(UI_DIR + "index.html");
    }
}
