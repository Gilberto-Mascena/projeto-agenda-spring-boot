package br.com.mascenadev.projetoagendaspringboot.repository;

import br.com.mascenadev.projetoagendaspringboot.entity.Contato;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    boolean existsByEmail(String email);

    Page<Contato> findAll(Pageable pageable);

    @Query("SELECT c FROM Contato c WHERE " +
            "LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(c.telefone) LIKE LOWER(CONCAT('%', :termo, '%'))")
    Page<Contato> buscaGlobal(String termo, Pageable pageable);
}
