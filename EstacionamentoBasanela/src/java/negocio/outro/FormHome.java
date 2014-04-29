/*
 * Classe FormHome
 */
package negocio.outro;

import controller.ControllerInterface;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class FormHome implements ControllerInterface {

    public String call(HttpServletRequest request, HttpServletResponse response) {
        return "home.jsp";
    }

}
