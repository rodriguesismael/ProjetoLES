/*
 * Classe CadastrarMarca
 */
package negocio.marca;

import controller.ControllerInterface;
import dao.marcaDAO.MarcaDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.marca.Marca;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class CadastrarMarca implements ControllerInterface {

    public String call(HttpServletRequest request, HttpServletResponse response) {
        Marca marca = new Marca();
        MarcaDAO marcaDAO = new MarcaDAO();
        marca.setCodMarca(Integer.parseInt(request.getParameter("codMarca")));
        marca.setDescricao(request.getParameter("descricao"));
        try {
            marcaDAO.insert(marca);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarMarca.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Controller?name=ListarMarca";
    }

}
