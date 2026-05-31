package br.com.mascenadev.projetoagendaspringboot.service;

import br.com.mascenadev.projetoagendaspringboot.dto.ContatoRequestDTO;
import br.com.mascenadev.projetoagendaspringboot.dto.ContatoResponseDTO;
import br.com.mascenadev.projetoagendaspringboot.entity.Contato;
import br.com.mascenadev.projetoagendaspringboot.exception.BusinessException;
import br.com.mascenadev.projetoagendaspringboot.exception.ObjectNotFoundException;
import br.com.mascenadev.projetoagendaspringboot.mapper.ContatoMapper;
import br.com.mascenadev.projetoagendaspringboot.message.BusinessMessages;
import br.com.mascenadev.projetoagendaspringboot.repository.ContatoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<ContatoResponseDTO> listarTodos(Pageable paginacao) {
        return contatoRepository.findAll(paginacao)
                .map(contatoMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public ContatoResponseDTO buscarPorId(Long id) {
        Contato contato = buscarEntidadePorId(id);
        return contatoMapper.toResponseDTO(contato);
    }

    @Transactional(readOnly = true)
    public Page<ContatoResponseDTO> buscaGlobal(String termo, Pageable paginacao) {
        if (termo == null || termo.isBlank()) {
            return Page.empty(paginacao);
        }

        String termoLimpo = termo.trim();

        if (termoLimpo.length() < 3) {
            throw new BusinessException(BusinessMessages.TERMO_BUSCA_CURTO);
        }

        return contatoRepository.buscaGlobal(termoLimpo, paginacao)
                .map(contatoMapper::toResponseDTO);
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
