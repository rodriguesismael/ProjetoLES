/*
 * Classe EfetuarLogin
 */
package negocio.outro;

import controller.UpdateInterface;
import dao.operadorDAO.OperadorDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
public class EfetuarLogin implements UpdateInterface {
    
    public void executa(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException exc) {
            Logger.getLogger(EfetuarLogin.class.getName()).log(Level.SEVERE, null, exc);
        }
        Operador operador = new Operador();
        OperadorDAO operadorDAO = new OperadorDAO();
        operador.setLogin(request.getParameter("login"));
        operador.setSenha(request.getParameter("senha"));
        try {
            operador = operadorDAO.login(operador);
        } catch (SQLException ex) {
            Logger.getLogger(EfetuarLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean status = false;
        if (operador.getCodOperador() != 0) {
            status = true;
        }
        out.print("{\"status\":" + status + "}");
    }
}
