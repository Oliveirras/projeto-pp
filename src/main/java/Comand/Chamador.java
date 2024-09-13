package Comand;

//Invoker - Chamador para executar os comandos
public class Chamador {
 private Comando comando;

 public void setComando(Comando comando) {
     this.comando = comando;
 }

 public void executarComando() {
     if (comando != null) {
         comando.executar();
     }
 }
}
