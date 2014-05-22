/*
 * Classe ListarOperador
 */
package negocio.operador;

import controller.ControllerInterface;
import dao.operadorDAO.OperadorDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.operador.Operador;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class ListarOperador implements ControllerInterface {

    private List<Operador> listaOperador;

    @Override
    public String call(HttpServletRequest request, HttpServletResponse response) {
        OperadorDAO operadorDAO = new OperadorDAO();
        try {
            listaOperador = operadorDAO.selectAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        request.setAttribute("listaOperador", listaOperador);
        return "operador/listaOperador.jsp";
    }
}
