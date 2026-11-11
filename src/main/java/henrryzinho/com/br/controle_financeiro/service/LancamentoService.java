package henrryzinho.com.br.controle_financeiro.service;


import henrryzinho.com.br.controle_financeiro.dto.LancamentoRequestDTO;
import henrryzinho.com.br.controle_financeiro.dto.LancamentoResponseDTO;
import henrryzinho.com.br.controle_financeiro.exception.CategoriaNaoEncontradaException;
import henrryzinho.com.br.controle_financeiro.model.Categoria;
import henrryzinho.com.br.controle_financeiro.repository.CategoriaRepository;
import henrryzinho.com.br.controle_financeiro.repository.LancamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LancamentoService {
    private final LancamentoRepository lancamentoRepository;
    private final CategoriaRepository categoriaRepository;

    public LancamentoService(LancamentoRepository lancamentoRepository, CategoriaRepository categoriaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.categoriaRepository = categoriaRepository;

    }
    @Transactional
    public LancamentoResponseDTO cadastrar(LancamentoRequestDTO dto){
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() ->new CategoriaNaoEncontradaException(dto.categoriaId()));
        return null;
    }
}
