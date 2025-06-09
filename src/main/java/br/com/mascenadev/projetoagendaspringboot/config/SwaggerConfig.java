package br.com.mascenadev.projetoagendaspringboot.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração Spring ({@link org.springframework.context.annotation.Configuration})
 * responsável por personalizar a documentação da API utilizando **OpenAPI 3.0** (anteriormente conhecida como Swagger).
 * <p>
 * Esta configuração define metadados essenciais da API, como título, versão, descrição e informações de licença.
 * Esses detalhes são exibidos na interface de usuário interativa do **Swagger UI**,
 * geralmente acessível em {@code /swagger-ui.html} ou {@code /swagger-ui/index.html},
 * permitindo a exploração e teste dos endpoints da API de forma intuitiva.
 * </p>
 *
 * @author Gilberto Dev
 * @see io.swagger.v3.oas.models.OpenAPI
 * @see io.swagger.v3.oas.models.info.Info
 * @see io.swagger.v3.oas.annotations.OpenAPIDefinition
 * @since 1.0.0
 */
@Configuration
public class SwaggerConfig {

    /**
     * Versão da API, injetada a partir da propriedade {@code projeto-agenda-spring-boot.api.version}
     * definida no arquivo {@code application.properties} ou {@code application.yml}.
     * Esta versão será exibida na documentação do Swagger UI.
     */
    @Value("${projeto-agenda-spring-boot.api.version}")
    private String apiVersion;

    /**
     * Configura e retorna uma instância personalizada de {@link OpenAPI} para a documentação da API.
     * <p>
     * Este método, anotado com {@link org.springframework.context.annotation.Bean},
     * é executado pelo Spring para criar e registrar o objeto {@code OpenAPI} no contexto da aplicação.
     * Ele define as informações globais da API, como título ({@code agenda API}),
     * versão ({@code apiVersion} injetada), descrição e detalhes da licença,
     * que serão apresentados no Swagger UI.
     * </p>
     *
     * @return Uma instância de {@link OpenAPI} contendo os metadados configurados da API.
     * @see io.swagger.v3.oas.models.OpenAPI#info(Info)
     * @see io.swagger.v3.oas.models.info.Info
     * @see #apiLicense()
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("agenda API")
                .version(apiVersion)
                .description("Documentation of agenda API")
                .license(apiLicense()));
    }

    /**
     * Cria e retorna o objeto {@link License} que detalha a licença de uso da API.
     * <p>
     * Atualmente configura a API para usar a **Licença MIT**, com um link direto para o seu repositório no GitHub.
     * </p>
     * @return Uma instância de {@link License} com o nome ("MIT License") e URL correspondente.
     * @see io.swagger.v3.oas.models.info.License
     */
    private License apiLicense() {
        return new License()
                .name("MIT License")
                .url("https://github.com/Gilberto-Mascena/dslist/blob/main/LICENSE.md");
    }
}