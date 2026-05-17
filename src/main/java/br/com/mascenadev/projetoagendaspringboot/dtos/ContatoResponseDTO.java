package br.com.mascenadev.projetoagendaspringboot.dtos;

public record ContatoResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone
) {
}
