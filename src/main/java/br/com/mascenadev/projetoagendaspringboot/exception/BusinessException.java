package br.com.mascenadev.projetoagendaspringboot.exception;

import br.com.mascenadev.projetoagendaspringboot.interfaces.MessageBase;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final String codigo;

    public BusinessException(MessageBase mensagemBase) {
        super(mensagemBase.getMensagem());
        this.codigo = mensagemBase.getCodigo();
    }
}
