import java.util.ArrayList;
import java.util.List;

public class ListaDeProdutos {

    List<Produto> lista = new ArrayList<Produto>();

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

    @Override
    public String toString() {
        return "ListaDeProdutos{" +
                "lista=" + lista +
                '}';
    }
}
