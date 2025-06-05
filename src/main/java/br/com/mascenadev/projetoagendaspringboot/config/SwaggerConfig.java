package br.com.mascenadev.projetoagendaspringboot.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring configuration class for customizing the OpenAPI (Swagger) documentation.
 *
 * <p>This configuration defines API metadata such as title, version, description,
 * and license information, which will be displayed in the generated Swagger UI.</p>
 *
 * @author Gilberto Dev
 */
@Configuration
public class SwaggerConfig {

    // Inject the API version value from application.properties (ex: agenda.api.version=1.0.0)
    @Value("${projeto-agenda-spring-boot.api.version}")
    private String apiVersion;

    /**
     * Builds and returns a custom {@link OpenAPI} object with API metadata.
     *
     * @return an OpenAPI instance with configured information
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
     * Creates and returns the {@link License} object for the API.
     *
     * @return a License instance with name and URL
     */
    private License apiLicense() {
        return new License()
                .name("MIT License")
                .url("https://github.com/Gilberto-Mascena/dslist/blob/main/LICENSE.md");
    }
}