package henrryzinho.com.br.controle_financeiro.service;

import henrryzinho.com.br.controle_financeiro.dto.CategoriaResponseDTO;
import henrryzinho.com.br.controle_financeiro.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import henrryzinho.com.br.controle_financeiro.dto.CategoriaRequestDTO;
import henrryzinho.com.br.controle_financeiro.model.Categoria;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {
    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Test
    void deveCriarCategoriaComSucesso() {
        // Arrange: prepara a entrada e configura o comportamento do Repository falso
        CategoriaRequestDTO dto = new CategoriaRequestDTO("Alimentação");
        Categoria categoriaSalva = new Categoria(dto.nome());

        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaSalva);

        //isso é uma variavel = Act: chama o método que está sendo testado
        CategoriaResponseDTO resposta = categoriaService.cadastrar(dto);

        // Assert: verifica se o resultado é o esperado
        assertEquals(dto.nome(), resposta.nome());

        verify(categoriaRepository).save(any(Categoria.class));
    }

    @Test
    void deveListarCategoriaComSucesso() {
        // Arrange: prepara a entrada e configura o comportamento do Repository falso
        Categoria alimentação = new Categoria("Alimentação");
        Categoria transporte = new Categoria("Transporte");

        when(categoriaRepository.findAll()).thenReturn(List.of(alimentação, transporte));

        // Act: chama o método que está sendo testado
        List<CategoriaResponseDTO> resposta = categoriaService.listar();

        // Assert: verifica se o resultado é o esperado
        assertEquals(2, resposta.size());
        assertEquals("Alimentação", resposta.get(0).nome());
        assertEquals("Transporte", resposta.get(1).nome());

        verify(categoriaRepository).findAll();
    }


}
