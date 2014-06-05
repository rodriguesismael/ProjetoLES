/*
 * Classe BuscarVeiculo
 */
package negocio.veiculo;

import controller.UpdateInterface;
import dao.faturaDAO.FaturaDAO;
import dao.movimentoDAO.MovimentoDAO;
import dao.veiculoDAO.VeiculoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.movimento.Movimento;
import modelo.veiculo.Veiculo;
import negocio.outro.EfetuarLogin;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class BuscarVeiculo implements UpdateInterface {

    public void executa(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException exc) {
            Logger.getLogger(EfetuarLogin.class.getName()).log(Level.SEVERE, null, exc);
        }
        Veiculo veiculo = new Veiculo();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculo.setPlaca(request.getParameter("placa"));
        try {
            veiculo = veiculoDAO.selectById(veiculo);
        } catch (SQLException ex) {
            Logger.getLogger(BuscarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        String json = "{\"existeVeiculo\": ";
        if (veiculo.getMarca() != null) { //Veiculo existe no BD
            json += true + ", ";
            json += "\"veiculo\": [{";
            json += "\"placa\": \"" + veiculo.getPlaca() + "\", ";
            json += "\"tipo\": " + veiculo.getTipo() + ", ";
            json += "\"marca\": \"" + veiculo.getMarca().getDescricao() + "\", ";
            json += "\"modelo\": \"" + veiculo.getModelo().getDescricao() + "\"";
            json += "}], ";
            json += "\"emMovimento\": ";
            Movimento movimento = new Movimento();
            MovimentoDAO movimentoDAO = new MovimentoDAO();
            movimento.setVeiculo(veiculo);
            boolean emMovimento = false;
            String dataHoraEntrada="";
            try {
                emMovimento = movimentoDAO.selectEmMovimento(movimento);
                if(emMovimento){
                    movimento = movimentoDAO.selectByVeiculo(movimento);
                    String dataHora = movimento.getDataInicio();

                    try{
                        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dataHora);
                        String data = new SimpleDateFormat("dd/MM/yyyy").format(date);
                        String hora = new SimpleDateFormat("HH:mm").format(date);
                        dataHoraEntrada += "\"dataEntrada\": \""+data+"\", ";
                        dataHoraEntrada += "\"horaEntrada\": \""+hora+"\", ";                        
                    }catch(ParseException ex){
                        Logger.getLogger(BuscarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }                

            } catch (SQLException ex) {
                Logger.getLogger(BuscarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
            json += emMovimento + ", ";
            json += dataHoraEntrada;
            boolean faturaAberta = false;
            FaturaDAO faturaDAO = new FaturaDAO();
            try {
                faturaAberta = faturaDAO.selectInadimplentesPorVeiculo(veiculo);
            } catch (SQLException ex) {
                Logger.getLogger(BuscarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
            json += "\"faturasAbertas\": " + faturaAberta + "}";
        } else { //Veiculo nao existe no BD
            json += false + "}";
        }
        out.print(json);
    }
}
