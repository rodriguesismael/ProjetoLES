/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio.cidade;

import controller.UpdateInterface;
import dao.cidadeDAO.CidadeDAO;
import modelo.cidade.Cidade;
import dao.estadoDAO.EstadoDAO;
import modelo.estado.Estado;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.outro.EfetuarLogin;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ismael
 */
public class BuscarCidade implements UpdateInterface{
    private List<Cidade> lista;
    public void executa(HttpServletRequest request, HttpServletResponse response){
        
        Estado estado = new Estado();
        Cidade cidade = new Cidade();
        
        /***
         * 
         * 
        EstadoDAO estadoDAO = new EstadoDAO();
        estado.setCodEstado(Integer.parseInt(request.getParameter("codEstado")));*
        * 
        * 
        * 
        */
        estado.setCodEstado(Integer.parseInt(request.getParameter("codEstado")));
        cidade.setEstado(estado);
        CidadeDAO cidadeDao = new CidadeDAO();
        try{
            /*estadoDAO.selectById(estado);*/
            lista = cidadeDao.selectByEstado(cidade);
            /*lista = estado.getListaCidade()*/;
        }catch(SQLException ex){
            Logger.getLogger(BuscarCidade.class.getName()).log(Level.SEVERE,null,ex);
        }
        response.setContentType("application/json");
        PrintWriter out = null;
        try{
            out = response.getWriter();
        }catch(IOException exc){
            Logger.getLogger(BuscarCidade.class.getName()).log(Level.SEVERE,null,exc);
        }
        out.print("{\"listaCidade\": [");
        int i = 0;
        for(Cidade c:lista){
            i++;
            out.print("{\"codCidade\": \""+c.getCodCidade()+"\", ");
            out.print("\"descricao\": \""+c.getDescricao()+"\"");
            if(lista.size() != i){
                out.print("}, ");
            }else{
                out.print("}");
            }
        }
        
        out.print("]}");
    }
}
