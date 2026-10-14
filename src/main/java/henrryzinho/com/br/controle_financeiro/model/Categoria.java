package henrryzinho.com.br.controle_financeiro.model;

import jakarta.persistence.*;


// A classe Categoria representa uma categoria de transações financeiras no sistema de controle financeiro.
// Cada categoria possui um identificador único (id) e um nome que descreve a categoria. A anotação @Entity indica que esta classe é uma entidade JPA,
// e a anotação @Table especifica o nome da tabela correspondente no banco de dados.
@Table(name = "categorias")
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false, unique = true, length = 100)
    private String nome;

    protected Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}