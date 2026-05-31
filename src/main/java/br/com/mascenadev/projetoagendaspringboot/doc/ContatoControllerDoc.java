package br.com.mascenadev.projetoagendaspringboot.doc;

import br.com.mascenadev.projetoagendaspringboot.dto.ContatoRequestDTO;
import br.com.mascenadev.projetoagendaspringboot.dto.ContatoResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Tag(
        name = "Contato",
        description = "Endpoint relacionado à gestão de contatos, incluindo criação, listagem, busca global e deleção.")
public interface ContatoControllerDoc {

    @Operation(
            summary = "Criar um novo contato",
            description = "Cria um novo contato com as informações fornecidas no corpo da requisição.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Contato criado com sucesso. URI do novo recurso disponível no cabeçalho Location."),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de validação nos campos da requisição"),
            @ApiResponse(
                    responseCode = "422",
                    description = "Dados inválidos.")
    })
    ResponseEntity<ContatoResponseDTO> salvarContato(@RequestBody @Valid ContatoRequestDTO request);

    @Operation(
            summary = "Listar todos os contatos",
            description = "Retorna uma lista de todos os contatos cadastrados em ordem alfabética pelo nome ascendente. Retorna lista vazia se não houver registros.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de contatos retornada com sucesso"),
    })
    ResponseEntity<List<ContatoResponseDTO>> listarTodos();

    @Operation(
            summary = "Busca global de contatos",
            description = "Realiza uma busca global por contatos com base em um termo fornecido (nome, email ou telefone).")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Busca realizada com sucesso (pode retornar uma lista vazia)"),
            @ApiResponse(
                    responseCode = "422",
                    description = "Regra de negócio violada (termo de busca com menos de 3 caracteres)"),

    })
    ResponseEntity<List<ContatoResponseDTO>> buscaGlobal(@RequestParam String termo);

    @Operation(
            summary = "Buscar contato por ID",
            description = "Retorna os detalhes de um contato específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Contato encontrado e retornado com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Contato não encontrado")
    })
    ResponseEntity<ContatoResponseDTO> buscarPorId(@PathVariable Long id);

    @Operation(
            summary = "Atualizar contato",
            description = "Atualiza as informações de um contato específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Contato atualizado com sucesso"),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de validação nos campos fornecidos"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Contato não encontrado")
    })
    ResponseEntity<ContatoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ContatoRequestDTO request);

    @Operation(
            summary = "Excluir contato",
            description = "Exclui um contato específico com base no ID fornecido.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Contato excluído com sucesso"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Contato não encontrado")
    })
    ResponseEntity<Void> excluir(@PathVariable Long id);
}
