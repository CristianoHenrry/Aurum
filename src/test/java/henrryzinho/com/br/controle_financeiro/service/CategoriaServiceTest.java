package henrryzinho.com.br.controle_financeiro.service;

import henrryzinho.com.br.controle_financeiro.dto.CategoriaRequestDTO;
import henrryzinho.com.br.controle_financeiro.dto.CategoriaResponseDTO;
import henrryzinho.com.br.controle_financeiro.exception.CategoriaNaoEncontradaException;
import henrryzinho.com.br.controle_financeiro.model.Categoria;
import henrryzinho.com.br.controle_financeiro.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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
        Categoria alimentacao = new Categoria("Alimentação");
        Categoria transporte = new Categoria("Transporte");

        when(categoriaRepository.findAll()).thenReturn(List.of(alimentacao, transporte));

        // Act: chama o método que está sendo testado
        List<CategoriaResponseDTO> resposta = categoriaService.listar();

        // Assert: verifica se o resultado é o esperado
        assertEquals(2, resposta.size());
        assertEquals("Alimentação", resposta.get(0).nome());
        assertEquals("Transporte", resposta.get(1).nome());

        verify(categoriaRepository).findAll();
    }

    @Test
    void deveBuscarCategoriaPorIdComSucesso() {
        //arrange: prepara a entrada e configura o comportamento do Repository falso
        Long id = 1L;
        Categoria comida = new Categoria("Alimentação");
        ReflectionTestUtils.setField(comida, "id", id); // Define o ID da categoria comida

        when(categoriaRepository.findById(id)).thenReturn(java.util.Optional.of(comida)); // Simula que a categoria FOI encontrada

        //Act: chama o método que está sendo testado
        CategoriaResponseDTO resposta = categoriaService.buscarPorId(id);

        //Assert: verifica se o resultado é o esperado
        assertEquals("Alimentação", resposta.nome());
        verify(categoriaRepository).findById(id);
        assertEquals(id, resposta.id());
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoEncontrada() {
        Long id = 999L;

        when(categoriaRepository.findById(id))
                .thenReturn(java.util.Optional.empty()); // Simula que a categoria NÃO foi encontrada
        assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.buscarPorId(id));
    }

    @Test
    void deveAtualizarCategoriaComSucesso() {
        Long id = 1L;
        Categoria categoriaExistente = new Categoria("Alimentação");
        ReflectionTestUtils.setField(categoriaExistente, "id", id);
        CategoriaRequestDTO dto = new CategoriaRequestDTO("Transporte");

        when(categoriaRepository.findById(id)).thenReturn(java.util.Optional.of(categoriaExistente));
        when(categoriaRepository.save(any(Categoria.class))).thenReturn(categoriaExistente);

        //Act
        CategoriaResponseDTO resposta = categoriaService.atualizar(id, dto);

        //Assert
        assertEquals(id, resposta.id());
        assertEquals(dto.nome(), resposta.nome());

        verify(categoriaRepository).findById(id);
        verify(categoriaRepository).save(categoriaExistente);
    }

    @Test
    void deveLancarExcecaoAoAtualizarCategoriaInexistente() {
        Long id = 555L;
        CategoriaRequestDTO inexistente = new CategoriaRequestDTO("transporte");
        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.atualizar(id, inexistente));
        verify(categoriaRepository, never()).save(any(Categoria.class));
    }

    @Test
    void deveExcluirCategoriaComSucesso() {
        Long id = 1L;
        Categoria categoriaExistente = new Categoria("Outros");
        when(categoriaRepository.findById(id)).thenReturn(Optional.of(categoriaExistente));

        //Act
        categoriaService.excluir(id);

        verify(categoriaRepository).findById(id);
        verify(categoriaRepository).delete(categoriaExistente);
    }
    @Test
    void  deveLancarExcecaoAoExcluirCategoriaInexistente(){
        Long id = 3L;
        when(categoriaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CategoriaNaoEncontradaException.class, () -> categoriaService.excluir(id));
        verify(categoriaRepository, never()).delete(any(Categoria.class));
    }
}
