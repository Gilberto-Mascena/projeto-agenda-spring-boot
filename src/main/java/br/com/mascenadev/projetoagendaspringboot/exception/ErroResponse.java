package br.com.mascenadev.projetoagendaspringboot.exception;

import java.time.LocalDateTime;

/**
 * Representa o **corpo padrão de uma resposta de erro** (payload) retornada pela API.
 * <p>
 * Esta {@code record} class encapsula informações detalhadas e padronizadas sobre exceções
 * ou problemas ocorridos durante o processamento de requisições HTTP.
 * Ela é projetada para ser utilizada por manipuladores de exceções (como classes anotadas com {@code @ControllerAdvice})
 * para fornecer uma resposta consistente e informativa ao cliente da API, facilitando a depuração e o tratamento de erros.
 * </p>
 *
 * @param timestamp O {@link java.time.LocalDateTime} exato em que o erro foi registrado (momento da ocorrência).
 * @param status    O código de status HTTP ({@code int}) que indica a natureza do erro (e.g., 404 para Not Found, 400 para Bad Request).
 * @param error     Uma breve mensagem descritiva do tipo de erro (e.g., "Not Found", "Bad Request").
 * @param details   Um objeto {@link Object} contendo informações adicionais específicas sobre o erro,
 *                  que pode ser uma mensagem de exceção, uma lista de erros de validação, ou qualquer outro dado relevante.
 * @author Gilberto Dev
 * @see org.springframework.web.bind.annotation.ControllerAdvice
 * @see org.springframework.http.HttpStatus
 * @since 1.0.0
 */
public record ErroResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        Object details
) {
}
