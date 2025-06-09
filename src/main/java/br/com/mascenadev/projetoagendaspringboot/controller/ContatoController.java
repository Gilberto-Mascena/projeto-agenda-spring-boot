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
 * **Controlador REST** ({@link org.springframework.web.bind.annotation.RestController})
 * responsável por expor os endpoints da API para a **gestão de contatos**.
 * <p>
 * Este controlador manipula requisições HTTP para todas as operações **CRUD (Create, Read, Update, Delete)**
 * relacionadas à entidade {@link Contato}. Ele atua como a camada de entrada para o serviço de contatos,
 * transformando requisições HTTP em chamadas de método de serviço e formatando as respostas para o cliente.
 * </p>
 * <p>
 * Todos os endpoints deste controlador são acessíveis sob o caminho base {@code /contatos}.
 * </p>
 *
 * @author Gilberto Dev
 * @see ContatoService
 * @see ContatoRequestDTO
 * @see ContatoResponseDTO
 * @see ContatoNaoEncontradoException
 * @since 1.0.0
 */
@RestController
@RequestMapping("/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    /**
     * Construtor para injeção de dependência do serviço de contatos no controlador.
     * <p>
     * O Spring Framework injeta automaticamente uma instância de {@link ContatoService}
     * quando o controlador é criado.
     * </p>
     *
     * @param contatoService O serviço de contatos que contém a lógica de negócio para manipulação de contatos.
     */
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    /**
     * Cria um novo contato no sistema.
     * <p>
     * Este endpoint {@code POST /contatos} recebe os dados de um novo contato através de um {@link ContatoRequestDTO}.
     * O DTO é validado e convertido para uma entidade {@link Contato}, que é então persistida no banco de dados
     * através do {@link ContatoService}.
     * </p>
     * <p>
     * Em caso de sucesso, retorna o contato salvo com seu ID gerado e o status HTTP 201 (Created),
     * incluindo o cabeçalho {@code Location} com a URI do novo recurso.
     * </p>
     *
     * @param request DTO contendo os dados do contato a ser criado. Deve ser validado conforme as regras definidas no {@link ContatoRequestDTO}.
     * @return {@link ResponseEntity} contendo o {@link ContatoResponseDTO} do contato salvo e o status HTTP 201 (Created).
     * @see ContatoService#salvar(Contato)
     * @see ContatoRequestDTO
     * @see ContatoResponseDTO
     * @see Contato
     */
    @PostMapping
    public ResponseEntity<ContatoResponseDTO> salvar(@RequestBody @Valid ContatoRequestDTO request) {
        Contato contato = request.toEntity();
        Contato salvo = contatoService.salvar(contato);
        URI location = URI.create("/contatos/" + salvo.getId());
        return ResponseEntity.created(location).body(new ContatoResponseDTO(salvo));
    }

    /**
     * Busca e retorna um contato específico pelo seu identificador único.
     * <p>
     * Este endpoint {@code GET /contatos/{id}} recebe o ID de um contato como parte da URL.
     * Ele tenta localizar o contato no banco de dados.
     * </p>
     *
     * @param id O identificador único ({@code Long}) do contato a ser buscado.
     * @return {@link ResponseEntity} contendo o {@link ContatoResponseDTO} do contato encontrado e o status HTTP 200 (OK).
     * @throws ContatoNaoEncontradoException Se o contato com o ID fornecido não for encontrado na base de dados,
     *                                       resultando em um status HTTP 404 (Not Found).
     * @see ContatoService#buscarPorId(Long)
     * @see ContatoResponseDTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> buscarPorId(@PathVariable Long id) {
        Contato contato = contatoService.buscarPorId(id)
                .orElseThrow(() -> new ContatoNaoEncontradoException(id));
        return ResponseEntity.ok(new ContatoResponseDTO(contato));
    }

    /**
     * Retorna uma lista de todos os contatos cadastrados no sistema.
     * <p>
     * Este endpoint {@code GET /contatos} recupera todos os registros de contatos do banco de dados,
     * os converte para uma lista de {@link ContatoResponseDTO} e os retorna ao cliente.
     * </p>
     *
     * @return {@link ResponseEntity} contendo uma {@link java.util.List} de {@link ContatoResponseDTO}
     * com todos os contatos cadastrados e o status HTTP 200 (OK).
     * @see ContatoService#buscarTodos()
     * @see ContatoResponseDTO
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
     * Atualiza os dados de um contato existente.
     * <p>
     * Este endpoint {@code PUT /contatos/{id}} recebe o identificador único do contato na URL
     * e um {@link ContatoRequestDTO} no corpo da requisição com os novos dados a serem aplicados.
     * Os dados são validados e o serviço é acionado para persistir as alterações.
     * </p>
     *
     * @param id      O identificador único ({@code Long}) do contato a ser atualizado.
     * @param request DTO contendo os dados atualizados do contato. Deve ser validado conforme as regras definidas no {@link ContatoRequestDTO}.
     * @return {@link ResponseEntity} contendo o {@link ContatoResponseDTO} do contato atualizado e o status HTTP 200 (OK).
     * @throws ContatoNaoEncontradoException Se o contato com o ID fornecido não for encontrado na base de dados,
     *                                       resultando em um status HTTP 404 (Not Found).
     * @see ContatoService#atualizar(Long, Contato)
     * @see ContatoRequestDTO
     * @see ContatoResponseDTO
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
     * Exclui um contato específico da base de dados pelo seu identificador único.
     * <p>
     * Este endpoint {@code DELETE /contatos/{id}} tenta remover o contato correspondente ao ID fornecido.
     * </p>
     *
     * @param id O identificador único ({@code Long}) do contato a ser excluído.
     * @return {@link ResponseEntity} com o status HTTP 204 (No Content) se a exclusão for bem-sucedida.
     * @throws ContatoNaoEncontradoException Se o contato com o ID fornecido não for encontrado na base de dados,
     *                                       resultando em um status HTTP 404 (Not Found).
     * @see ContatoService#excluir(Long)
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
