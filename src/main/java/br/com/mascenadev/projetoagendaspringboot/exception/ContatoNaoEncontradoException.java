package br.com.mascenadev.projetoagendaspringboot.exception;

public class ContatoNaoEncontradoException extends RuntimeException {
    public ContatoNaoEncontradoException(Long id) {
        super("Contato com o ID:" + id + " não foi encontrado");
    }

    public ContatoNaoEncontradoException() {
        super("Contato não encontrado");
    }
}
