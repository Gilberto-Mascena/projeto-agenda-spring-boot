package br.com.mascenadev.projetoagendaspringboot.service;

import br.com.mascenadev.projetoagendaspringboot.dto.ContatoRequestDTO;
import br.com.mascenadev.projetoagendaspringboot.dto.ContatoResponseDTO;
import br.com.mascenadev.projetoagendaspringboot.entity.Contato;
import br.com.mascenadev.projetoagendaspringboot.exception.BusinessException;
import br.com.mascenadev.projetoagendaspringboot.exception.ObjectNotFoundException;
import br.com.mascenadev.projetoagendaspringboot.mapper.ContatoMapper;
import br.com.mascenadev.projetoagendaspringboot.message.ContatoMessages;
import br.com.mascenadev.projetoagendaspringboot.repository.ContatoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final ContatoMapper contatoMapper;

    public ContatoService(ContatoRepository contatoRepository, ContatoMapper contatoMapper) {
        this.contatoRepository = contatoRepository;
        this.contatoMapper = contatoMapper;
    }

    public ContatoResponseDTO salvarContato(ContatoRequestDTO dto) {
        if (contatoRepository.existsByEmail(dto.email())) {
            throw new BusinessException(ContatoMessages.EMAIL_JA_CADASTRADO);
        }

        Contato contato = contatoMapper.toEntity(dto);
        contatoRepository.save(contato);
        return contatoMapper.toResponseDTO(contato);
    }

    public List<ContatoResponseDTO> listarTodos() {
        return contatoRepository.findAllByOrderByNomeAsc().stream()
                .map(contatoMapper::toResponseDTO)
                .toList();
    }

    public List<ContatoResponseDTO> buscaGlobal(String termo) {
        if (termo == null || termo.isBlank()) {
            return Collections.emptyList();
        }

        String termoLimpo = termo.trim();

        if (termoLimpo.length() < 3) {
            throw new BusinessException(ContatoMessages.TERMO_BUSCA_CURTO);
        }

        return contatoRepository.buscaGlobal(termoLimpo)
                .stream()
                .map(contatoMapper::toResponseDTO)
                .toList();
    }

    public ContatoResponseDTO buscarId(Long id) {
        return contatoRepository.findById(id)
                .map(contatoMapper::toResponseDTO)
                .orElseThrow(() -> new ObjectNotFoundException(ContatoMessages.CONTATO_NAO_ENCONTRADO));
    }

    public ContatoResponseDTO atualizar(Long id, ContatoRequestDTO dto) {
        Contato contato = contatoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(ContatoMessages.CONTATO_NAO_ENCONTRADO));
        contatoMapper.atualizarContato(dto, contato);
        Contato contatoAtualizado = contatoRepository.save(contato);
        return contatoMapper.toResponseDTO(contatoAtualizado);
    }

    public void excluir(Long id) {
        if (!contatoRepository.existsById(id)) {
            throw new ObjectNotFoundException(ContatoMessages.CONTATO_NAO_ENCONTRADO);
        }
        contatoRepository.deleteById(id);
    }
}
