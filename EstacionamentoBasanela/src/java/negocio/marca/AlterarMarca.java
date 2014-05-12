/*
 * Classe AlterarMarca
 */
package negocio.marca;

import controller.UpdateInterface;
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
public class AlterarMarca implements UpdateInterface {

    public void executa(HttpServletRequest request, HttpServletResponse response) {
        Marca marca = new Marca();
        MarcaDAO marcaDAO = new MarcaDAO();
        marca.setCodMarca(Integer.parseInt(request.getParameter("codMarca")));
        marca.setDescricao(request.getParameter("descricao"));
        try {
            marcaDAO.update(marca);
        } catch (SQLException ex) {
            Logger.getLogger(AlterarMarca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
