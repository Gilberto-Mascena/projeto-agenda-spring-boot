package br.com.mascenadev.projetoagendaspringboot.exceptions;

import br.com.mascenadev.projetoagendaspringboot.interfaces.MessageBase;
import lombok.Getter;

@Getter
public class ObjectNotFoundException extends RuntimeException {

    private final String codigo;

    public ObjectNotFoundException(MessageBase mensagemBase) {
        super(mensagemBase.getMensagem());
        this.codigo = mensagemBase.getCodigo();
    }
}
