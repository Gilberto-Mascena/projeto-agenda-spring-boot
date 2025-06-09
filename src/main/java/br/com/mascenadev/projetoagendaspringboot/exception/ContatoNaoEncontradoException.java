package br.com.mascenadev.projetoagendaspringboot.exception;

/**
 * Exceção personalizada lançada para indicar que um recurso {@link br.com.mascenadev.projetoagendaspringboot.entities.Contato}
 * específico não pôde ser encontrado no sistema.
 * <p>
 * Esta exceção é do tipo **unchecked** (estende {@link RuntimeException}), o que significa que
 * não exige tratamento obrigatório em tempo de compilação.
 * É tipicamente lançada pela camada de serviço ({@link br.com.mascenadev.projetoagendaspringboot.service.ContatoService})
 * e capturada por um controlador ({@link br.com.mascenadev.projetoagendaspringboot.controller.ContatoController})
 * ou por um manipulador de exceções global ({@code @ControllerAdvice}),
 * resultando geralmente em uma resposta HTTP **404 Not Found**.
 * </p>
 *
 * <p>Dois construtores estão disponíveis para flexibilidade na criação da mensagem de erro:</p>
 * <ul>
 * <li>Um que aceita o {@code ID} do contato, gerando uma mensagem específica com base nesse ID.</li>
 * <li>Outro construtor padrão, que utiliza uma mensagem de erro mais genérica.</li>
 * </ul>
 *
 * @author Gilberto Dev
 * @see RuntimeException
 * @see br.com.mascenadev.projetoagendaspringboot.service.ContatoService
 * @see br.com.mascenadev.projetoagendaspringboot.controller.ContatoController
 * @since 1.0.0
 */
public class ContatoNaoEncontradoException extends RuntimeException {

    /**
     * Construtor padrão que cria uma exceção com uma mensagem de erro genérica.
     * Útil quando o contexto específico do ID do contato não está disponível ou não é relevante.
     */
    public ContatoNaoEncontradoException() {
        super("Contato não encontrado");
    }

    /**
     * Construtor que cria uma exceção com uma mensagem de erro detalhada,
     * incluindo o identificador do contato que não foi encontrado.
     *
     * @param id O {@link Long} identificador único do contato que não foi localizado.
     */
    public ContatoNaoEncontradoException(Long id) {
        super("Contato com o ID:" + id + " não foi encontrado");
    }
}
