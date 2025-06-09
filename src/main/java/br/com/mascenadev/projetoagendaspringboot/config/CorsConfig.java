package br.com.mascenadev.projetoagendaspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Classe de configuração Spring ({@link org.springframework.context.annotation.Configuration})
 * responsável por habilitar e configurar o **CORS (Cross-Origin Resource Sharing)** para a aplicação.
 * <p>
 * O CORS é um mecanismo de segurança que permite que um navegador web faça requisições
 * a um servidor em um domínio diferente daquele onde a página original foi carregada.
 * Esta configuração é crucial para permitir que um frontend (como uma aplicação Angular, React ou Vue.js)
 * rodando em uma porta ou domínio diferente acesse os recursos da sua API RESTful com segurança.
 * </p>
 * <p>
 * Ao implementar {@link org.springframework.web.servlet.config.annotation.WebMvcConfigurer},
 * esta classe customiza as configurações de CORS padrão do Spring MVC.
 * </p>
 *
 * @author Gilberto Dev
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer
 * @see org.springframework.web.servlet.config.annotation.CorsRegistry
 * @since 1.0.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * Configura as regras de CORS para os endpoints da aplicação.
     * <p>
     * Este método é sobrescrito da interface {@link WebMvcConfigurer} para registrar
     * mapeamentos de CORS específicos. Ele define quais origens, métodos HTTP e cabeçalhos
     * são permitidos para acessar os recursos da API.
     * </p>
     * <ul>
     * <li>**{@code registry.addMapping("/contatos/**")}**: Aplica as regras de CORS a todos os endpoints
     * que começam com {@code /contatos/}.</li>
     * <li>**{@code .allowedOrigins("http://localhost:4200")}**: Permite que requisições vindas
     * especificamente do frontend Angular rodando em {@code http://localhost:4200} acessem a API.
     * **ATENÇÃO:** Em produção, esta origem deve ser o domínio real do seu frontend.</li>
     * <li>**{@code .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")}**: Permite as operações HTTP padrão
     * de leitura, criação, atualização e exclusão, além do método OPTIONS (usado para preflight requests CORS).</li>
     * <li>**{@code .allowedHeaders("*")}**: Permite todos os cabeçalhos nas requisições.</li>
     * <li>**{@code .allowCredentials(true)}**: Permite que requisições incluam credenciais (como cookies ou
     * cabeçalhos de autorização) em solicitações de cross-origin. Isso é importante para sessões ou autenticação baseada em token.</li>
     * </ul>
     *
     * @param registry O {@link CorsRegistry} para configurar as regras de mapeamento de CORS.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/contatos/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}