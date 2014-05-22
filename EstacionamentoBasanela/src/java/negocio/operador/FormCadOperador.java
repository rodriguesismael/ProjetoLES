/*
 * Classe FormCadOperador
 */
package negocio.operador;

import controller.ControllerInterface;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class FormCadOperador implements ControllerInterface {

    @Override
    public String call(HttpServletRequest request, HttpServletResponse response) {
        return "operador/formCadOperador.jsp";
    }
}
