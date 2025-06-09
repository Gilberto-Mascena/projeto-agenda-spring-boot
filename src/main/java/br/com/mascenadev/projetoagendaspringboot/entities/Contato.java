package br.com.mascenadev.projetoagendaspringboot.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa um **contato** na aplicação de agenda.
 * <p>
 * Esta classe é um **POJO (Plain Old Java Object)** que mapeia para uma tabela no banco de dados,
 * atuando como uma **entidade JPA** ({@link jakarta.persistence.Entity}).
 * Contém informações básicas como nome, e-mail e telefone, e é a representação
 * persistente dos dados de um contato na base de dados.
 * </p>
 *
 * @author Gilberto Dev
 * @see br.com.mascenadev.projetoagendaspringboot.repository.ContatoRepository
 * @see br.com.mascenadev.projetoagendaspringboot.service.ContatoService
 * @since 1.0.0
 */
@Entity
public class Contato implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Identificador único do contato (gerado automaticamente).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do contato.
     * Deve conter entre 3 e 100 caracteres.
     * Não pode ser nulo ou em branco.
     */
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    /**
     * Endereço de e-mail do contato.
     * Deve ser um e-mail válido e não pode ser nulo ou em branco.
     */
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    /**
     * Número de telefone do contato.
     * Deve seguir o formato brasileiro com DDD: (XX) XXXXX-XXXX ou (XX) XXXX-XXXX.
     */
    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(
            regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
            message = "Telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX")
    private String telefone;

    /**
     * Construtor padrão necessário para o JPA.
     */
    public Contato() {
    }

    /**
     * Construtor para criar uma nova instância de {@code Contato} com todos os atributos obrigatórios,
     * exceto o ID, que é gerado automaticamente pelo banco de dados.
     *
     * @param nome     O nome completo do contato.
     * @param email    O endereço de e-mail do contato.
     * @param telefone O número de telefone do contato no formato especificado.
     */
    public Contato(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
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
     * Define o ID do contato.
     *
     * @param id Novo ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do contato.
     *
     * @return Nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do contato.
     *
     * @param nome Novo nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o e-mail do contato.
     *
     * @return E-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do contato.
     *
     * @param email Novo e-mail
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o telefone do contato.
     *
     * @return Telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do contato.
     *
     * @param telefone Novo telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Compara se dois contatos são iguais com base no ID.
     *
     * @param o Objeto a ser comparado
     * @return true se os IDs forem iguais, false caso contrário
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id);
    }

    /**
     * Gera um código hash com base no ID.
     *
     * @return hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Representação textual do objeto Contato.
     *
     * @return String contendo os dados do contato
     */
    @Override
    public String toString() {
        return "Contato{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", email='" + email + '\'' +
               ", telefone='" + telefone + '\'' +
               '}';
    }
}
