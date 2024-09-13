package FactoryMetode;

import Model.ServicosDisponiveis;

public class FabricaServicoComum implements FabricaServico {
	
	
	public ServicosDisponiveis criarServico(String nome, double custo) {
        // Pode adicionar lógicas específicas para serviços VIP se necessário
        return new ServicosDisponiveis(null, nome, custo);
    }

}
