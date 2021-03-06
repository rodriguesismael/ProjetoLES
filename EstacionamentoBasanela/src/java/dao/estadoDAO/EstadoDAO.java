/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.estadoDAO;

import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.estado.Estado;
import modelo.cidade.Cidade;
import dao.cidadeDAO.CidadeDAO;
/**
 *
 * @author Ismael
 */
public class EstadoDAO {
    //public static final String INSERT="";
    //public static final String UPDATE="";
    //public static final String DELETE="";
    public static final String SELECTALL="SELECT codEstado, uf, descricao FROM Estado";
    public static final String SELECTBYID="SELECT codEstado, uf, descricao FROM Estado WHERE codEstado = ?";
    
    
    public List<Estado> selectAll() throws SQLException{
      Connection con = null;
      PreparedStatement stmt = null;
      ResultSet rs = null;
      List<Estado> lista = new ArrayList<Estado>();
      try{
          con = ConnectionFactory.getConexao();
          stmt = con.prepareStatement(SELECTALL);
          rs = stmt.executeQuery();
          while(rs.next()){
              Estado estado =  new Estado();
              estado.setCodEstado(rs.getInt("codEstado"));
              estado.setUf(rs.getString("uf"));
              estado.setDescricao(rs.getString("descricao"));
              
              /*Cidade cidade = new Cidade();
              CidadeDAO cidadeDAO = new CidadeDAO();
              cidade.setEstado(estado);
              
              estado.setListaCidade(cidadeDAO.selectByEstado(cidade));*/
              
              lista.add(estado);
          }
      }catch(SQLException ex){
          throw ex;
      }finally{
          ConnectionFactory.closeAll(con,stmt,rs);
      }
      return lista;
    }
    
    public Estado selectById(Estado estado) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setInt(1, estado.getCodEstado());
            rs = stmt.executeQuery();
            if(rs.next()){
                estado.setCodEstado(rs.getInt("codEstado"));
                estado.setUf(rs.getString("uf"));
                estado.setDescricao(rs.getString("descricao"));
                /*Cidade cidade = new Cidade();
                CidadeDAO cidadeDAO = new CidadeDAO();
                cidade.setEstado(estado);

                estado.setListaCidade(cidadeDAO.selectByEstado(cidade));*/
                
            }
        } catch (SQLException ex){
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con,stmt,rs);
        }
        
        return estado;
    }
}
