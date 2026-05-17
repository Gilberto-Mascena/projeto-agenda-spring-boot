package br.com.mascenadev.projetoagendaspringboot.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${projeto-agenda-spring-boot.api.version}")
    private String apiVersion;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("agenda API")
                .version(apiVersion)
                .description("Documentation of agenda API")
                .contact(new Contact()
                        .name("Gilberto | Dev")
                        .email("gilbertomascena@gmail.com")
                        .url(""))
                .license(apiLicense()));
    }

    private License apiLicense() {
        return new License()
                .name("MIT License")
                .url("https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/LICENSE.md");
    }
}