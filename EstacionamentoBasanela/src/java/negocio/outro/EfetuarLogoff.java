/*
 * Classe EfetuarLogoff
 */
package negocio.outro;

import controller.ControllerInterface;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class EfetuarLogoff implements ControllerInterface {

    public String call(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute("loggedIn", false);
        return "index.jsp";
    }
}
