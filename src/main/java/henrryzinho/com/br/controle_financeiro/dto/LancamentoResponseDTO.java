package henrryzinho.com.br.controle_financeiro.dto;

import henrryzinho.com.br.controle_financeiro.model.TipoLancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoResponseDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        LocalDate data,
        TipoLancamento tipo,
        CategoriaResponseDTO categoria
) {
}
