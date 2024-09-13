package FactoryMetode;

import Model.ServicosDisponiveis;

public class FabricaServicoVIP implements FabricaServico {
    @Override
    public ServicosDisponiveis criarServico(String nome, double custo) {
        // Pode adicionar lógicas específicas para serviços VIP se necessário
        return new ServicosDisponiveis(null, nome, custo);
    }
}

