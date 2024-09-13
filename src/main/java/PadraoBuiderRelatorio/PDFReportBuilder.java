package PadraoBuiderRelatorio;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import Dto.ServicoDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFReportBuilder implements ReportBuilder {
    private Document document;
    private ByteArrayOutputStream pdfContent;

    public PDFReportBuilder() {
        document = new Document();
        pdfContent = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, pdfContent);
            document.open();  // Abre o documento aqui, no construtor
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PDFReportBuilder setHeader(String header) {
        try {
            document.add(new Paragraph(header));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public PDFReportBuilder setContent(List<ServicoDTO> servicos) {
        try {
            for (ServicoDTO servico : servicos) {
                document.add(new Paragraph("Cliente: " + servico.getNomeCliente()));
                document.add(new Paragraph("E-mail: " + servico.getEmail()));
                document.add(new Paragraph("Tipo de Serviço: " + servico.getTipoServico()));
                document.add(new Paragraph("Tipo: " + (servico.isCaro() ? "Caro" : "Barato")));
                document.add(new Paragraph("Horário: " + servico.getHorario()));
                document.add(new Paragraph("Ordem: " + servico.getOrdem()));
                document.add(new Paragraph("-----------"));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public PDFReportBuilder setFooter(String footer) {
        try {
            document.add(new Paragraph(footer));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public byte[] build() {
        document.close(); // Fecha o documento ao finalizar a construção
        return pdfContent.toByteArray();
    }
}
