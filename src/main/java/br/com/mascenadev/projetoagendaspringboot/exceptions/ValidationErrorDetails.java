package br.com.mascenadev.projetoagendaspringboot.exceptions;

public record ValidationErrorDetails(
        String campo,
        String mensagem
) {
}
