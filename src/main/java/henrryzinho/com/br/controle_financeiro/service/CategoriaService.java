package henrryzinho.com.br.controle_financeiro.service;

import henrryzinho.com.br.controle_financeiro.dto.CategoriaRequestDTO;
import henrryzinho.com.br.controle_financeiro.dto.CategoriaResponseDTO;
import henrryzinho.com.br.controle_financeiro.model.Categoria;
import henrryzinho.com.br.controle_financeiro.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
