package Validacoes;

import Dto.ServicoDTO;
import java.util.regex.Pattern;

public class CommonValidator {
	
	  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

	    // Valida o formato do email
	    public static boolean validarEmail(String email) {
	        if (email == null || email.isEmpty()) {
	            return false;
	        }
	        return Pattern.matches(EMAIL_REGEX, email);
	    }

	    // Exemplo de validação de senha (ajuste conforme necessário)
	    public static boolean validarSenha(String senha) {
	        // A senha deve ter pelo menos 6 caracteres
	        return senha != null && senha.length() >= 6;
	    }

	    // Exemplo de validação de nome (ajuste conforme necessário)
	    public static boolean validarNome(String nome) {
	        // O nome deve ter pelo menos 3 caracteres
	        return nome != null && nome.length() >= 3;
	    }


   

    // Valida o telefone: deve ter 10 ou 11 dígitos
    public static boolean validarTelefone(String telefone) {
        String telefoneRegex = "\\d{10,11}";
        return telefone != null && Pattern.matches(telefoneRegex, telefone);
    }

    // Valida o endereço: não pode ser nulo ou vazio
    public static boolean validarEndereco(String endereco) {
        return endereco != null && !endereco.trim().isEmpty();
    }

   

    // Valida o nome do serviço: não pode ser nulo ou vazio
    public static boolean validarNomeServico(ServicoDTO servicoDTO) {
        String nomeServico = servicoDTO.getTipoServico();
        return nomeServico != null && !nomeServico.trim().isEmpty();
    }

    // Valida o nome do cliente no serviço
    public static boolean validarNomeCliente(ServicoDTO servicoDTO) {
        String nomeCliente = servicoDTO.getNomeCliente();
        return nomeCliente != null && !nomeCliente.trim().isEmpty();
    }
    
    
}
