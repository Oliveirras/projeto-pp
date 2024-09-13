package FactoryMetode;

import Model.ServicosDisponiveis;

public interface FabricaServico {
    ServicosDisponiveis criarServico(String nome, double custo);
}