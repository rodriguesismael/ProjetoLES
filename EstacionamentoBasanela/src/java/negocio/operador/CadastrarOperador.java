/*
 * Classe CadastrarOperador
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
public class CadastrarOperador implements ControllerInterface {

    @Override
    public String call(HttpServletRequest request, HttpServletResponse response) {
        Operador operador = new Operador();
        OperadorDAO operadorDAO = new OperadorDAO();
        operador.setNome(request.getParameter("nome"));
        if (Integer.parseInt(request.getParameter("administrador")) == 0) {
            operador.setAdministrador(false);
        } else {
            operador.setAdministrador(true);
        }
        operador.setLogin(request.getParameter("login"));
        operador.setSenha(request.getParameter("senha"));
        operador.setStatus(true);// Ativo
        try {
            operadorDAO.insert(operador);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "Controller?name=ListarOperador";
    }
}
