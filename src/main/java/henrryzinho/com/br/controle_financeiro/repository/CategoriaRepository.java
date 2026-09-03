package henrryzinho.com.br.controle_financeiro.repository;

import henrryzinho.com.br.controle_financeiro.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
