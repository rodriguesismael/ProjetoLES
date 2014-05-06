/*
 * Classe FormCadVeiculo
 */
package negocio.veiculo;

import controller.ControllerInterface;
import dao.marcaDAO.MarcaDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.marca.Marca;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class FormCadVeiculo implements ControllerInterface {

    private List<Marca> listaMarca;

    public String call(HttpServletRequest request, HttpServletResponse response) {
        MarcaDAO marcaDAO = new MarcaDAO();
        try {
            listaMarca = marcaDAO.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(FormCadVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaMarca", listaMarca);
        return "veiculo/formCadVeiculo.jsp";
    }
}
