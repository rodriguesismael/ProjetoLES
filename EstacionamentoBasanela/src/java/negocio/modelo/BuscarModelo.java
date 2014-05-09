/*
 * Classe BuscarModelo
 */
package negocio.modelo;

import controller.UpdateInterface;
import dao.modeloDAO.ModeloDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class BuscarModelo implements UpdateInterface {

    private List<Modelo> lista;

    public void executa(HttpServletRequest request, HttpServletResponse response) {
        Marca marca = new Marca();
        Modelo modelo = new Modelo();
        ModeloDAO modeloDAO = new ModeloDAO();
        marca.setCodMarca(Integer.parseInt(request.getParameter("codMarca")));
        modelo.setMarca(marca);
        try {
            lista = modeloDAO.selectByMarca(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(BuscarModelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("application/json");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException exc) {
            Logger.getLogger(BuscarModelo.class.getName()).log(Level.SEVERE, null, exc);
        }
        out.print("{\"listaModelo\": [");
        int i = 0;
        for (Modelo m : lista) {
            i++;
            out.print("{\"codModelo\": " + m.getCodModelo() + ", ");
            out.print("\"descricao\": \"" + m.getDescricao() + "\"");
            if (lista.size() != i) {
                out.print("}, ");
            } else {
                out.print("}");
            }
        }
        out.print("]}");
    }
}
