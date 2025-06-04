package br.com.mascenadev.projetoagendaspringboot.exception;

/**
 * Exceção lançada quando um contato não é encontrado no sistema.
 * <p>
 * Pode ser utilizada em serviços, controladores ou em qualquer camada
 * onde seja necessário sinalizar que o recurso {@code Contato} não está disponível.
 * </p>
 *
 * <p>Existem dois construtores disponíveis:</p>
 * <ul>
 *     <li>Um que recebe o {@code ID} do contato e gera uma mensagem personalizada.</li>
 *     <li>Outro sem argumentos, com uma mensagem genérica.</li>
 * </ul>
 *
 * @author Gilberto Dev
 * @see RuntimeException
 */
public class ContatoNaoEncontradoException extends RuntimeException {

    /**
     * Construtor que recebe o ID do contato não encontrado e
     * gera uma mensagem personalizada de erro.
     *
     * @param id o identificador do contato não encontrado
     */
    public ContatoNaoEncontradoException(Long id) {
        super("Contato com o ID:" + id + " não foi encontrado");
    }

    /**
     * Construtor padrão com mensagem genérica.
     */
    public ContatoNaoEncontradoException() {
        super("Contato não encontrado");
    }
}
