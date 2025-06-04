package br.com.mascenadev.projetoagendaspringboot.service;

import br.com.mascenadev.projetoagendaspringboot.entities.Contato;
import br.com.mascenadev.projetoagendaspringboot.exception.ContatoNaoEncontradoException;
import br.com.mascenadev.projetoagendaspringboot.repository.ContatoRepository;

import java.util.List;
import java.util.Optional;

public class ContatoService {

    private final ContatoRepository contatoRepository;

    public ContatoService(ContatoRepository contatoRepository) {
        this.contatoRepository = contatoRepository;
    }

    public Contato salvar(Contato contato) {
        return contatoRepository.save(contato);
    }

    public Optional<Contato> buscarPorId(Long id) {
        return contatoRepository.findById(id);
    }

    public List<Contato> buscarTodos() {
        return contatoRepository.findAll();
    }

    public void excluir(Long id) {
        if (!contatoRepository.existsById(id)) {
            throw new ContatoNaoEncontradoException(id);
        }
        contatoRepository.deleteById(id);
    }

    public Contato atualizar(Long id, Contato contatoAtualizado) {
        return contatoRepository.findById(id)
                .map(contato -> {
                    contato.setNome(contatoAtualizado.getNome());
                    contato.setEmail(contatoAtualizado.getEmail());
                    contato.setTelefone(contatoAtualizado.getTelefone());
                    return contatoRepository.save(contato);
                })
                .orElseThrow(() -> new ContatoNaoEncontradoException(id));
    }
}
