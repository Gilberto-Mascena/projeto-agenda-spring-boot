package br.com.mascenadev.projetoagendaspringboot.exception;

public class ContatoNaoEncontradoException extends RuntimeException {
    public ContatoNaoEncontradoException(Long id) {
        super("Contato não encontrado com o ID:" + id);
    }

    public ContatoNaoEncontradoException() {
        super("Contato não encontrado");
    }
}
