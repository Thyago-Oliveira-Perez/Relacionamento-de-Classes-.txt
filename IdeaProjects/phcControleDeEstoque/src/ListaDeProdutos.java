import java.util.ArrayList;
import java.util.List;

public class ListaDeProdutos {

    List<Produto> lista = new ArrayList<Produto>();
    String limpar;

    public ListaDeProdutos() {
    }

    public ListaDeProdutos(List<Produto> lista) {
        this.lista = lista;
    }

    public List<Produto> getLista() {
        return lista;
    }

    public void setLista(List<Produto> lista) {
        this.lista = lista;
    }

    public int size(){

        return lista.size();

    }

    @Override
    public String toString() {
        return "ListaDeProdutos: " + '\n' + lista;
    }

    public void clear() {

        lista.clear();

    }
}
