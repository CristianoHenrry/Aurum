package henrryzinho.com.br.controle_financeiro.exception;

public class CategoriaNaoEncontradaException extends RuntimeException{

    public CategoriaNaoEncontradaException(Long id) {
        super("Categoria não encontrada com o ID: " + id);
    }


}
