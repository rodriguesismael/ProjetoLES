/*
 * Classe ListarMarca
 */
package negocio.marca;

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
public class ListarMarca implements ControllerInterface {

    private List<Marca> lista;

    public String call(HttpServletRequest request, HttpServletResponse response) {
        MarcaDAO marcaDAO = new MarcaDAO();
        try {
            lista = marcaDAO.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(ListarMarca.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (lista.isEmpty()) {
            request.setAttribute("resultado", false);
        } else {
            request.setAttribute("resultado", true);
            request.setAttribute("listaMarca", lista);
        }
        return "marca/listaMarca.jsp";
    }
}
