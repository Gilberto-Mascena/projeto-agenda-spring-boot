package br.com.mascenadev.projetoagendaspringboot.exception;

public record ValidationErrorDetails(
        String campo,
        String mensagem
) {
}
