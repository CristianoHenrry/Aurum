package henrryzinho.com.br.controle_financeiro.service;

import henrryzinho.com.br.controle_financeiro.dto.LancamentoRequestDTO;
import henrryzinho.com.br.controle_financeiro.model.Categoria;
import henrryzinho.com.br.controle_financeiro.model.Lancamento;
import henrryzinho.com.br.controle_financeiro.model.TipoLancamento;
import henrryzinho.com.br.controle_financeiro.repository.CategoriaRepository;
import henrryzinho.com.br.controle_financeiro.repository.LancamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LancamentoServiceTest {
    @Mock
    private LancamentoRepository lancamentoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private LancamentoService lancamentoService;

    @Test
    void deveCadastrarLancamentoComSucesso() {
        Long categoriaId = 1L;
        Categoria alimento = new Categoria("Alimentação");
        ReflectionTestUtils.setField(alimento, "id", categoriaId);
        LancamentoRequestDTO dto = new LancamentoRequestDTO("Compra no mercado", new BigDecimal("150.00"), LocalDate.of(2026, 11, 14), TipoLancamento.DESPESA, categoriaId);

        when(categoriaRepository.findById(categoriaId)).thenReturn(Optional.of(alimento));

        Lancamento lancamentoSalvo = new Lancamento(dto.descricao(), dto.valor(), dto.data(), dto.tipo(), alimento);
        ReflectionTestUtils.setField(lancamentoSalvo, "id", 10L);

        when(lancamentoRepository.save(any(Lancamento.class))).thenReturn(lancamentoSalvo);
    }

}
