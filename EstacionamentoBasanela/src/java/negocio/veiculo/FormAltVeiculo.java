/*
 * Classe FormAltVeiculo
 */
package negocio.veiculo;

import controller.ControllerInterface;
import dao.marcaDAO.MarcaDAO;
import dao.modeloDAO.ModeloDAO;
import dao.veiculoDAO.VeiculoDAO;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.marca.Marca;
import modelo.modelo.Modelo;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class FormAltVeiculo implements ControllerInterface {

    List<Marca> listaMarca;
    List<Modelo> listaModelo;

    public String call(HttpServletRequest request, HttpServletResponse response) {
        MarcaDAO marcaDAO = new MarcaDAO();
        ModeloDAO modeloDAO = new ModeloDAO();
        Veiculo veiculo = new Veiculo();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculo.setPlaca(request.getParameter("id"));
        try {
            veiculo = veiculoDAO.selectById(veiculo);
            listaMarca = marcaDAO.selectAll();
            listaModelo = modeloDAO.selectByMarca(veiculo.getModelo());
        } catch (SQLException ex) {
            Logger.getLogger(FormAltVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("listaMarca", listaMarca);
        request.setAttribute("listaModelo", listaModelo);
        request.setAttribute("veiculo", veiculo);
        return "veiculo/formAltVeiculo.jsp";
    }
}
