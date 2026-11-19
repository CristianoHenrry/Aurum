package henrryzinho.com.br.controle_financeiro.controller;

import henrryzinho.com.br.controle_financeiro.service.LancamentoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/lancamentos")
public class LancamentoController {
    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }


}
