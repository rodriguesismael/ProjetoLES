/*
 * Classe FormCadVeiculoMensal
 */
package negocio.veiculo;

import controller.ControllerInterface;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class FormCadVeiculoMensal implements ControllerInterface {

    public String call(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("placa", request.getParameter("id"));
        return "veiculo/formCadVeiculoMensal.jsp";
    }

}
