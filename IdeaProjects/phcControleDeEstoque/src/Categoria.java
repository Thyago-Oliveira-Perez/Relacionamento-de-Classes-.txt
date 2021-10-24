import java.util.ArrayList;
import java.util.List;

public class Categoria {

    private String nome;
    List<String> listaDeProdutos = new ArrayList<>();

    public Categoria() {
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(List<String> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }

    public Categoria(String nome, List<String> listaDeProdutos) {
        this.nome = nome;
        this.listaDeProdutos = listaDeProdutos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public void setListaDeProdutos(List<String> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }

    public String mostrar(){

        return nome;

    }

    @Override
    public String toString() {
        return "Categoria{" +
                "nome='" + nome + '\'' +
                ", listaDeProdutos=" + listaDeProdutos +
                '}';
    }
}
