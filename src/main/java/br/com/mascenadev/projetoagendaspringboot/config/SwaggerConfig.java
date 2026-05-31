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
                .description("""
                          API REST para gerenciamento de contatos, permitindo:
                          - Criar
                          - Listar todos
                          - Buscar dinâmicamente com JPQL
                          - Atualizar
                          - Excluir contatos.
                        
                          Desenvolvida com Spring Boot, utilizando o padrão RESTful e seguindo as melhores práticas de desenvolvimento.
                        """)
                .contact(new Contact()
                        .name("Gilberto | Dev")
                        .email("gilbertomascena@gmail.com")
                        .url("https://github.com/Gilberto-Mascena"))
                .license(apiLicense()));
    }

    private License apiLicense() {
        return new License()
                .name("MIT License")
                .url("https://github.com/Gilberto-Mascena/projeto-agenda-spring-boot/blob/main/LICENSE.md");
    }
}