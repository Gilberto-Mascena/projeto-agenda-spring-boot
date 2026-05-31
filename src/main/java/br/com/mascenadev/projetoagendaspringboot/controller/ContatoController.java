package br.com.mascenadev.projetoagendaspringboot.controller;

import br.com.mascenadev.projetoagendaspringboot.doc.ContatoControllerDoc;
import br.com.mascenadev.projetoagendaspringboot.dto.ContatoRequestDTO;
import br.com.mascenadev.projetoagendaspringboot.dto.ContatoResponseDTO;
import br.com.mascenadev.projetoagendaspringboot.service.ContatoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public ResponseEntity<List<ContatoResponseDTO>> listarTodos() {
        return ResponseEntity.ok().body(contatoService.listarTodos());
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<List<ContatoResponseDTO>> buscaGlobal(@RequestParam String termo) {
        return ResponseEntity.ok(contatoService.buscaGlobal(termo));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contatoService.buscarPorId(id));
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
