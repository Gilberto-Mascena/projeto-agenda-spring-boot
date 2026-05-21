package br.com.mascenadev.projetoagendaspringboot.exceptions;

import java.time.Instant;
import java.util.List;

public record ErroResponseValidacao(
        Instant dataHora,
        Integer status,
        String codigo,
        String erro,
        String mensagem,
        String caminho,
        List<ValidationErrorDetails> erros
) {
}
