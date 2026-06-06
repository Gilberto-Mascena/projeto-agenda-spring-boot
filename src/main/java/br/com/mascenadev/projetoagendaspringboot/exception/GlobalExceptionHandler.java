package br.com.mascenadev.projetoagendaspringboot.exception;

import br.com.mascenadev.projetoagendaspringboot.interfaces.MessageBase;
import br.com.mascenadev.projetoagendaspringboot.message.GlobalMessages;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex, HttpServletRequest request) {

        log.warn("Regra de negócio violada na rota [{}]: Código: {}, Mensagem: {}",
                request.getRequestURI(),
                ex.getCodigo(),
                ex.getMessage()
        );

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorResponse err = montarErro(
                ex.getCodigo(),
                ex.getMessage(),
                status,
                request);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleContatoNaoEncontrado(ObjectNotFoundException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse err = montarErro(
                ex.getCodigo(),
                ex.getMessage(),
                status,
                request);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {

        log.warn("Falha de validação de campos na rota [{}]. Quantidade de erros: {}",
                request.getRequestURI(),
                ex.getBindingResult()
                        .getFieldErrorCount()
        );

        List<ValidationErrorDetails> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? error.getDefaultMessage() : "Campo inválido",
                        (existingMessage, newMessage) -> existingMessage
                ))
                .entrySet()
                .stream()
                .map(entry -> new ValidationErrorDetails(entry.getKey(), entry.getValue()))
                .toList();

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ValidationErrorResponse err = montarErroValidacao(
                status,
                request,
                erros);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse err = montarErro(
                GlobalMessages.DADOS_INVALIDOS,
                status,
                request);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {

        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        ErrorResponse err = montarErro(
                GlobalMessages.METODO_INVALIDO,
                status,
                request);
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {

        log.error("Erro inesperado: ", ex);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse err = montarErro(
                GlobalMessages.ERRO_INTERNO,
                status,
                request);
        return ResponseEntity.status(status).body(err);
    }

    private ErrorResponse montarErro(MessageBase messageBase, HttpStatus status, HttpServletRequest request) {

        return new ErrorResponse(
                Instant.now(),
                status.value(),
                messageBase.getCodigo(),
                "Erro na Requisição",
                messageBase.getMensagem(),
                request.getRequestURI()
        );
    }

    private ErrorResponse montarErro(String codigo, String mensagem, HttpStatus status, HttpServletRequest request) {

        return new ErrorResponse(
                Instant.now(),
                status.value(),
                codigo,
                "Erro na Requisição",
                mensagem,
                request.getRequestURI()

        );
    }

    private ValidationErrorResponse montarErroValidacao(HttpStatus status, HttpServletRequest request, List<ValidationErrorDetails> erros) {

        return new ValidationErrorResponse(
                Instant.now(),
                status.value(),
                GlobalMessages.VALIDACAO_FALHA.getCodigo(),
                "Erro de Validação",
                GlobalMessages.VALIDACAO_FALHA.getMensagem(),
                request.getRequestURI(),
                erros
        );
    }
}