package org.soft.base.server.configuration;

import org.soft.base.annotation.Description;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagePathConfiguration implements WebMvcConfigurer {
    @Override
    @Description(message = "")
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler( "/editorimages/**" ).addResourceLocations( "file:/Users/lanruijiang/Desktop/document/image/images/" );
    }
}
