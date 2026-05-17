package br.com.mascenadev.projetoagendaspringboot.exceptions;

import java.time.LocalDateTime;

public record ErroResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        Object details
) {
}
