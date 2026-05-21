package br.com.mascenadev.projetoagendaspringboot.repository;

import br.com.mascenadev.projetoagendaspringboot.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    boolean existsByEmail(String email);
}
