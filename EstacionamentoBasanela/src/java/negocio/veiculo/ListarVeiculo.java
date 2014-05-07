/*
 * Classe ListarVeiculo
 */
package negocio.veiculo;

import controller.ControllerInterface;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class ListarVeiculo implements ControllerInterface {

    public String call(HttpServletRequest request, HttpServletResponse response) {
        return "veiculo/listaVeiculo.jsp";
    }

}
