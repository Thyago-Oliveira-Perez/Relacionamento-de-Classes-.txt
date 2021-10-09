public class Produto{

    private long id;
    private String nome;
    private String numSerial;
    private TipoDeProduto tipo;
    private int quantidade;
    private String estadoConservacao;

    public Produto() {
    }

    public Produto(long id, String nome, String numSerial, TipoDeProduto tipo, int quantidade, String estadoConservacao) {
        this.id = id;
        this.nome = nome;
        this.numSerial = numSerial;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.estadoConservacao = estadoConservacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumSerial() {
        return numSerial;
    }

    public void setNumSerial(String numSerial) {
        this.numSerial = numSerial;
    }

    public TipoDeProduto getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeProduto tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(String estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", numSerial='" + numSerial + '\'' +
                ", tipo=" + tipo +
                ", quantidade=" + quantidade +
                ", estadoConservacao='" + estadoConservacao + '\'' +
                '}';
    }
}