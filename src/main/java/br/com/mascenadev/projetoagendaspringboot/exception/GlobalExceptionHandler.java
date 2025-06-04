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
 * Classe responsável pelo tratamento global de exceções da aplicação.
 * <p>
 * Utiliza a anotação {@link ControllerAdvice} para capturar e tratar exceções
 * lançadas pelos controladores REST de forma centralizada, retornando uma resposta padronizada.
 * </p>
 * Fornece tratadores para:
 * <ul>
 *     <li>Erros de validação ({@link MethodArgumentNotValidException})</li>
 *     <li>Entidades não encontradas ({@link ContatoNaoEncontradoException})</li>
 *     <li>Outras exceções genéricas não tratadas</li>
 * </ul>
 *
 * @author Gilberto Dev
 * @see ErroResponse
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata erros de validação de argumentos anotados com {@code @Valid}.
     *
     * @param ex exceção capturada ao validar os campos da requisição
     * @return resposta contendo os campos inválidos e mensagens de erro
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
     * Trata exceções do tipo {@link ContatoNaoEncontradoException}.
     *
     * @param ex exceção lançada quando um contato não é encontrado
     * @return resposta com status 404 (Not Found) e mensagem de erro
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
     * Trata exceções genéricas que não foram capturadas por outros tratadores.
     *
     * @param ex exceção genérica lançada durante a execução
     * @return resposta com status 500 (Internal Server Error) e detalhes da exceção
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
