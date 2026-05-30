package br.com.mascenadev.projetoagendaspringboot.exception;

import java.time.Instant;

public record ErrorResponse(
        Instant dataHora,
        Integer status,
        String codigo,
        String erro,
        String mensagem,
        String caminho
) {
}
