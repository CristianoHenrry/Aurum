package henrryzinho.com.br.controle_financeiro.service;

import henrryzinho.com.br.controle_financeiro.dto.CategoriaRequestDTO;
import henrryzinho.com.br.controle_financeiro.dto.CategoriaResponseDTO;
import henrryzinho.com.br.controle_financeiro.model.Categoria;
import henrryzinho.com.br.controle_financeiro.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import henrryzinho.com.br.controle_financeiro.exception.CategoriaNaoEncontradaException;
import java.util.List;


@Service
public class CategoriaService {
    // Injeção de dependência do repositório de categorias
    private final CategoriaRepository categoriaRepository;

    // Construtor para injetar a dependência do repositório de categorias
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // esse bloco de codigo é responsável por cadastrar uma nova categoria no sistema. Ele recebe um DTO contendo os dados da categoria a ser cadastrada,
    // cria uma nova entidade Categoria com base nesses dados, salva a entidade no repositório e retorna um DTO de resposta contendo o ID e o nome da categoria cadastrada.
    @Transactional
    public CategoriaResponseDTO cadastrar(CategoriaRequestDTO dto) {
        Categoria categoria = new Categoria(dto.nome()); // Cria uma entidade Categoria com o nome recebido no DTO
        Categoria categoriaSalva = categoriaRepository.save(categoria); //persiste a categoria no banco de dados e retorna a entidade salva
        return new CategoriaResponseDTO(categoriaSalva.getId(), categoriaSalva.getNome()); // Retorna o DTO de resposta com o ID e nome da categoria
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listar() {
        return categoriaRepository.findAll().stream()
                .map(categoria -> new CategoriaResponseDTO(categoria.getId(), categoria.getNome()))
                .toList();
    }

    // esse bloco de código é responsável por buscar uma categoria pelo seu ID. Ele recebe o ID da categoria como parâmetro,
    // consulta o repositório para encontrar a entidade correspondente e, se encontrada, retorna um DTO de resposta contendo o ID e o nome da categoria. Caso a categoria não seja encontrada, lança uma exceção CategoriaNaoEncontradaException.
   @Transactional(readOnly = true)
    public CategoriaResponseDTO buscarPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id)) ;
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }
    @Transactional
    public CategoriaResponseDTO atualizar(Long id, CategoriaRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));
        categoria.setNome(dto.nome());
        Categoria categoriaAtualizada = categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(categoriaAtualizada.getId(), categoriaAtualizada.getNome());
    }
    @Transactional
    public void excluir(Long id){
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaNaoEncontradaException(id));
        categoriaRepository.delete(categoria);
    }




}
