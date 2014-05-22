/*
 * Classe FormAltOperador
 */
package negocio.operador;

import controller.ControllerInterface;
import dao.operadorDAO.OperadorDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.operador.Operador;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class FormAltOperador implements ControllerInterface {

    @Override
    public String call(HttpServletRequest request, HttpServletResponse response) {
        Operador operador = new Operador();
        OperadorDAO operadorDAO = new OperadorDAO();
        operador.setCodOperador(Integer.parseInt(request.getParameter("id")));
        try {
            operador = operadorDAO.selectById(operador);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        request.setAttribute("operador", operador);
        return "operador/formAltOperador.jsp";
    }
}
