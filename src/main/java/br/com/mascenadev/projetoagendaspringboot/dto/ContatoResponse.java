package br.com.mascenadev.projetoagendaspringboot.dto;

import br.com.mascenadev.projetoagendaspringboot.entities.Contato;

import java.util.Objects;

public class ContatoResponse {

    private Long id;
    private final String nome;
    private final String email;
    private final String telefone;

    public ContatoResponse(Contato entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.telefone = entity.getTelefone();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContatoResponse that = (ContatoResponse) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ContatoResponse{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", email='" + email + '\'' +
               ", telefone='" + telefone + '\'' +
               '}';
    }
}
