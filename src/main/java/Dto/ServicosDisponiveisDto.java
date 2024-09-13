package Dto;

//Dto/ServicosDisponiveisDto.java
public class ServicosDisponiveisDto {
 private String nome;
 private double custo;
 private String tipo; // Adicionado para armazenar o tipo de serviço

 public ServicosDisponiveisDto() {}

 public ServicosDisponiveisDto(String nome, double custo, String tipo) {
     this.nome = nome;
     this.custo = custo;
     this.tipo = tipo;
 }

 public String getNome() { return nome; }
 public void setNome(String nome) { this.nome = nome; }

 public double getCusto() { return custo; }
 public void setCusto(double custo) { this.custo = custo; }

 public String getTipo() { return tipo; }
 public void setTipo(String tipo) { this.tipo = tipo; }
}
