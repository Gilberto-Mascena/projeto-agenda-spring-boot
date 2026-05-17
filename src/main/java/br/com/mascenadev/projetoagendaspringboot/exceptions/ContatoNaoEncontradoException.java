package br.com.mascenadev.projetoagendaspringboot.exceptions;

public class ContatoNaoEncontradoException extends RuntimeException {

    public ContatoNaoEncontradoException() {
        super("Contato não encontrado");
    }

    public ContatoNaoEncontradoException(Long id) {
        super("Contato com o ID:" + id + " não foi encontrado");
    }
}
