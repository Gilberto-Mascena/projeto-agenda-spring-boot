package br.com.mascenadev.projetoagendaspringboot.service;

import br.com.mascenadev.projetoagendaspringboot.entities.Contato;
import br.com.mascenadev.projetoagendaspringboot.exception.ContatoNaoEncontradoException;
import br.com.mascenadev.projetoagendaspringboot.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * **Camada de Serviço** ({@link org.springframework.stereotype.Service}) responsável
 * por encapsular e executar a **lógica de negócio** principal para a entidade {@link Contato}.
 * <p>
 * Atua como intermediária entre a camada de apresentação ({@link br.com.mascenadev.projetoagendaspringboot.controller.ContatoController})
 * e a camada de persistência ({@link br.com.mascenadev.projetoagendaspringboot.repository.ContatoRepository}).
 * Esta classe coordena as operações de CRUD, aplica regras de negócio,
 * validações (quando necessário) e lida com a transacionalidade para garantir a integridade dos dados.
 * </p>
 *
 * @author Gilberto Dev
 * @see Contato
 * @see br.com.mascenadev.projetoagendaspringboot.repository.ContatoRepository
 * @see br.com.mascenadev.projetoagendaspringboot.controller.ContatoController
 * @see br.com.mascenadev.projetoagendaspringboot.exception.ContatoNaoEncontradoException
 * @since 1.0.0
 */
@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;

    /**
     * Construtor para injeção de dependência do repositório de contatos.
     * <p>
     * O Spring Framework injeta uma instância de {@link ContatoRepository} para
     * permitir que o serviço interaja com a camada de persistência e realize
     * operações de acesso a dados.
     * </p>
     *
     * @param contatoRepository A interface de repositório para acesso aos dados de contatos no banco de dados.
     */
    @Autowired
    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    /**
     * Salva um novo {@link Contato} no banco de dados ou atualiza um contato existente.
     * <p>
     * Se o objeto {@link Contato} fornecido tiver um ID nulo, um novo contato será criado.
     * Se o ID não for nulo e corresponder a um contato existente, este será atualizado.
     * </p>
     *
     * @param contato A entidade {@link Contato} a ser persistida.
     * @return A instância de {@link Contato} salva ou atualizada, com o ID gerado (se for uma nova criação).
     * @see ContatoRepository#save(Object)
     */
    public Contato salvar(Contato contato) {
        return contatoRepository.save(contato);
    }

    /**
     * Busca um {@link Contato} específico pelo seu identificador único.
     * <p>
     * Este método retorna um {@link java.util.Optional} para indicar a possível ausência do contato:
     * <ul>
     * <li>Um {@code Optional} contendo o {@link Contato} se ele for encontrado.</li>
     * <li>Um {@code Optional} vazio ({@link Optional#empty()}) se nenhum contato corresponder ao ID.</li>
     * </ul>
     * </p>
     *
     * @param id O {@link Long} identificador único do contato a ser buscado.
     * @return Um {@link Optional} que pode conter a entidade {@link Contato} encontrada.
     * @see ContatoRepository#findById(Object)
     */
    public Optional<Contato> buscarPorId(Long id) {
        return contatoRepository.findById(id);
    }

    /**
     * Retorna uma lista contendo todos os {@link Contato}s cadastrados no banco de dados.
     * <p>
     * A lista pode estar vazia se não houver contatos registrados.
     * </p>
     *
     * @return Uma {@link java.util.List} de entidades {@link Contato}.
     * @see ContatoRepository#findAll()
     */
    public List<Contato> buscarTodos() {
        return contatoRepository.findAll();
    }

    /**
     * Atualiza as informações de um {@link Contato} existente com base no ID fornecido.
     * <p>
     * O método primeiro tenta localizar o contato pelo {@code id}. Se encontrado,
     * ele atualiza as propriedades de nome, e-mail e telefone com os dados do {@code contatoAtualizado}
     * e persiste as alterações.
     * </p>
     *
     * @param id                O {@link Long} identificador único do contato a ser atualizado.
     * @param contatoAtualizado A entidade {@link Contato} contendo os novos dados (nome, email, telefone)
     *                          que serão aplicados ao contato existente.
     * @return A entidade {@link Contato} com os dados atualizados.
     * @throws ContatoNaoEncontradoException Se nenhum contato for encontrado com o {@code id} fornecido.
     * @see ContatoRepository#findById(Object)
     * @see ContatoRepository#save(Object)
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
     * Exclui um {@link Contato} do banco de dados com base no seu identificador único.
     * <p>
     * Antes de tentar a exclusão, o método verifica a existência do contato pelo ID.
     * Se o contato não for encontrado, uma {@link ContatoNaoEncontradoException} é lançada.
     * Caso contrário, o contato é removido permanentemente.
     * </p>
     *
     * @param id O {@link Long} identificador único do contato a ser excluído.
     * @throws ContatoNaoEncontradoException Se nenhum contato for encontrado com o {@code id} fornecido.
     * @see ContatoRepository#existsById(Object)
     * @see ContatoRepository#deleteById(Object)
     */
    public void excluir(Long id) {
        if (!contatoRepository.existsById(id)) {
            throw new ContatoNaoEncontradoException(id);
        }
        contatoRepository.deleteById(id);
    }
}
