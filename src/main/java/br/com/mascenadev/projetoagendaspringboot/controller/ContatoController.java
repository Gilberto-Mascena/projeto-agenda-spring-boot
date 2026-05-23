package br.com.mascenadev.projetoagendaspringboot.controller;

import br.com.mascenadev.projetoagendaspringboot.dto.ContatoRequestDTO;
import br.com.mascenadev.projetoagendaspringboot.dto.ContatoResponseDTO;
import br.com.mascenadev.projetoagendaspringboot.service.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    @Operation(summary = "Criar um novo contato", description = "Cria um novo contato com as informações fornecidas no corpo da requisição.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contato criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos campos da requisição"),
            @ApiResponse(responseCode = "422", description = "Dados inválidos.")
    })
    public ResponseEntity<ContatoResponseDTO> salvarContato(@RequestBody @Valid ContatoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.salvarContato(request));
    }

    @GetMapping()
    @Operation(summary = "Listar todos os contatos", description = "Retorna uma lista de todos os contatos cadastrados em ordem alfabética pelo nome ascendente. Retorna lista vazia se não houver registros.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de contatos retornada com sucesso"),
    })
    public ResponseEntity<List<ContatoResponseDTO>> listarTodos() {
        return ResponseEntity.ok().body(contatoService.listarTodos());
    }

    @GetMapping("/buscar")
    @Operation(summary = "Busca global de contatos", description = "Realiza uma busca global por contatos com base em um termo fornecido (nome, email ou telefone).")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso (pode retornar uma lista vazia)"),
            @ApiResponse(responseCode = "422", description = "Regra de negócio violada (termo de busca com menos de 3 caracteres)"),

    })
    public ResponseEntity<List<ContatoResponseDTO>> buscaGlobal(@RequestParam String termo) {
        return ResponseEntity.ok(contatoService.buscaGlobal(termo));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar contato por ID", description = "Retorna os detalhes de um contato específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contato encontrado e retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<ContatoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contatoService.buscarId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar contato", description = "Atualiza as informações de um contato específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contato updated com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação nos campos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<ContatoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ContatoRequestDTO request) {
        return ResponseEntity.ok(contatoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir contato", description = "Exclui um contato específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Contato excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado")
    })
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        contatoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
