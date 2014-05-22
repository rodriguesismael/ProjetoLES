/*
 * Classe BuscarLoginDuplicado
 */
package negocio.operador;

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
public class BuscarLoginDuplicado implements UpdateInterface {

    @Override
    public void executa(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException exc) {
            Logger.getLogger(BuscarLoginDuplicado.class.getName()).log(Level.SEVERE, null, exc);
        }
        Operador operador = new Operador();
        OperadorDAO operadorDAO = new OperadorDAO();
        operador.setLogin(request.getParameter("login"));
        boolean existeLogin = false;
        try {
            existeLogin = operadorDAO.selectByLogin(operador);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        response.setContentType("application/json");
        out.print("{\"existeLogin\": " + existeLogin + "}");
    }
}
