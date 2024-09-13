package PadraoBuiderRelatorio;

import Dto.ServicoDTO;
import java.util.List;

public class ReportBuilderDirector {
    private ReportBuilder builder;

    public ReportBuilderDirector(ReportBuilder builder) {
        this.builder = builder;
    }

    public byte[] constructReport(String header, List<ServicoDTO> servicos, String footer) {
        return builder.setHeader(header)
                      .setContent(servicos)
                      .setFooter(footer)
                      .build(); // Finaliza e retorna o relatório
    }
}
