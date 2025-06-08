package br.com.mascenadev.projetoagendaspringboot.controller;

import br.com.mascenadev.projetoagendaspringboot.dtos.ContatoRequestDTO;
import br.com.mascenadev.projetoagendaspringboot.dtos.ContatoResponseDTO;
import br.com.mascenadev.projetoagendaspringboot.entities.Contato;
import br.com.mascenadev.projetoagendaspringboot.exception.ContatoNaoEncontradoException;
import br.com.mascenadev.projetoagendaspringboot.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador responsável pela gestão dos contatos da aplicação.
 * <p>
 * Este controlador manipula requisições HTTP para operações CRUD (Create, Read, Update, Delete)
 * para a entidade {@link Contato}. Ele oferece endpoints para criar, buscar, atualizar e excluir contatos.
 * </p>
 *
 * @author Gilberto Dev
 */
@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    /**
     * Construtor que injeta o serviço de contatos no controlador.
     *
     * @param contatoService serviço que contém a lógica de negócio para manipulação de contatos
     */
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    /**
     * Cria um novo contato.
     * <p>
     * Este método recebe um {@link ContatoRequestDTO}, converte-o em uma entidade {@link Contato},
     * e então chama o serviço para salvar esse contato no banco de dados. Ao final, retorna o contato
     * salvo com status HTTP 201 (Created).
     * </p>
     *
     * @param request dados do contato a ser criado
     * @return {@link ResponseEntity} contendo a resposta com o status HTTP 201 e o contato criado
     */
    @PostMapping
    public ResponseEntity<ContatoResponseDTO> salvar(@RequestBody @Valid ContatoRequestDTO request) {
        Contato contato = request.toEntity();
        Contato salvo = contatoService.salvar(contato);
        URI location = URI.create("/contatos/" + salvo.getId());
        return ResponseEntity.created(location).body(new ContatoResponseDTO(salvo));
    }

    /**
     * Busca um contato pelo seu ID.
     * <p>
     * Este método recebe o ID de um contato e tenta localizá-lo. Se encontrado, retorna o contato com status HTTP 200 (OK).
     * Caso contrário, lança uma exceção {@link ContatoNaoEncontradoException}, que resulta em um status HTTP 404 (Not Found).
     * </p>
     *
     * @param id identificador único do contato
     * @return {@link ResponseEntity} contendo o contato encontrado com status HTTP 200 (OK)
     * @throws ContatoNaoEncontradoException se o contato não for encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> buscarPorId(@PathVariable Long id) {
        Contato contato = contatoService.buscarPorId(id)
                .orElseThrow(() -> new ContatoNaoEncontradoException(id));
        return ResponseEntity.ok(new ContatoResponseDTO(contato));
    }

    /**
     * Retorna todos os contatos cadastrados.
     * <p>
     * Este método retorna todos os contatos no banco de dados, convertidos em objetos {@link ContatoResponseDTO}.
     * </p>
     *
     * @return {@link ResponseEntity} contendo a lista de todos os contatos com status HTTP 200 (OK)
     */
    @GetMapping
    public ResponseEntity<List<ContatoResponseDTO>> buscarTodos() {
        List<Contato> contatos = contatoService.buscarTodos();
        List<ContatoResponseDTO> contatoResponses = contatos.stream()
                .map(ContatoResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(contatoResponses);
    }

    /**
     * Atualiza um contato existente.
     * <p>
     * Este método recebe um ID e um {@link ContatoRequestDTO} com os novos dados do contato. O serviço é chamado para
     * atualizar o contato e, em seguida, retorna o contato atualizado com status HTTP 200 (OK).
     * </p>
     *
     * @param id      identificador único do contato a ser atualizado
     * @param request dados atualizados do contato
     * @return {@link ResponseEntity} contendo o contato atualizado com status HTTP 200 (OK)
     * @throws ContatoNaoEncontradoException se o contato não for encontrado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ContatoRequestDTO request) {
        // Converte o DTO para entidade
        Contato contato = request.toEntity();

        // Chama o serviço para atualizar o contato
        Contato atualizado = contatoService.atualizar(id, contato);

        // Retorna a resposta com status 200 (OK) e o ContatoResponse
        return ResponseEntity.ok(new ContatoResponseDTO(atualizado));
    }

    /**
     * Exclui um contato pelo seu ID.
     * <p>
     * Este método recebe um ID de um contato e tenta excluí-lo. Se o contato não for encontrado, lança uma exceção
     * {@link ContatoNaoEncontradoException}, resultando em um status HTTP 404 (Not Found). Caso contrário, o contato é
     * excluído com sucesso e retorna um status HTTP 204 (No Content).
     * </p>
     *
     * @param id identificador único do contato a ser excluído
     * @return {@link ResponseEntity} com status HTTP 204 (No Content) em caso de sucesso ou HTTP 404 (Not Found) se não encontrado
     * @throws ContatoNaoEncontradoException se o contato não for encontrado
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            // Chama o serviço para remover o contato
            contatoService.excluir(id);
            // Retorna resposta com status 204 (No Content), pois não há conteúdo para retornar
            return ResponseEntity.noContent().build();
        } catch (ContatoNaoEncontradoException e) {
            // Caso o contato não seja encontrado, retorna status 404 (Not Found)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
