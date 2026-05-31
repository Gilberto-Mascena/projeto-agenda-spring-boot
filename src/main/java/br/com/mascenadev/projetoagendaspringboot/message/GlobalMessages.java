package br.com.mascenadev.projetoagendaspringboot.message;

import br.com.mascenadev.projetoagendaspringboot.interfaces.MessageBase;
import lombok.Getter;

@Getter
public enum GlobalMessages implements MessageBase {

    DADOS_INVALIDOS("GLOB_001", "Corpo da requisição vazio ou formato JSON inválido."),
    VALIDACAO_FALHA("GLOB_002", "Erro de validação nos campos da requisição."),
    METODO_INVALIDO("GLOB_003", "Método HTTP não permitido para este endpoint."),
    ERRO_INTERNO("GLOB_006", "Ocorreu um erro interno inesperado. Tente novamente mais tarde.");

    private final String codigo;
    private final String mensagem;

    GlobalMessages(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
}
