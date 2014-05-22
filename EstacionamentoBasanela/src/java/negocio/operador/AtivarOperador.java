/*
 * Classe AtivarOperador
 */
package negocio.operador;

import controller.ControllerInterface;
import dao.operadorDAO.OperadorDAO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.operador.Operador;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class AtivarOperador implements ControllerInterface {

    public String call(HttpServletRequest request, HttpServletResponse response) {
        Operador operador = new Operador();
        OperadorDAO operadorDAO = new OperadorDAO();
        operador.setCodOperador(Integer.parseInt(request.getParameter("codOperador")));
        try {
            operador = operadorDAO.selectById(operador);
        } catch (SQLException ex) {
            Logger.getLogger(AtivarOperador.class.getName()).log(Level.SEVERE, null, ex);
        }
        operador.setStatus(true);
        try {
            operadorDAO.update(operador);
        } catch (SQLException ex) {
            Logger.getLogger(AtivarOperador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Controller?name=ListarOperador";
    }
}
