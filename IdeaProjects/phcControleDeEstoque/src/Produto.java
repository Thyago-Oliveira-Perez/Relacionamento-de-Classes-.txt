public class Produto{

    private String id;
    private String nome;
    private String numSerial;
    private String tipo;
    private String quantidade;
    private String estadoConservacao;
    private int IdCategoria;

    public Produto() {
    }

    public Produto(String id, String nome, String numSerial, String tipo, String quantidade, String estadoConservacao) {
        this.id = id;
        this.nome = nome;
        this.numSerial = numSerial;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.estadoConservacao = estadoConservacao;
    }

    public Produto(String id, String nome, String numSerial, String tipo, String quantidade, String estadoConservacao, int idCategoria) {
        this.id = id;
        this.nome = nome;
        this.numSerial = numSerial;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.estadoConservacao = estadoConservacao;
        this.IdCategoria = idCategoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(String estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        IdCategoria = idCategoria;
    }

    public String Mostrar(){

        return "id: " + id + " | " + "Nome: " + nome + " | " + "N° Serial: " + numSerial + " | " + "Tipo: " + tipo + " | " + "Quant.: " + quantidade + " | " + "Conservação: " + estadoConservacao + " | " + "Id Categoria: " + IdCategoria;

    }

    @Override
    public String toString() {
        return  id  + ";" + nome + ";" + numSerial + ";" + tipo + ";" + quantidade + ";" + estadoConservacao + ";" + IdCategoria;
    }
}