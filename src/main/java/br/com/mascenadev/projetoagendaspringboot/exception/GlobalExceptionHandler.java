package br.com.mascenadev.projetoagendaspringboot.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErroResponse> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErroResponse err = montarErro(ex.getCodigo(), ex.getMessage(), status, request);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErroResponse> handleContatoNaoEncontrado(ObjectNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErroResponse err = montarErro(ex.getCodigo(), ex.getMessage(), status, request);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponseValidacao> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<ValidationErrorDetails> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationErrorDetails(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroResponseValidacao err = montarErroValidacao("VALIDACAO_FALHA", "Erro de validação nos campos da requisição.", status, request, erros);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErroResponse err = montarErro("DADOS_INVALIDOS", "Corpo da requisição vazio ou formato JSON inválido.", status, request);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErroResponse> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        String mensagem = String.format("O método HTTP '%s' não é suportado para este caminho. Métodos aceitos: %s", ex.getMethod(), ex.getSupportedHttpMethods());
        ErroResponse err = montarErro("METODO_NAO_PERMITIDO", mensagem, status, request);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErroResponse err = montarErro("ERRO_INTERNO", "Ocorreu um erro interno inesperado. Tente novamente mais tarde.", status, request);
        return ResponseEntity.status(status).body(err);
    }

    private ErroResponse montarErro(String codigo, String mensagem, HttpStatus status, HttpServletRequest request) {
        return new ErroResponse(
                Instant.now(),
                status.value(),
                codigo,
                "Erro na Requisição",
                mensagem,
                request.getRequestURI()
        );
    }

    private ErroResponseValidacao montarErroValidacao(String codigo, String mensagem, HttpStatus status, HttpServletRequest request, List<ValidationErrorDetails> erros) {
        return new ErroResponseValidacao(
                Instant.now(),
                status.value(),
                codigo,
                "Erro de Validação",
                mensagem,
                request.getRequestURI(),
                erros
        );
    }
}
