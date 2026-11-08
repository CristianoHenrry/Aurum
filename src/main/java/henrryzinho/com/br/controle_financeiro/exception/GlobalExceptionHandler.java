package henrryzinho.com.br.controle_financeiro.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//reponseEntity é uma classe do Spring que representa a resposta HTTP completa

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler
    public ResponseEntity<String> tratarCategoriaNaoEncontradaException(CategoriaNaoEncontradaException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());

    }

    }

