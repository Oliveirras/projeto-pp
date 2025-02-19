package Model;

public class Cliente  {
    private String id;
    private String nome;
    private String email;
    private String telefone;

    public Cliente(String id, String nome, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    
    public Cliente(String nome) {
        this.nome = nome;
    }

    
    public void atualizar(String mensagem) {
        System.out.println("Notificação para " + nome + ": " + mensagem);
        // Aqui você pode exibir a notificação na interface do cliente (JOptionPane, por exemplo)
    }
}
