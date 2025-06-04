package br.com.mascenadev.projetoagendaspringboot.exception;

import java.time.LocalDateTime;

/**
 * Representa a estrutura padrão para respostas de erro retornadas pela API.
 * <p>
 * Essa classe é usada para encapsular informações detalhadas sobre erros
 * ocorridos durante a execução das requisições, fornecendo dados úteis
 * para depuração e compreensão do problema pelo cliente.
 * </p>
 *
 * @param timestamp o momento em que o erro ocorreu
 * @param status    o código de status HTTP associado ao erro
 * @param error     uma mensagem descritiva do tipo de erro
 * @param details   informações adicionais relacionadas ao erro (como mensagens de validação ou exceções)
 * @author Gilberto Dev
 */
public record ErroResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        Object details
) {
}

