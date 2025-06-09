package br.com.mascenadev.projetoagendaspringboot.repository;

import br.com.mascenadev.projetoagendaspringboot.entities.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório de dados para a entidade {@link Contato}.
 * <p>
 * Esta interface estende {@link org.springframework.data.jpa.repository.JpaRepository}
 * fornecida pelo **Spring Data JPA**. Ela abstrai a complexidade do acesso a dados,
 * permitindo operações de **CRUD (Create, Read, Update, Delete)**, paginação,
 * e ordenação para a entidade {@link Contato} de forma simplificada.
 * </p>
 * <p>
 * O Spring Data JPA cria uma implementação concreta desta interface em tempo de execução,
 * baseando-se nas convenções de nome de métodos para gerar consultas SQL automaticamente.
 * </p>
 *
 * <p>Por padrão, esta interface herda uma vasta gama de métodos para operações comuns, incluindo:</p>
 * <ul>
 * <li>{@code save(S entity)}: Salva uma entidade.</li>
 * <li>{@code findById(ID id)}: Busca uma entidade pelo seu ID, retornando um {@code Optional}.</li>
 * <li>{@code findAll()}: Retorna todas as entidades.</li>
 * <li>{@code deleteById(ID id)}: Exclui uma entidade pelo seu ID.</li>
 * <li>E muitos outros para contagem, existência e operações em lote.</li>
 * </ul>
 *
 * <p>
 * Métodos de consulta personalizados podem ser definidos aqui seguindo as convenções de nome do Spring Data JPA
 * (ex: {@code findByNome(String nome)}) ou utilizando a anotação {@code @Query} para consultas JPQL/Nativas.
 * </p>
 *
 * @author Gilberto Dev
 * @see Contato
 * @see JpaRepository
 * @see org.springframework.stereotype.Repository
 * @since 1.0.0
 */
public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
