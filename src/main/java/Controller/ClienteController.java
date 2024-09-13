package Controller;

import Dao.ClienteDAO;
import Dto.DtoCliente;
import Validacoes.CommonValidator;

public class ClienteController {
    private ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO(); // Inicializando o DAO
    }

    // Cadastra um novo cliente
    public String cadastrarCliente(String nome, String email, String telefone, String endereco, String senha) {
        // Validações
        if (!CommonValidator.validarNome(nome) || !CommonValidator.validarEmail(email) ||
            !CommonValidator.validarTelefone(telefone) || !CommonValidator.validarEndereco(endereco) ||
            !CommonValidator.validarSenha(senha)) {
            return "Dados inválidos.";
        }

        DtoCliente novoCliente = new DtoCliente(null, nome, email, telefone, endereco, senha);
        return clienteDAO.inserirCliente(novoCliente);
    }

    // Atualiza um cliente
    public boolean atualizarCliente(String id, String nome, String email, String telefone, String endereco, String senha) {
        if (!CommonValidator.validarNome(nome) || !CommonValidator.validarEmail(email) ||
            !CommonValidator.validarTelefone(telefone) || !CommonValidator.validarEndereco(endereco) ||
            !CommonValidator.validarSenha(senha)) {
            return false;
        }

        DtoCliente clienteAtualizado = new DtoCliente(id, nome, email, telefone, endereco, senha);
        return clienteDAO.atualizarCliente(clienteAtualizado);
    }

    // Remove um cliente por ID
    public void removerCliente(String id) {
        clienteDAO.removerCliente(id);
        System.out.println("Cliente removido com sucesso.");
    }

    // Mostra um cliente por ID
    public DtoCliente mostrarClientePorId(String id) {
        return clienteDAO.mostrarClientePorId(id);
    }

    // Autentica um cliente usando email e senha
    public boolean authenticateCliente(String email, String senha) {
        DtoCliente cliente = clienteDAO.buscarClientePorEmail(email);
        return cliente != null && cliente.getSenha().equals(senha);
    }
}
