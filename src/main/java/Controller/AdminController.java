package Controller;

import Dao.AdminDao;
import Dto.DtoAdministrador;
import Model.Admin;
import Validacoes.CommonValidator;

public class AdminController {
    private AdminDao adminDao;

    public AdminController() {
        adminDao = new AdminDao();
    }

    // Verifica se já existe um admin registrado
    public boolean isAdminRegistered() {
        return adminDao.findAdmin() != null;
    }

    // Registra um novo admin
    public boolean registerAdmin(Admin admin) {
        // Valida o email e senha antes de continuar
        if (!CommonValidator.validarEmail(admin.getEmail())) {
            return false; // Email inválido
        }
        if (!CommonValidator.validarSenha(admin.getSenha())) {
            return false; // Senha inválida
        }

        if (isAdminRegistered()) {
            return false; // Admin já cadastrado
        }

        adminDao.saveAdmin(admin);
        return true;
    }

    // Autentica um admin usando email e senha
    public boolean authenticateAdmin(String email, String senha) {
        Admin admin = adminDao.findAdmin(); // Isso deve ser ajustado se precisar encontrar um admin específico
        return admin != null && admin.getEmail().equals(email) && admin.getSenha().equals(senha);
    }

    // Cria um admin a partir de um DTO e tenta registrá-lo
    public Admin createAdmin(DtoAdministrador dtoAdmin) {
        Admin admin = new Admin(dtoAdmin.getNome(), dtoAdmin.getEmail(), dtoAdmin.getSenha());
        
        if (registerAdmin(admin)) {
            return admin; // Admin registrado com sucesso
        } else {
            return null; // Falha no registro
        }
    }
}
