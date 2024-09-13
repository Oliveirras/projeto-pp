package PadraoBuiderRelatorio;

import Dto.ServicoDTO;
import java.util.List;

public interface ReportBuilder {
    ReportBuilder setHeader(String header);
    ReportBuilder setContent(List<ServicoDTO> servicos);
    ReportBuilder setFooter(String footer);
    byte[] build(); // M�todo para concluir a constru��o e retornar o resultado
}
