package Model;

public class ServicosDisponiveis {
    private String id;
    private String nome;
    private double custo;

    // Construtores
    public ServicosDisponiveis() {}

    public ServicosDisponiveis(String id, String nome, double custo) {
        this.id = id;
        this.nome = nome;
        this.custo = custo;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getCusto() { return custo; }
    public void setCusto(double custo) { this.custo = custo; }
}
