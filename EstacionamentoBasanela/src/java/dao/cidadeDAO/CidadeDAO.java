/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.cidadeDAO;

import modelo.cidade.Cidade;
import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author Ismael
 */
public class CidadeDAO {
    public static final String INSERT = "INSERT INTO Cidade (codEsdado,descricao) VALUES (?,?)";
    public static final String UPDATE = "UPDATE Cidade SET codEstado = ?, descricao = ? WHERE codCidade=?";
    public static final String DELETE = "DELETE FROM Cidade WHERE codCidade = ?";
    public static final String SELECTALL = "SELECT codCidade, codEsdado, descricao FROM Cidade";
    public static final String SELECTBYID = SELECTALL+" WHERE codCidade = ?";
    public static final String SELECTBYESTADO = SELECTALL+" WHERE codEstado = ?";
    
    public void insert(Cidade cidade) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setInt(1, cidade.getEstado().getCodEstado());
            stmt.setString(2, cidade.getDescricao());
            stmt.executeUpdate();
        }catch(SQLException e){
            throw e;
        } finally{
            ConnectionFactory.closeAll(con, stmt);
        }
    }
    
    public void update(Cidade cidade) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(UPDATE);
            stmt.setInt(1, cidade.getEstado().getCodEstado());
            stmt.setString(2, cidade.getDescricao());
            stmt.setInt(3, cidade.getCodCidade());
            stmt.executeUpdate();
        }catch(SQLException e){
            throw e;
        } finally{
            ConnectionFactory.closeAll(con, stmt);
        }
    }
    
    public void delete(Cidade cidade) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(DELETE);
            stmt.setInt(1, cidade.getCodCidade());
            stmt.executeUpdate();
        }catch(SQLException e){
            throw e;
        } finally{
            ConnectionFactory.closeAll(con, stmt);
        }
    }    
    
    
}
