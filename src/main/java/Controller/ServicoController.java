package Controller;

// Controller/ServicoController.java
import Dao.ServicosDAO;
import Dto.ServicosDisponiveisDto;
import FactoryMetode.FabricaServico;
import FactoryMetode.FabricaServicoComum;
import FactoryMetode.FabricaServicoVIP;

public class ServicoController {
    private ServicosDAO servicoDAO;

    public ServicoController() {
        servicoDAO = new ServicosDAO();
    }

    public void adicionarServico(ServicosDisponiveisDto servicoDTO, String tipo) {
        FabricaServico fabrica;
        switch (tipo.toLowerCase()) {
            case "vip":
                fabrica = new FabricaServicoVIP();
                break;
            case "comum":
                fabrica = new FabricaServicoComum();
                break;
            default:
                throw new IllegalArgumentException("Tipo de serviço desconhecido");
        }
        servicoDTO.setTipo(tipo); // Definir o tipo no DTO
        servicoDAO.adicionarServico(fabrica, servicoDTO);
    }
}
