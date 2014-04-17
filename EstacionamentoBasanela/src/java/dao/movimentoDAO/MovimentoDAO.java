/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.movimentoDAO;

import dao.ConnectionFactory;
import modelo.movimento.Movimento;
import modelo.veiculo.Veiculo;
import dao.veiculoDAO.VeiculoDAO;
import modelo.cliente.Cliente;
import dao.clienteDAO.ClienteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ismael
 */
public class MovimentoDAO {
    public static final String INSERT="INSERT INTO Movimento (codCliente, placa, dataInicio, dataTermino) VALUES(?,?,?,?)";
    public static final String SELECTALL="SELECT codMovimento,codCliente,placa,dataInicio,dataTermino FROM Movimento";
    public static final String SELECTBYID= "SELECT codMovimento,codCliente,placa,dataInicio,dataTermino FROM Movimento "
            + "WHERE codMovimento = ?";
    public static final String SELECTBYCLIENTE= "SELECT codMovimento,codCliente,placa,dataInicio,dataTermino FROM Movimento "
            + "WHERE codCliente = ?";
    public static final String SELECTBYVEICULO= "SELECT codMovimento,codCliente,placa,dataInicio,dataTermino FROM Movimento "
            + "WHERE placa = ?";
    
    public void insert(Movimento movimento) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            //tratar para o valor padrao de id de cliente ser 0 quando for veiculo avulso
            if(movimento.getCliente() == null)
                stmt.setInt(1, 0);
            else
                stmt.setInt(1, movimento.getCliente().getCodCliente());
            stmt.setString(2, movimento.getVeiculo().getPlaca());
            stmt.setString(3, movimento.getData_inicio());
            stmt.setString(4, movimento.getData_termino());
            stmt.executeUpdate();
                    
        }catch(SQLException e){
            throw e;
        }finally{
            ConnectionFactory.closeAll(con,stmt);
        }  
    }
    
    public List<Movimento> selectAll() throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movimento> lista = new ArrayList<Movimento>();
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while(rs.next()){
                Movimento movimento = new Movimento();
                movimento.setCodMovimento(rs.getInt("codMovimento"));
                movimento.setData_inicio(rs.getString("dataInicio"));
                movimento.setData_termino(rs.getString("dataTermino"));
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculo = veiculoDAO.selectById(veiculo);
                movimento.setVeiculo(veiculo);                
                if(rs.getInt("codCliente") != 0){
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(rs.getInt("codCliente"));
                    ClienteDAO cliDAO = new ClienteDAO();
                    cliente = cliDAO.selectById(cliente);
                    movimento.setCliente(cliente);
                }else{
                    movimento.setCliente(null);
                }
                lista.add(movimento);
            }
        }catch(SQLException e){
            throw e;
        }finally{
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }
    
    public Movimento selectById(Movimento movimento) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setInt(1, movimento.getCodMovimento());
            rs = stmt.executeQuery();
            if(rs.next()){
                movimento.setCodMovimento(rs.getInt("codMovimento"));
                movimento.setData_inicio(rs.getString("dataInicio"));
                movimento.setData_termino(rs.getString("dataTermino"));
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculo = veiculoDAO.selectById(veiculo);
                movimento.setVeiculo(veiculo);                
                if(rs.getInt("codCliente") != 0){
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(rs.getInt("codCliente"));
                    ClienteDAO cliDAO = new ClienteDAO();
                    cliente = cliDAO.selectById(cliente);
                    movimento.setCliente(cliente);
                }else{
                    movimento.setCliente(null);
                }
            }
        }catch(SQLException e){
            throw e;
        }finally{
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return movimento;
    }    
    
    public List<Movimento> selectByCliente(Movimento movimento) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movimento> lista = new ArrayList<Movimento>();
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYCLIENTE);
            stmt.setInt(1, movimento.getCliente().getCodCliente());
            rs = stmt.executeQuery();
            Cliente cliente = new Cliente();
            cliente.setCodCliente(movimento.getCliente().getCodCliente());
            ClienteDAO cliDAO = new ClienteDAO();
            cliente = cliDAO.selectById(cliente);
            while(rs.next()){
                Movimento nMovimento = new Movimento();
                nMovimento.setCodMovimento(rs.getInt("codMovimento"));
                nMovimento.setData_inicio(rs.getString("dataInicio"));
                nMovimento.setData_termino(rs.getString("dataTermino"));
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculo = veiculoDAO.selectById(veiculo);
                nMovimento.setVeiculo(veiculo);
                nMovimento.setCliente(cliente);
                /*
                if(rs.getInt("codCliente") != 0){
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(rs.getInt("codCliente"));
                    ClienteDAO cliDAO = new ClienteDAO();
                    cliente = cliDAO.selectById(cliente);
                    
                }else{
                    nMovimento.setCliente(null);
                }*/
                lista.add(nMovimento);
            }
        }catch(SQLException e){
            throw e;
        }finally{
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }    
       
    public List<Movimento> selectByVeiculo(Movimento movimento) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movimento> lista = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYVEICULO);
            stmt.setString(1, movimento.getVeiculo().getPlaca());
            rs = stmt.executeQuery();
            
            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca(movimento.getVeiculo().getPlaca());
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculo = veiculoDAO.selectById(veiculo);
            
            while(rs.next()){
                Movimento nMovimento = new Movimento();
                nMovimento.setCodMovimento(rs.getInt("codMovimento"));
                nMovimento.setData_inicio(rs.getString("dataInicio"));
                nMovimento.setData_termino(rs.getString("dataTermino"));
                nMovimento.setVeiculo(veiculo);                
                if(rs.getInt("codCliente") != 0){
                    Cliente cliente = new Cliente();
                    cliente.setCodCliente(rs.getInt("codCliente"));
                    ClienteDAO cliDAO = new ClienteDAO();
                    cliente = cliDAO.selectById(cliente);
                    nMovimento.setCliente(cliente);
                }else{
                    nMovimento.setCliente(null);
                }
                lista.add(nMovimento);
            }
        }catch(SQLException e){
            throw e;
        }finally{
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }    
}
