package br.com.mascenadev.projetoagendaspringboot.controller;

import br.com.mascenadev.projetoagendaspringboot.dto.ContatoRequestDTO;
import br.com.mascenadev.projetoagendaspringboot.dto.ContatoResponseDTO;
import br.com.mascenadev.projetoagendaspringboot.service.ContatoService;
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
    public ResponseEntity<ContatoResponseDTO> salvarContato(@RequestBody @Valid ContatoRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contatoService.salvarContato(request));
    }

    @GetMapping()
    public ResponseEntity<List<ContatoResponseDTO>> listarTodos() {
        return ResponseEntity.ok().body(contatoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contatoService.buscarId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ContatoRequestDTO request) {
        return ResponseEntity.ok(contatoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        contatoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
