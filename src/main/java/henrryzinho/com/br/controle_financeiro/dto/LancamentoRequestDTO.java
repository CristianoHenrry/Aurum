package henrryzinho.com.br.controle_financeiro.dto;

import henrryzinho.com.br.controle_financeiro.model.TipoLancamento;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LancamentoRequestDTO(
        @NotBlank(message = "Descreva seu lançamento")
        @Size(max = 255, message = "Escreva pouco mano")
        String descricao,

        @NotNull
        @Positive
        @Digits(integer = 13, fraction = 2)
        BigDecimal valor,

        @NotNull
        LocalDate data,

        @NotNull
        TipoLancamento tipo,

        @NotNull
        @Positive
        Long categoriaId


        ) {

}
