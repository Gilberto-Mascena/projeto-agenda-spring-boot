package br.com.mascenadev.projetoagendaspringboot.dto;

public record ContatoResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone
) {
}
