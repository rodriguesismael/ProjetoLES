/*
 * Classe GerarXLSRelatorioMensal
 */
package negocio.movimento;

import dao.movimentoDAO.MovimentoDAO;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.movimento.Movimento;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class GerarXLSRelatorioMensal extends HttpServlet {

    private List<Movimento> movimentos = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Disposition", "attachment; filename=\"Relatorio Mensal.xls\"");
        response.setContentType("application/vnd.ms-excel");
        //Geração do Excel - INICIO
        int linhaTabela = 0; //contador das linhas das tabelas
        HSSFWorkbook wb = new HSSFWorkbook();
        //criação da planilha
        HSSFSheet planilha = wb.createSheet("Planilha Um");
        //criação da região expandida onde vai o título (mesclar células)
        Region r = new Region(0, (short) 0, 0, (short) 7);
        planilha.addMergedRegion(r);
        //estilo para o TÍTULO da tabela
        HSSFCellStyle estiloTitulo = wb.createCellStyle();
        HSSFFont fonteTitulo = wb.createFont();
        fonteTitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fonteTitulo.setFontHeight((short) 500);
        estiloTitulo.setFont(fonteTitulo);
        estiloTitulo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //estilo para o SUBTÍTULOS da tabela
        HSSFCellStyle estiloSubTitulo = wb.createCellStyle();
        HSSFFont fonteSubtitulo = wb.createFont();
        fonteSubtitulo.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        fonteSubtitulo.setFontHeight((short) 300);
        estiloSubTitulo.setFont(fonteSubtitulo);
        estiloSubTitulo.setWrapText(true);
        estiloSubTitulo.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //estilo Negrito para NOME DAS COLUNAS
        HSSFCellStyle estiloNegrito = wb.createCellStyle();
        HSSFFont fonteNegrito = wb.createFont();
        fonteNegrito.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        estiloNegrito.setFont(fonteNegrito);
        estiloNegrito.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //estilo cores alternadas para LINHAS da tabela
        HSSFPalette palette = wb.getCustomPalette();
        //linha0
        palette.setColorAtIndex((short) 41, (byte) 230, (byte) 230, (byte) 250);
        HSSFCellStyle linha0 = wb.createCellStyle();
        linha0.setFillForegroundColor(palette.getColor(41).getIndex());
        linha0.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //linha1
        palette.setColorAtIndex((short) 42, (byte) 238, (byte) 238, (byte) 238);
        HSSFCellStyle linha1 = wb.createCellStyle();
        linha1.setFillForegroundColor(palette.getColor(42).getIndex());
        linha1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        //Definição de estilos - FIM
        //Descobrindo o mês atual - INÍCIO
        Date dataAtual = new Date();
        int mes = dataAtual.getMonth();
        String mesAtual = "";
        switch (mes) {
            case 0:
                mesAtual = "Janeiro";
                break;
            case 1:
                mesAtual = "Fevereiro";
                break;
            case 2:
                mesAtual = "Março";
                break;
            case 3:
                mesAtual = "Abril";
                break;
            case 4:
                mesAtual = "Maio";
                break;
            case 5:
                mesAtual = "Junho";
                break;
            case 6:
                mesAtual = "Julho";
                break;
            case 7:
                mesAtual = "Agosto";
                break;
            case 8:
                mesAtual = "Setembro";
                break;
            case 9:
                mesAtual = "Outubro";
                break;
            case 10:
                mesAtual = "Novembro";
                break;
            case 11:
                mesAtual = "Dezembro";
                break;
        }
        //Descobrindo o mês atual - FIM
        //criação do Título
        HSSFRow titulo = planilha.createRow(linhaTabela);
        titulo.setHeight((short) 600);
        linhaTabela++;
        linhaTabela++;
        HSSFCell cellTitulo = titulo.createCell(0);
        cellTitulo.setCellValue("Relatório Mensal - " + mesAtual);
        cellTitulo.setCellStyle(estiloTitulo);
        //Verificando quantos dias tem o mês atual - INÍCIO
        Calendar calendar = new GregorianCalendar(dataAtual.getYear(), dataAtual.getMonth(), dataAtual.getDate());
        int dias = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //Verificando quantos dias tem o mês atual - FIM
        MovimentoDAO movimentoDAO = new MovimentoDAO();
        try {
            movimentos = movimentoDAO.selectRelatorio(dataAtual.getMonth() + 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        for (int i = 1; i <= dias; i++) { //Loop responsável pelos dias
            HSSFRow dia = planilha.createRow(linhaTabela);
            Region rs = new Region(linhaTabela, (short) 0, linhaTabela, (short) 7);
            planilha.addMergedRegion(rs);
            linhaTabela++;
            HSSFCell cellDia = dia.createCell(0);
            cellDia.setCellValue(i + "/" + (dataAtual.getMonth() + 1) + "/" + (dataAtual.getYear() + 1900));
            dia.setHeight((short) 400);
            cellDia.setCellStyle(estiloSubTitulo);
            //Título dos cabeçalhos da Tabela - INÍCIO
            HSSFRow tituloColuna = planilha.createRow(linhaTabela);
            linhaTabela++;
            HSSFCell cel0 = tituloColuna.createCell(0);
            cel0.setCellValue("CPF");
            cel0.setCellStyle(estiloNegrito);
            HSSFCell cel1 = tituloColuna.createCell(1);
            cel1.setCellValue("Nome");
            cel1.setCellStyle(estiloNegrito);
            HSSFCell cel2 = tituloColuna.createCell(2);
            cel2.setCellValue("Placa");
            cel2.setCellStyle(estiloNegrito);
            HSSFCell cel3 = tituloColuna.createCell(3);
            cel3.setCellValue("Tipo Veículo");
            cel3.setCellStyle(estiloNegrito);
            HSSFCell cel4 = tituloColuna.createCell(4);
            cel4.setCellValue("Marca");
            cel4.setCellStyle(estiloNegrito);
            HSSFCell cel5 = tituloColuna.createCell(5);
            cel5.setCellValue("Modelo");
            cel5.setCellStyle(estiloNegrito);
            HSSFCell cel6 = tituloColuna.createCell(6);
            cel6.setCellValue("Entrada");
            cel6.setCellStyle(estiloNegrito);
            HSSFCell cel7 = tituloColuna.createCell(7);
            cel7.setCellValue("Saída");
            cel7.setCellStyle(estiloNegrito);
            //Título dos cabeçalhos da Tabela - FIM
            for (Movimento movimento : movimentos) {
                String diaMovimento = movimento.getDataInicio().substring(8, 10);
                if (Integer.parseInt(diaMovimento) == i) {
                    HSSFRow linhaMovimento = planilha.createRow(linhaTabela);
                    linhaTabela++;
                    //CPF
                    HSSFCell celCPF = linhaMovimento.createCell(0);
                    if (movimento.getCliente().getCpf() != null) {
                        celCPF.setCellValue(movimento.getCliente().getCpf());
                    } else {
                        celCPF.setCellValue("");
                    }
                    if (linhaTabela % 2 == 0) {
                        celCPF.setCellStyle(linha0);
                    } else {
                        celCPF.setCellStyle(linha1);
                    }
                    //Nome
                    HSSFCell celNome = linhaMovimento.createCell(1);
                    if (movimento.getCliente().getCpf() != null) {
                        celNome.setCellValue(movimento.getCliente().getNome());
                    } else {
                        celNome.setCellValue("");
                    }
                    if (linhaTabela % 2 == 0) {
                        celNome.setCellStyle(linha0);
                    } else {
                        celNome.setCellStyle(linha1);
                    }
                    //Placa
                    HSSFCell celPlaca = linhaMovimento.createCell(2);
                    celPlaca.setCellValue(movimento.getVeiculo().getPlaca());
                    if (linhaTabela % 2 == 0) {
                        celPlaca.setCellStyle(linha0);
                    } else {
                        celPlaca.setCellStyle(linha1);
                    }
                    //Tipo de Veiculo
                    HSSFCell celTipo = linhaMovimento.createCell(3);
                    if (movimento.getVeiculo().getTipo() == 0) {
                        celTipo.setCellValue("Carro");
                    } else {
                        celTipo.setCellValue("Moto");
                    }
                    if (linhaTabela % 2 == 0) {
                        celTipo.setCellStyle(linha0);
                    } else {
                        celTipo.setCellStyle(linha1);
                    }
                    //Marca
                    HSSFCell celMarca = linhaMovimento.createCell(4);
                    celMarca.setCellValue(movimento.getVeiculo().getMarca().getDescricao());
                    if (linhaTabela % 2 == 0) {
                        celMarca.setCellStyle(linha0);
                    } else {
                        celMarca.setCellStyle(linha1);
                    }
                    //Modelo
                    HSSFCell celModelo = linhaMovimento.createCell(5);
                    celModelo.setCellValue(movimento.getVeiculo().getModelo().getDescricao());
                    if (linhaTabela % 2 == 0) {
                        celModelo.setCellStyle(linha0);
                    } else {
                        celModelo.setCellStyle(linha1);
                    }
                    //Entrada
                    HSSFCell celEntrada = linhaMovimento.createCell(6);
                    celEntrada.setCellValue(movimento.getDataInicio().substring(11, 16));
                    if (linhaTabela % 2 == 0) {
                        celEntrada.setCellStyle(linha0);
                    } else {
                        celEntrada.setCellStyle(linha1);
                    }
                    //Saída
                    HSSFCell celSaida = linhaMovimento.createCell(7);
                    if (movimento.getDataTermino() != null) {
                        celSaida.setCellValue(movimento.getDataTermino().substring(11, 16));
                    } else {
                        celSaida.setCellValue("");
                    }
                    if (linhaTabela % 2 == 0) {
                        celSaida.setCellStyle(linha0);
                    } else {
                        celSaida.setCellStyle(linha1);
                    }
                }
                //Criar célula com o total por mês
            }//for Movimentos
            linhaTabela++;
            linhaTabela++;
        }

        planilha.setColumnWidth(0, 3500);
        planilha.setColumnWidth(1, 7000);
        planilha.setColumnWidth(2, 3500);
        planilha.setColumnWidth(3, 3500);
        planilha.setColumnWidth(4, 7000);
        planilha.setColumnWidth(5, 7000);
        planilha.setColumnWidth(6, 3500);
        planilha.setColumnWidth(7, 3500);
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.close();
    }
}
