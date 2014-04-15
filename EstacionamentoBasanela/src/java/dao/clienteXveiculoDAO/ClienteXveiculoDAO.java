/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.clienteXveiculoDAO;

import modelo.clienteXveiculo.ClienteXveiculo;
//import dao.cliente.Cliente;
import modelo.veiculo.Veiculo;
import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ismael
 */
public class ClienteXveiculoDAO {
    
    public static final String INSERT="INSERT INTO ClienteXVeiculo (codCliente,placa) VALUES(?,?)";
    public static final String DELETE="DELETE FROM ClienteXVeiculo WHERE codCliente = ?";
    public static final String SELECTALL="SELECT ";
    public static final String SELECTBYVCLIENTE="";
    public static final String SELECTBYVVEICULO="";
    
    public void insert(ClienteXveiculo clienteXveiculo) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setInt(1, clienteXveiculo.getCliente().getCodCliente());
            stmt.setString(2, clienteXveiculo.getVeiculo().getPlaca());
            stmt.executeUpdate();
        } catch (SQLException e){
            throw e;
        } finally {
            ConnectionFactory.closeAll(con,stmt);
        }
    }
    
    public void delete(ClienteXveiculo clienteXveiculo) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(DELETE);
            stmt.setInt(1, clienteXveiculo.getCliente().getCodCliente());
            stmt.executeUpdate();
        }catch (SQLException e){
            throw e;
        }finally{
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public List<ClienteXveiculo> selectAll(){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClienteXveiculo> lista = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                ClienteXveiculo clienteXveiculo = new ClienteXveiculo();
                clienteXveiculo.setCliente(cliente.se);
                
            }
        }
        return lista;
    }
}
