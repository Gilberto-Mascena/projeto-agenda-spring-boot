package br.com.mascenadev.projetoagendaspringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * **Manipulador de Exceções Global** para a aplicação da agenda.
 * <p>
 * Anotada com {@link org.springframework.web.bind.annotation.ControllerAdvice},
 * esta classe centraliza o tratamento de exceções lançadas por todos os
 * controladores REST ({@link org.springframework.web.bind.annotation.RestController}) da aplicação.
 * Ela intercepta exceções específicas e as traduz em respostas HTTP padronizadas e amigáveis para o cliente,
 * utilizando o DTO {@link ErroResponse} para formatar os detalhes do erro.
 * </p>
 * <p>
 * Atualmente, fornece métodos especializados para tratar:
 * </p>
 * <ul>
 * <li>**Erros de Validação:** Exceções do tipo {@link org.springframework.web.bind.MethodArgumentNotValidException},
 * que ocorrem quando os dados de entrada de uma requisição {@code @Valid} são inválidos.</li>
 * <li>**Recursos Não Encontrados:** Exceções personalizadas como {@link ContatoNaoEncontradoException},
 * indicando que um recurso solicitado não existe.</li>
 * <li>**Erros Internos Genéricos:** Qualquer outra {@link Exception} não capturada pelos tratadores específicos,
 * servindo como um fallback para garantir que nenhuma exceção interna vaze para o cliente sem um tratamento adequado.</li>
 * </ul>
 *
 * @author Gilberto Dev
 * @see ErroResponse
 * @see ContatoNaoEncontradoException
 * @see org.springframework.web.bind.MethodArgumentNotValidException
 * @see org.springframework.web.bind.annotation.ExceptionHandler
 * @see org.springframework.http.ResponseEntity
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata exceções do tipo {@link MethodArgumentNotValidException}, que ocorrem
     * quando a validação de argumentos anotados com {@code @Valid} falha.
     * <p>
     * Este método extrai todos os erros de validação dos campos, formata-os em um mapa
     * e retorna um {@link ResponseEntity} com status HTTP {@link HttpStatus#BAD_REQUEST} (400)
     * e um {@link ErroResponse} contendo os detalhes dos erros.
     * </p>
     *
     * @param ex A exceção {@link MethodArgumentNotValidException} capturada, contendo os resultados da validação.
     * @return Um {@link ResponseEntity} com {@link ErroResponse} detalhando os erros de validação e status HTTP 400.
     * @see MethodArgumentNotValidException
     * @see ErroResponse
     * @see HttpStatus#BAD_REQUEST
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }

        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação nos campos enviados",
                fieldErrors
        );

        return ResponseEntity.badRequest().body(erroResponse);
    }

    /**
     * Trata exceções personalizadas {@link ContatoNaoEncontradoException}.
     * <p>
     * Este método é acionado quando uma tentativa de buscar, atualizar ou excluir um contato
     * com um ID inexistente falha. Ele retorna um {@link ResponseEntity} com status HTTP
     * {@link HttpStatus#NOT_FOUND} (404) e um {@link ErroResponse} com a mensagem de erro.
     * </p>
     *
     * @param ex A exceção {@link ContatoNaoEncontradoException} que foi lançada.
     * @return Um {@link ResponseEntity} com {@link ErroResponse} e status HTTP 404 (Not Found).
     * @see ContatoNaoEncontradoException
     * @see ErroResponse
     * @see HttpStatus#NOT_FOUND
     */
    @ExceptionHandler(ContatoNaoEncontradoException.class)
    public ResponseEntity<ErroResponse> handleContatoNaoEncontrado(ContatoNaoEncontradoException ex) {
        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Contato não encontrado",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erroResponse);
    }

    /**
     * **Tratador de exceções genéricas (fallback)** para qualquer {@link Exception}
     * que não tenha sido especificamente tratada por outros métodos neste ou em outros
     * manipuladores de exceção.
     * <p>
     * Este método garante que nenhuma exceção não tratada vaze para o cliente,
     * retornando um {@link ResponseEntity} com status HTTP
     * {@link HttpStatus#INTERNAL_SERVER_ERROR} (500). A mensagem da exceção
     * é incluída, mas em um ambiente de produção, esta mensagem pode ser
     * mais genérica para evitar expor detalhes internos do sistema.
     * </p>
     *
     * @param ex A exceção {@link Exception} genérica que foi capturada.
     * @return Um {@link ResponseEntity} com {@link ErroResponse} e status HTTP 500 (Internal Server Error).
     * @see Exception
     * @see ErroResponse
     * @see HttpStatus#INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(Exception ex) {
        ErroResponse erroResponse = new ErroResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno no servidor",
                ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
    }
}
