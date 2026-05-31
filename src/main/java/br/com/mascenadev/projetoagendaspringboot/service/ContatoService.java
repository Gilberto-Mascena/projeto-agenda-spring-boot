package br.com.mascenadev.projetoagendaspringboot.service;

import br.com.mascenadev.projetoagendaspringboot.dto.ContatoRequestDTO;
import br.com.mascenadev.projetoagendaspringboot.dto.ContatoResponseDTO;
import br.com.mascenadev.projetoagendaspringboot.entity.Contato;
import br.com.mascenadev.projetoagendaspringboot.exception.BusinessException;
import br.com.mascenadev.projetoagendaspringboot.exception.ObjectNotFoundException;
import br.com.mascenadev.projetoagendaspringboot.mapper.ContatoMapper;
import br.com.mascenadev.projetoagendaspringboot.message.BusinessMessages;
import br.com.mascenadev.projetoagendaspringboot.repository.ContatoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ContatoResponseDTO salvarContato(ContatoRequestDTO dto) {
        if (contatoRepository.existsByEmail(dto.email())) {
            throw new BusinessException(BusinessMessages.EMAIL_JA_CADASTRADO);
        }

        Contato contato = contatoMapper.toEntity(dto);
        contatoRepository.save(contato);
        return contatoMapper.toResponseDTO(contato);
    }

    @Transactional(readOnly = true)
    public List<ContatoResponseDTO> listarTodos() {
        return contatoRepository.findAllByOrderByNomeAsc().stream()
                .map(contatoMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ContatoResponseDTO> buscaGlobal(String termo) {
        if (termo == null || termo.isBlank()) {
            return Collections.emptyList();
        }

        String termoLimpo = termo.trim();

        if (termoLimpo.length() < 3) {
            throw new BusinessException(BusinessMessages.TERMO_BUSCA_CURTO);
        }

        return contatoRepository.buscaGlobal(termoLimpo)
                .stream()
                .map(contatoMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ContatoResponseDTO buscarPorId(Long id) {
        Contato contato = buscarEntidadePorId(id);
        return contatoMapper.toResponseDTO(contato);
    }

    @Transactional
    public ContatoResponseDTO atualizar(Long id, ContatoRequestDTO dto) {
        Contato contato = buscarEntidadePorId(id);
        contatoMapper.atualizarContato(dto, contato);
        Contato contatoAtualizado = contatoRepository.save(contato);
        return contatoMapper.toResponseDTO(contatoAtualizado);
    }

    @Transactional
    public void excluir(Long id) {
        buscarEntidadePorId(id);
        contatoRepository.deleteById(id);
    }

    private Contato buscarEntidadePorId(Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(BusinessMessages.CONTATO_NAO_ENCONTRADO));
    }
}
