/*
 * Classe AlterarModelo
 */
package negocio.modelo;

import controller.UpdateInterface;
import dao.modeloDAO.ModeloDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.modelo.Modelo;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class AlterarModelo implements UpdateInterface {

    public void executa(HttpServletRequest request, HttpServletResponse response) {
        Modelo modelo = new Modelo();
        ModeloDAO modeloDAO = new ModeloDAO();
        modelo.setCodModelo(Integer.parseInt(request.getParameter("codModelo")));
        modelo.setDescricao(request.getParameter("descricao"));
        try {
            modeloDAO.update(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(AlterarModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
