package br.com.mascenadev.projetoagendaspringboot.dtos;

import br.com.mascenadev.projetoagendaspringboot.entities.Contato;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.BeanUtils;

/**
 * DTO (Data Transfer Object) utilizado para encapsular os **dados de entrada**
 * em requisições HTTP para a criação ou atualização de um {@link Contato}.
 * <p>
 * Este DTO é responsável por receber as informações básicas de um contato (nome, e-mail, telefone)
 * e aplica validações rigorosas (definidas pelas anotações Jakarta Bean Validation)
 * para garantir a integridade dos dados antes que sejam processados pelo serviço.
 * </p>
 *
 * @author Gilberto Dev
 * @see Contato
 * @see br.com.mascenadev.projetoagendaspringboot.controller.ContatoController#salvar(ContatoRequestDTO)
 * @see br.com.mascenadev.projetoagendaspringboot.controller.ContatoController#atualizar(Long, ContatoRequestDTO)
 * @since 1.0.0
 */
public class ContatoRequestDTO {

    /**
     * Nome do contato.
     * Não pode ser nulo ou vazio.
     */
    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
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
    @Pattern(
            regexp = "\\(\\d{2}\\) \\d{4,5}-\\d{4}",
            message = "Telefone deve estar no formato (XX) XXXXX-XXXX ou (XX) XXXX-XXXX")
    private String telefone;

    /**
     * Construtor padrão.
     */
    public ContatoRequestDTO() {
    }

    /**
     * Construtor para criar uma nova instância de {@code ContatoRequestDTO} com os dados básicos.
     *
     * @param nome     O nome completo do contato.
     * @param email    O endereço de e-mail do contato.
     * @param telefone O número de telefone do contato.
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
     * Converte os dados contidos neste DTO para uma nova instância da entidade {@link Contato}.
     * <p>
     * Este método é útil para mapear os dados recebidos da requisição HTTP (no formato DTO)
     * para o formato da entidade que será persistida no banco de dados.
     * Ele utiliza {@link org.springframework.beans.BeanUtils#copyProperties(Object, Object)}
     * para realizar a cópia das propriedades correspondentes entre os objetos.
     * </p>
     *
     * @return Uma nova instância de {@link Contato} preenchida com os dados deste {@code ContatoRequestDTO}.
     */
    public Contato toEntity() {
        Contato contato = new Contato();
        BeanUtils.copyProperties(this, contato);
        return contato;
    }
}
