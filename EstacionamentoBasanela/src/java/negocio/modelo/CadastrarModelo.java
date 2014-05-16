/*
 * Classe CadastrarModelo
 */
package negocio.modelo;

import controller.UpdateInterface;
import dao.modeloDAO.ModeloDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.marca.Marca;
import modelo.modelo.Modelo;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class CadastrarModelo implements UpdateInterface {

    public void executa(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException exc) {
            Logger.getLogger(CadastrarModelo.class.getName()).log(Level.SEVERE, null, exc);
        }
        Modelo modelo = new Modelo();
        ModeloDAO modeloDAO = new ModeloDAO();
        Marca marca = new Marca();
        marca.setCodMarca(Integer.parseInt(request.getParameter("codMarca")));
        modelo.setMarca(marca);
        modelo.setDescricao(request.getParameter("descricao"));
        int codModelo = 0;
        try {
            codModelo = modeloDAO.insert(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("application/json");
        out.print("{\"codModelo\": " + codModelo + ", ");
        out.print("\"descricao\": \"" + modelo.getDescricao() + "\"}");
    }
}
