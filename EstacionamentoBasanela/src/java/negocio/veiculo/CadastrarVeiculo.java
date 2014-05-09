/*
 * Classe CadastrarVeiculo
 */
package negocio.veiculo;

import controller.UpdateInterface;
import dao.marcaDAO.MarcaDAO;
import dao.modeloDAO.ModeloDAO;
import dao.veiculoDAO.VeiculoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class CadastrarVeiculo implements UpdateInterface {

    public void executa(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException exc) {
            Logger.getLogger(CadastrarVeiculo.class.getName()).log(Level.SEVERE, null, exc);
        }
        Veiculo veiculo = new Veiculo();
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculo.setPlaca(request.getParameter("placa"));
        veiculo.setTipo(Integer.parseInt(request.getParameter("tipoVeiculo")));
        Marca marca = new Marca();
        MarcaDAO marcaDAO = new MarcaDAO();
        marca.setCodMarca(Integer.parseInt(request.getParameter("marcaVeiculo")));
        try {
            marca = marcaDAO.selectById(marca);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        veiculo.setMarca(marca);
        Modelo modelo = new Modelo();
        ModeloDAO modeloDAO = new ModeloDAO();
        modelo.setCodModelo(Integer.parseInt(request.getParameter("modeloVeiculo")));
        try {
            modelo = modeloDAO.selectById(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        veiculo.setModelo(modelo);
        try {
            veiculoDAO.insert(veiculo);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarVeiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("application/json");
        out.print("{\"placa\": " + veiculo.getPlaca());
    }
}
