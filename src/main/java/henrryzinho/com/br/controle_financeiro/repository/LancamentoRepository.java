package henrryzinho.com.br.controle_financeiro.repository;

import henrryzinho.com.br.controle_financeiro.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
