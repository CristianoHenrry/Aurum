package henrryzinho.com.br.controle_financeiro.controller;


import henrryzinho.com.br.controle_financeiro.dto.CategoriaRequestDTO;
import henrryzinho.com.br.controle_financeiro.dto.CategoriaResponseDTO;
import henrryzinho.com.br.controle_financeiro.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDTO cadastrar(@RequestBody @Valid CategoriaRequestDTO dto) {
        return categoriaService.cadastrar(dto);
    }

    @GetMapping()
    public List<CategoriaResponseDTO> listar() {
        return categoriaService.listar();
    }

}
