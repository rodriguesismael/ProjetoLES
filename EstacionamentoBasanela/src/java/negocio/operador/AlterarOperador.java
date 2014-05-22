/*
 * Classe AlterarOperador
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
public class AlterarOperador implements ControllerInterface {

    public String call(HttpServletRequest request, HttpServletResponse response) {
        Operador operador = new Operador();
        OperadorDAO operadorDAO = new OperadorDAO();
        try {
            operador = operadorDAO.selectById(operador);
        } catch (SQLException ex) {
            Logger.getLogger(AlterarOperador.class.getName()).log(Level.SEVERE, null, ex);
        }
        operador.setCodOperador(Integer.parseInt(request.getParameter("codOperador")));
        operador.setNome(request.getParameter("nome"));
        if (Integer.parseInt(request.getParameter("administrador")) == 0) {
            operador.setAdministrador(false);
        } else {
            operador.setAdministrador(true);
        }
        operador.setLogin(request.getParameter("login"));
        if (request.getParameter("senha") != "") {
            operador.setSenha(request.getParameter("senha"));
        }
        if (operador.getSenha() != null) {
            try {
                operadorDAO.updateComSenha(operador);
            } catch (SQLException ex) {
                Logger.getLogger(AlterarOperador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                operadorDAO.update(operador);
            } catch (SQLException ex) {
                Logger.getLogger(AlterarOperador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "Controller?name=ListarOperador";
    }
}
