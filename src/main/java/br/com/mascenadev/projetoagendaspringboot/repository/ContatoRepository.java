package br.com.mascenadev.projetoagendaspringboot.repository;

import br.com.mascenadev.projetoagendaspringboot.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

    boolean existsByEmail(String email);

    List<Contato> findAllByOrderByNomeAsc();

    @Query("SELECT c FROM Contato c WHERE " +
            "LOWER(c.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(c.email) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
            "LOWER(c.telefone) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Contato> buscaGlobal(String termo);
}
