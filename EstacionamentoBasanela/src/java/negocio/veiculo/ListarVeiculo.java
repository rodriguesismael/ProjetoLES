/*
 * Classe ListarVeiculo
 */
package negocio.veiculo;

import controller.ControllerInterface;
import dao.veiculoDAO.VeiculoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class ListarVeiculo implements ControllerInterface {

    List<Veiculo> listaVeiculo;

    public String call(HttpServletRequest request, HttpServletResponse response) {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        try {
            listaVeiculo = veiculoDAO.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(ListarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaVeiculo", listaVeiculo);
        return "veiculo/listaVeiculo.jsp";
    }

}
