/*
 * Classe BuscarVeiculo
 */
package negocio.veiculo;

import controller.UpdateInterface;
import dao.veiculoDAO.VeiculoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        String json = "{\"existeVeiculo\": ";
        Veiculo veiculo = new Veiculo();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculo.setPlaca(request.getParameter("placa"));
        try {
            veiculo = veiculoDAO.selectById(veiculo);
        } catch (SQLException ex) {
            Logger.getLogger(BuscarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (veiculo.getTipo() != 0) { //Veiculo existe no BD
            json += true + ", ";
            json += "\"veiculo\": [{";
            json += "\"placa\": \"" + veiculo.getPlaca() + "\", ";
            json += "\"tipo\": " + veiculo.getTipo() + ", ";
            json += "\"marca\": \"" + veiculo.getMarca().getDescricao() + "\", ";
            json += "\"modelo\": \"" + veiculo.getModelo().getDescricao() + "\"";
            json += "}], ";
            json += "\"emMovimento\": ";

        } else { //Veiculo nao existe no BD
            json += false + "}";
        }
    }
}
