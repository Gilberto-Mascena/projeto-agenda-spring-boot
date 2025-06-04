package br.com.mascenadev.projetoagendaspringboot.dto;

import br.com.mascenadev.projetoagendaspringboot.entities.Contato;

import java.util.Objects;

/**
 * DTO de resposta que representa os dados de um contato retornados pela API.
 * <p>
 * Essa classe encapsula os dados expostos da entidade {@link Contato} ao cliente,
 * ocultando qualquer dado interno que não deve ser transferido diretamente.
 *
 * @author Gilberto Dev
 */

public class ContatoResponse {

    /**
     * Identificador único do contato.
     */
    private final Long id;

    /**
     * Nome do contato.
     */
    private final String nome;

    /**
     * Endereço de e-mail do contato.
     */
    private final String email;

    /**
     * Número de telefone do contato.
     */

    private final String telefone;

    /**
     * Construtor que popula o DTO a partir da entidade {@link Contato}.
     *
     * @param entity Entidade Contato usada como base
     */
    public ContatoResponse(Contato entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();
        this.telefone = entity.getTelefone();
    }

    /**
     * Obtém o ID do contato.
     *
     * @return ID do contato
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtém o nome do contato.
     *
     * @return nome do contato
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtém o e-mail do contato.
     *
     * @return e-mail do contato
     */
    public String getEmail() {
        return email;
    }

    /**
     * Obtém o telefone do contato.
     *
     * @return telefone do contato
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Compara este objeto com outro para verificar se são iguais, com base no ID.
     *
     * @param o Objeto a ser comparado
     * @return true se os objetos tiverem o mesmo ID, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ContatoResponse that = (ContatoResponse) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Gera o hash code com base no ID do contato.
     *
     * @return código hash
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Retorna uma representação textual do DTO, útil para logs e debug.
     *
     * @return String com os dados do contato
     */
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
