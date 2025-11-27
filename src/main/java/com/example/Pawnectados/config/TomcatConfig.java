package com.example.Pawnectados.config;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {
    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> tomcatCustomizer() {
        return (factory) -> factory.addConnectorCustomizers(connector -> {
            connector.setMaxParameterCount(5000); // aumenta límite de partes
            connector.setMaxPostSize(20 * 1024 * 1024); // 20MB
        });
    }
}

