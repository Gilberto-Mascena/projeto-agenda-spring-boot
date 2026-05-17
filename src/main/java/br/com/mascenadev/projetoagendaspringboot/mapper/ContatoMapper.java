package br.com.mascenadev.projetoagendaspringboot.mapper;

import br.com.mascenadev.projetoagendaspringboot.dtos.ContatoRequestDTO;
import br.com.mascenadev.projetoagendaspringboot.dtos.ContatoResponseDTO;
import br.com.mascenadev.projetoagendaspringboot.entities.Contato;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {

    public Contato toEntity(ContatoRequestDTO dto) {

        if (dto == null) return null;

        Contato contato = new Contato();
        if (dto.nome() != null) contato.setNome(dto.nome());
        if (dto.email() != null) contato.setEmail(dto.email());
        if (dto.telefone() != null) contato.setTelefone(dto.telefone());
        return contato;
    }

    public ContatoResponseDTO toResponseDTO(Contato contato) {

        if (contato == null) return null;

        return new ContatoResponseDTO(
                contato.getId(),
                contato.getNome(),
                contato.getEmail(),
                contato.getTelefone()
        );
    }

    public void AtualizarContato(ContatoRequestDTO dto, Contato contato) {

        if (dto == null || contato == null) return;

        if (dto.nome() != null) contato.setNome(dto.nome());
        if (dto.email() != null) contato.setEmail(dto.email());
        if (dto.telefone() != null) contato.setTelefone(dto.telefone());
    }
}
