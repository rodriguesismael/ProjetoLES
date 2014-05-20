/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.marca;

import controller.UpdateInterface;
import dao.estadoDAO.EstadoDAO;
import dao.marcaDAO.MarcaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.estado.Estado;
import modelo.marca.Marca;

/**
 *
 * @author ismael
 */
public class CarregarMarcas implements UpdateInterface{
    private List<Marca> lista;
    public void executa(HttpServletRequest request, HttpServletResponse response){
        MarcaDAO marcaDAO = new MarcaDAO();
        
        try{
            lista = marcaDAO.selectAll();
        }catch(SQLException exc){
            Logger.getLogger(CarregarMarcas.class.getName()).log(Level.SEVERE, null, exc);
        }
        response.setContentType("application/json");
        PrintWriter out = null;
        try{
            out = response.getWriter();
        }catch(IOException ex){
            Logger.getLogger(CarregarMarcas.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.print("{\"listaMarca\": [");
        int i = 0;
        for(Marca e:lista){
            i++;
            out.print("{\"codMarca\": \""+e.getCodMarca()+"\", ");
            out.print("\"descricao\": \""+e.getDescricao()+"\"");
            if(lista.size() != i){
                out.print("}, ");
            }else{
                out.print("}");
            }
        }
        
        out.print("]}");        
    }
}
