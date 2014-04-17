/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.clienteXveiculoDAO;

import modelo.clienteXVeiculo.ClienteXVeiculo;
//import dao.cliente.Cliente;
import modelo.veiculo.Veiculo;
import dao.veiculoDAO.VeiculoDAO;
import modelo.cliente.Cliente;
import dao.clienteDAO.ClienteDAO;
import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ismael
 */
public class ClienteXveiculoDAO {

    public static final String INSERT = "INSERT INTO ClienteXVeiculo (codCliente,placa) VALUES(?,?)";
    public static final String DELETE = "DELETE FROM ClienteXVeiculo WHERE codCliente = ?";
    public static final String SELECTALL = "SELECT ";
    public static final String SELECTBYCLIENTE = "";
    public static final String SELECTBYVVEICULO = "";

    public void insert(ClienteXVeiculo clienteXveiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setInt(1, clienteXveiculo.getCliente().getCodCliente());
            stmt.setString(2, clienteXveiculo.getVeiculo().getPlaca());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void delete(ClienteXVeiculo clienteXveiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(DELETE);
            stmt.setInt(1, clienteXveiculo.getCliente().getCodCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public List<ClienteXVeiculo> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClienteXVeiculo> lista = new ArrayList<ClienteXVeiculo>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ClienteXVeiculo clienteXveiculo = new ClienteXVeiculo();
                Cliente cliente = new Cliente();
                cliente.setCodCliente(rs.getInt("codCliente"));
                ClienteDAO cliDAO = new ClienteDAO();
                cliente = cliDAO.selectById(cliente);
                clienteXveiculo.setCliente(cliente);
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculo = veiculoDAO.selectById(veiculo);
                clienteXveiculo.setVeiculo(veiculo);

                lista.add(clienteXveiculo);

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public List<Veiculo> selectByCliente(ClienteXVeiculo clienteXVeiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Veiculo> lista = new ArrayList<Veiculo>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYCLIENTE);
            stmt.setInt(1, clienteXVeiculo.getCliente().getCodCliente());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculo = veiculoDAO.selectById(veiculo);
                lista.add(veiculo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }
}
