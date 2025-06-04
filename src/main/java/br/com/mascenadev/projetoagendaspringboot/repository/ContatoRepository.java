package br.com.mascenadev.projetoagendaspringboot.repository;

import br.com.mascenadev.projetoagendaspringboot.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para a entidade {@link Contato}.
 * <p>
 * Estende {@link JpaRepository} para fornecer operações CRUD, paginação e outras
 * funcionalidades de acesso a dados para a entidade {@code Contato}.
 * </p>
 *
 * <p>Por padrão, herda métodos como:
 * <ul>
 *     <li>{@code save}</li>
 *     <li>{@code findById}</li>
 *     <li>{@code findAll}</li>
 *     <li>{@code deleteById}</li>
 *     <li>entre outros</li>
 * </ul>
 * </p>
 *
 * <p>Você pode adicionar aqui métodos personalizados de consulta se necessário.</p>
 *
 * @author Gilberto Dev
 * @see Contato
 * @see JpaRepository
 */
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
