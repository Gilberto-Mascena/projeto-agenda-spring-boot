package br.com.mascenadev.projetoagendaspringboot.dtos;

import br.com.mascenadev.projetoagendaspringboot.entities.Contato;

import java.util.Objects;

/**
 * DTO (Data Transfer Object) utilizado para encapsular os **dados de saída**
 * em respostas HTTP da API, representando as informações de um {@link Contato}.
 * <p>
 * Este DTO serve como um contrato de dados para o cliente, expondo apenas as
 * informações relevantes da entidade {@link Contato} e garantindo que dados
 * internos ou sensíveis não sejam transferidos desnecessariamente.
 * É usado para retornar contatos em operações de busca e após a criação/atualização bem-sucedida.
 * </p>
 *
 * @author Gilberto Dev
 * @see Contato
 * @see br.com.mascenadev.projetoagendaspringboot.controller.ContatoController
 * @since 1.0.0
 */
public class ContatoResponseDTO {

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
     * Construtor para criar uma nova instância de {@code ContatoResponseDTO} a partir de uma entidade {@link Contato}.
     * <p>
     * Este construtor realiza o mapeamento das propriedades da entidade para os campos do DTO,
     * facilitando a conversão de dados do modelo de domínio para o formato de resposta da API.
     * </p>
     *
     * @param entity A entidade {@link Contato} cujos dados serão usados para popular o DTO.
     */
    public ContatoResponseDTO(Contato entity) {
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
        ContatoResponseDTO that = (ContatoResponseDTO) o;
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
