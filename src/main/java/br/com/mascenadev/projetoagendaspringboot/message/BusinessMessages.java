package br.com.mascenadev.projetoagendaspringboot.message;

import br.com.mascenadev.projetoagendaspringboot.interfaces.MessageBase;
import lombok.Getter;

@Getter
public enum BusinessMessages implements MessageBase {

    CONTATO_NAO_ENCONTRADO("BUSINESS_001", "Contato não encontrado"),
    EMAIL_JA_CADASTRADO("BUSINESS_002", "Já existe um contato cadastrado com este e-mail"),
    TERMO_BUSCA_CURTO("BUSINESS_003", "O termo de busca deve conter pelo menos 3 caracteres."),
    FALHA_PROCESSAMENTO("BUSINESS_004", "Não foi possível processar a requisição no estado atual do sistema.");

    private final String codigo;
    private final String mensagem;

    BusinessMessages(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
}
