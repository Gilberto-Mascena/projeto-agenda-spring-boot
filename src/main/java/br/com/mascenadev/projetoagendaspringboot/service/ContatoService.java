package br.com.mascenadev.projetoagendaspringboot.service;

import br.com.mascenadev.projetoagendaspringboot.entities.Contato;
import br.com.mascenadev.projetoagendaspringboot.exception.ContatoNaoEncontradoException;
import br.com.mascenadev.projetoagendaspringboot.repository.ContatoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço responsável por realizar operações relacionadas à entidade {@link Contato}.
 * <p>
 * Atua como camada intermediária entre o controlador e o repositório,
 * encapsulando a lógica de negócio da aplicação.
 *
 * @author Gilberto Dev
 * </p>
 */

public class ContatoService {

    private final ContatoRepository contatoRepository;

    /**
     * Construtor que injeta o repositório de contatos.
     *
     * @param contatoRepository repositório para acesso aos dados de contatos
     */
    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    /**
     * Salva um novo contato ou atualiza um existente.
     *
     * @param contato entidade {@link Contato} a ser salva
     * @return o contato salvo
     */
    public Contato salvar(Contato contato) {
        return contatoRepository.save(contato);
    }

    /**
     * Busca um contato pelo seu ID.
     *
     * @param id identificador do contato
     * @return um {@link Optional} contendo o contato, se encontrado
     */
    public Optional<Contato> buscarPorId(Long id) {
        return contatoRepository.findById(id);
    }

    /**
     * Retorna todos os contatos cadastrados.
     *
     * @return lista de contatos
     */
    public List<Contato> buscarTodos() {
        return contatoRepository.findAll();
    }

    /**
     * Atualiza os dados de um contato existente.
     * <p>
     * Caso o contato não seja encontrado, uma {@link ContatoNaoEncontradoException} será lançada.
     * </p>
     *
     * @param id                identificador do contato a ser atualizado
     * @param contatoAtualizado objeto contendo os novos dados do contato
     * @return o contato atualizado
     * @throws ContatoNaoEncontradoException se o contato não for encontrado
     */
    public Contato atualizar(Long id, Contato contatoAtualizado) {
        return contatoRepository.findById(id)
                .map(contato -> {
                    contato.setNome(contatoAtualizado.getNome());
                    contato.setEmail(contatoAtualizado.getEmail());
                    contato.setTelefone(contatoAtualizado.getTelefone());
                    return contatoRepository.save(contato);
                })
                .orElseThrow(() -> new ContatoNaoEncontradoException(id));
    }

    /**
     * Exclui um contato com base no ID.
     * <p>
     * Se o contato não for encontrado, uma {@link ContatoNaoEncontradoException} será lançada.
     * </p>
     *
     * @param id identificador do contato a ser excluído
     * @throws ContatoNaoEncontradoException se o contato não existir
     */
    public void excluir(Long id) {
        if (!contatoRepository.existsById(id)) {
            throw new ContatoNaoEncontradoException(id);
        }
        contatoRepository.deleteById(id);
    }
}
