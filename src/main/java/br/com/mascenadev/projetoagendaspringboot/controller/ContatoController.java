package br.com.mascenadev.projetoagendaspringboot.controller;

import br.com.mascenadev.projetoagendaspringboot.doc.ContatoControllerDoc;
import br.com.mascenadev.projetoagendaspringboot.dto.ContatoRequestDTO;
import br.com.mascenadev.projetoagendaspringboot.dto.ContatoResponseDTO;
import br.com.mascenadev.projetoagendaspringboot.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController implements ContatoControllerDoc {

    private final ContatoService contatoService;

    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @Override
    @PostMapping
    public ResponseEntity<ContatoResponseDTO> salvarContato(@RequestBody @Valid ContatoRequestDTO request) {
        ContatoResponseDTO contato = contatoService.salvarContato(request);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(contato.id())
                .toUri();
        return ResponseEntity.created(uri).body(contato);
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<ContatoResponseDTO>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(contatoService.listarTodos(paginacao));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contatoService.buscarPorId(id));
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Page<ContatoResponseDTO>> buscaGlobal(@RequestParam(required = false) String termo, @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(contatoService.buscaGlobal(termo, paginacao));
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ContatoRequestDTO request) {
        return ResponseEntity.ok(contatoService.atualizar(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        contatoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
