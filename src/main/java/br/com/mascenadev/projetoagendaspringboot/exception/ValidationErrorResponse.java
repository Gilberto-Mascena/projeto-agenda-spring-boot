package br.com.mascenadev.projetoagendaspringboot.exception;

import java.time.Instant;
import java.util.List;

public record ValidationErrorResponse(
        Instant dataHora,
        Integer status,
        String codigo,
        String erro,
        String mensagem,
        String caminho,
        List<ValidationErrorDetails> erros
) {
}
