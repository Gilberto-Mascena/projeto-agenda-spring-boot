package br.com.mascenadev.projetoagendaspringboot.message;

import br.com.mascenadev.projetoagendaspringboot.interfaces.MessageBase;
import lombok.Getter;

@Getter
public enum ContatoMessages implements MessageBase {

    CONTATO_NAO_ENCONTRADO("CONTATO_001", "Contato não encontrado"),
    EMAIL_JA_CADASTRADO("CONTATO_002", "Já existe um contato cadastrado com este e-mail"),
    TERMO_BUSCA_CURTO("CONTATO_003", "O termo de busca deve conter pelo menos 3 caracteres.");

    private final String codigo;
    private final String mensagem;

    ContatoMessages(String codigo, String mensagem) {
        this.codigo = codigo;
        this.mensagem = mensagem;
    }
}
