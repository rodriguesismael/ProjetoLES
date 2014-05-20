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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.fatura.Fatura;
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
            try {
                emMovimento = movimentoDAO.selectEmMovimento(movimento);
            } catch (SQLException ex) {
                Logger.getLogger(BuscarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
            json += emMovimento + ", ";
            json += "\"faturasAbertas\": ";
            List<Fatura> listaFatura = new ArrayList<Fatura>();
            FaturaDAO faturaDAO = new FaturaDAO();
            try {
                listaFatura = faturaDAO.selectInadimplentesPorVeiculo(veiculo);
            } catch (SQLException ex) {
                Logger.getLogger(BuscarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (listaFatura.size() != 0) {
                int contador = 1;
                json += "\"";
                for (Fatura fatura : listaFatura) {
                    json += fatura.getCodFatura();
                    if (contador < listaFatura.size()) {
                        json += " - ";
                    }
                }
                json += "\"}";
            } else {
                json += "0}";
            }
        } else { //Veiculo nao existe no BD
            json += false + "}";
        }
        out.print(json);
    }
}
