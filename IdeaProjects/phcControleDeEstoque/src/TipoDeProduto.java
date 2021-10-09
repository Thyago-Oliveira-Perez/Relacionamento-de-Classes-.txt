public class TipoDeProduto{

    private String tipo;

    public TipoDeProduto() {
    }

    public TipoDeProduto(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "TipoDeProduto{" +
                "tipo='" + tipo + '\'' +
                '}';
    }
}