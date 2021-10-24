import java.util.ArrayList;
import java.util.List;

public class ListaDeCategorias{

    List<String> lista = new ArrayList<String>();

    public ListaDeCategorias() {
    }

    public ListaDeCategorias(List<String> lista) {
        this.lista = lista;
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
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
