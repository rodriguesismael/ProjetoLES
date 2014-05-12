/*
 * Classe CadastrarMarca
 */
package negocio.marca;

import controller.UpdateInterface;
import dao.marcaDAO.MarcaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.marca.Marca;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class CadastrarMarca implements UpdateInterface {

    public void executa(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException exc) {
            Logger.getLogger(CadastrarMarca.class.getName()).log(Level.SEVERE, null, exc);
        }
        Marca marca = new Marca();
        MarcaDAO marcaDAO = new MarcaDAO();
        marca.setDescricao(request.getParameter("descricao"));
        int codMarca = 0;
        try {
            codMarca = marcaDAO.insert(marca);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarMarca.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("application/json");
        out.print("{\"codMarca\": " + codMarca + ", ");
        out.print("\"descricao\": \"" + marca.getDescricao() + "\"}");
    }
}
