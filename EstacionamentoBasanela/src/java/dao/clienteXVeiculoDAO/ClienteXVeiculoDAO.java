/*
 * Classe ClienteXVeiculoDAO
 */
package dao.clienteXVeiculoDAO;

import modelo.clienteXVeiculo.ClienteXVeiculo;
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
 * @author Ismael Rodrigues
 */
public class ClienteXVeiculoDAO {

    public static final String INSERT = "INSERT INTO ClienteXVeiculo (cpf,placa) VALUES(?,?)";
    public static final String DELETE = "DELETE FROM ClienteXVeiculo WHERE cpf = ?";
    public static final String DELETEBYVEICULO = "DELETE FROM ClienteXVeiculo WHERE placa = ?";
    public static final String SELECTALL = "SELECT * FROM ClienteXVeiculo";
    public static final String SELECTBYCLIENTE = "SELECT * FROM ClienteXVeiculo WHERE cpf = ?";
    public static final String SELECTBYVEICULO = "SELECT cpf, placa FROM ClienteXVeiculo WHERE placa = ?";

    public void insert(ClienteXVeiculo clienteXveiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, clienteXveiculo.getCliente().getCpf());
            stmt.setString(2, clienteXveiculo.getVeiculo().getPlaca());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
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
            stmt.setString(1, clienteXveiculo.getCliente().getCpf());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void deleteByVeiculo(ClienteXVeiculo clienteXveiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(DELETEBYVEICULO);
            stmt.setString(1, clienteXveiculo.getVeiculo().getPlaca());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
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

                cliente.setCpf(rs.getString("cpf"));

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
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public List<ClienteXVeiculo> selectByCliente(ClienteXVeiculo clienteXVeiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ClienteXVeiculo> lista = new ArrayList<ClienteXVeiculo>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYCLIENTE);
            stmt.setString(1, clienteXVeiculo.getCliente().getCpf());
            rs = stmt.executeQuery();
            while (rs.next()) {
                ClienteXVeiculo novoClienteXVeiculo = new ClienteXVeiculo();
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculo = veiculoDAO.selectById(veiculo);
                novoClienteXVeiculo.setVeiculo(veiculo);
                novoClienteXVeiculo.setCliente(clienteXVeiculo.getCliente());
                lista.add(novoClienteXVeiculo);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public ClienteXVeiculo selectByVeiculo(ClienteXVeiculo clienteXVeiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYVEICULO);
            stmt.setString(1, clienteXVeiculo.getVeiculo().getPlaca());
            rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                ClienteDAO clienteDAO = new ClienteDAO();
                cliente.setCpf(rs.getString("cpf"));
                cliente = clienteDAO.selectById(cliente);
                clienteXVeiculo.setCliente(cliente);
                Veiculo veiculo = new Veiculo();
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo = veiculoDAO.selectById(veiculo);
                clienteXVeiculo.setVeiculo(veiculo);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return clienteXVeiculo;
    }
}
