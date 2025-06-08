package br.com.mascenadev.projetoagendaspringboot.dtos;

import br.com.mascenadev.projetoagendaspringboot.entities.Contato;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;

/**
 * DTO utilizado para receber dados de requisições relacionadas à entidade {@link Contato}.
 * <p>
 * Contém validações para garantir que os campos obrigatórios sejam informados e estejam em formato válido.
 * </p>
 *
 * @author Gilberto Dev
 */
public class ContatoRequestDTO {

    /**
     * Nome do contato.
     * Não pode ser nulo ou vazio.
     */
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    /**
     * Email do contato.
     * Não pode ser nulo ou vazio e deve estar em um formato válido.
     */
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    /**
     * Número de telefone do contato.
     * Não pode ser nulo ou vazio.
     */
    @NotBlank(message = "Telefone é obrigatório")
    private String telefone;

    /**
     * Construtor padrão.
     */
    public ContatoRequestDTO() {
    }

    /**
     * Construtor com todos os campos.
     *
     * @param nome     o nome do contato
     * @param email    o email do contato
     * @param telefone o telefone do contato
     */
    public ContatoRequestDTO(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    /**
     * Retorna o nome do contato.
     *
     * @return nome do contato
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do contato.
     *
     * @param nome nome do contato
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o email do contato.
     *
     * @return email do contato
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do contato.
     *
     * @param email email do contato
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retorna o telefone do contato.
     *
     * @return telefone do contato
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do contato.
     *
     * @param telefone telefone do contato
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Converte este DTO em uma entidade {@link Contato}.
     * Utiliza {@link BeanUtils#copyProperties(Object, Object)} para copiar as propriedades.
     *
     * @return instância da entidade {@link Contato} com os dados deste DTO
     */
    public Contato toEntity() {
        Contato contato = new Contato();
        BeanUtils.copyProperties(this, contato);
        return contato;
    }
}
