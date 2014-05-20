/*
 * Classe RegistrarSaida
 */
package negocio.movimento;

import controller.UpdateInterface;
import dao.movimentoDAO.MovimentoDAO;
import dao.veiculoDAO.VeiculoDAO;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.movimento.Movimento;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class RegistrarSaida implements UpdateInterface {

    public void executa(HttpServletRequest request, HttpServletResponse response) {
        Veiculo veiculo = new Veiculo();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculo.setPlaca(request.getParameter("placa"));
        try {
            veiculo = veiculoDAO.selectById(veiculo);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarSaida.class.getName()).log(Level.SEVERE, null, ex);
        }
        MovimentoDAO movimentoDAO = new MovimentoDAO();
        Movimento movimento = new Movimento();
        movimento.setVeiculo(veiculo);
        try {
            movimento = movimentoDAO.selectByVeiculo(movimento);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarSaida.class.getName()).log(Level.SEVERE, null, ex);
        }
        SimpleDateFormat sdfout = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        movimento.setDataTermino(sdfout.format(date));
        try {
            movimentoDAO.update(movimento);
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarSaida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
