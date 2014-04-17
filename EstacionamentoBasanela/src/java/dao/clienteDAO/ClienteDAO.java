/*
 * Classe ClienteDAO
 */
package dao.clienteDAO;

import modelo.cliente.Cliente;
import dao.ConnectionFactory;
import dao.estadoDAO.EstadoDAO;
import modelo.estado.Estado;
import dao.cidadeDAO.CidadeDAO;
import dao.clienteXveiculoDAO.ClienteXveiculoDAO;
import modelo.cidade.Cidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.clienteXVeiculo.ClienteXVeiculo;

/**
 *
 * @author ismael
 */
public class ClienteDAO {

    public static final String INSERT = "INSERT INTO Cliente VALUES(?,?,?,?,?,?,?,?)";
    public static final String UPDATE = "UPDATE Cliente SET nome = ?,endereco= ?,codEstado = ?,"
            + " codCidade=?, telefone = ?, celular = ?, periodo = ? "
            + " WHERE codCliente = ?";
    public static final String DELETE = "DELETE FROM Cliente WHERE codCliente = ?";
    public static final String SELECTALL = "SELECT codCliente,nome,endereco,codEstado,codCidade,telefone"
            + "celular, periodo FROM Cliente";
    public static final String SELECTBYID = "SELECT codCliente,nome,endereco,codEstado,codCidade,telefone"
            + "celular, periodo FROM Cliente WHERE codCliente = ?";
    public static final String SELECTBYESTADO = "SELECT codCliente,nome,endereco,codEstado,codCidade,telefone"
            + "celular, periodo FROM Cliente WHERE codEstado = ?";
    public static final String SELECTBYCIDADE = "SELECT codCliente,nome,endereco,codEstado,codCidade,telefone"
            + "celular, periodo FROM Cliente WHERE codCidade = ?";

    public void insert(Cliente cliente) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setInt(1, cliente.getCodCliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEndereco());
            stmt.setInt(4, cliente.getEstado().getCodEstado());
            stmt.setInt(5, cliente.getCidade().getCodCidade());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getCelular());
            stmt.setInt(8, cliente.getPeriodo());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void update(Cliente cliente) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(UPDATE);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEndereco());
            stmt.setInt(3, cliente.getEstado().getCodEstado());
            stmt.setInt(4, cliente.getCidade().getCodCidade());
            stmt.setString(5, cliente.getTelefone());
            stmt.setString(6, cliente.getCelular());
            stmt.setInt(7, cliente.getPeriodo());
            stmt.setInt(8, cliente.getCodCliente());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void delete(Cliente cliente) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(DELETE);
            stmt.setInt(1, cliente.getCodCliente());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public List<Cliente> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cliente cliente = new Cliente();

                Estado estado = new Estado();
                estado.setCodEstado(rs.getInt("codEstado"));
                EstadoDAO estadoDAO = new EstadoDAO();
                estado = estadoDAO.selectById(estado);
                Cidade cidade = new Cidade();
                cidade.setCodCidade(rs.getInt("codCidade"));
                CidadeDAO cidadeDAO = new CidadeDAO();
                cidade = cidadeDAO.selectById(cidade);

                cliente.setCodCliente(rs.getInt("codCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setEstado(estado);
                cliente.setCidade(cidade);
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setPeriodo(rs.getInt("periodo"));

                ClienteXVeiculo clienteXveiculo = new ClienteXVeiculo();
                ClienteXveiculoDAO clienteXveiculoDAO = new ClienteXveiculoDAO();
                clienteXveiculo.setCliente(cliente);
                cliente.setListaVeiculo(clienteXveiculoDAO.selectByCliente(clienteXveiculo));
                lista.add(cliente);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }

        return lista;
    }

    public Cliente selectById(Cliente cliente) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setInt(1, cliente.getCodCliente());
            rs = stmt.executeQuery();
            if (rs.next()) {
                Estado estado = new Estado();
                estado.setCodEstado(rs.getInt("codEstado"));
                EstadoDAO estadoDAO = new EstadoDAO();
                estado = estadoDAO.selectById(estado);
                Cidade cidade = new Cidade();
                cidade.setCodCidade(rs.getInt("codCidade"));
                CidadeDAO cidadeDAO = new CidadeDAO();
                cidade = cidadeDAO.selectById(cidade);

                cliente.setCodCliente(rs.getInt("codCliente"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setEstado(estado);
                cliente.setCidade(cidade);
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setPeriodo(rs.getInt("periodo"));
                
                ClienteXVeiculo clienteXVeiculo = new ClienteXVeiculo();
                ClienteXveiculoDAO clienteXveiculoDAO = new ClienteXveiculoDAO();
                clienteXVeiculo.setCliente(cliente);
                cliente.setListaVeiculo(clienteXveiculoDAO.selectByCliente(clienteXVeiculo));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }

        return cliente;
    }

    public List<Cliente> selectByEstado(Cliente cliente) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYESTADO);
            stmt.setInt(1, cliente.getEstado().getCodEstado());
            rs = stmt.executeQuery();
            Estado estado = new Estado();
            estado.setCodEstado(cliente.getEstado().getCodEstado());
            EstadoDAO estadoDAO = new EstadoDAO();
            estado = estadoDAO.selectById(estado);
            while (rs.next()) {
                Cliente nCliente = new Cliente();

                Cidade cidade = new Cidade();
                cidade.setCodCidade(rs.getInt("codCidade"));
                CidadeDAO cidadeDAO = new CidadeDAO();
                cidade = cidadeDAO.selectById(cidade);

                nCliente.setCodCliente(rs.getInt("codCliente"));
                nCliente.setNome(rs.getString("nome"));
                nCliente.setEndereco(rs.getString("endereco"));
                nCliente.setEstado(estado);
                nCliente.setCidade(cidade);
                nCliente.setTelefone(rs.getString("telefone"));
                nCliente.setCelular(rs.getString("celular"));
                nCliente.setPeriodo(rs.getInt("periodo"));
                
                ClienteXVeiculo clienteXVeiculo = new ClienteXVeiculo();
                ClienteXveiculoDAO clienteXveiculoDAO = new ClienteXveiculoDAO();
                clienteXVeiculo.setCliente(nCliente);
                nCliente.setListaVeiculo(clienteXveiculoDAO.selectByCliente(clienteXVeiculo));
                
                lista.add(nCliente);

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }

        return lista;
    }

    public List<Cliente> selectByCidade(Cliente cliente) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList<Cliente>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYCIDADE);
            stmt.setInt(1, cliente.getCidade().getCodCidade());
            //Cidade de um mesmo estado
            Estado estado = new Estado();
            estado.setCodEstado(cliente.getEstado().getCodEstado());
            EstadoDAO estadoDAO = new EstadoDAO();
            estado = estadoDAO.selectById(estado);
            Cidade cidade = new Cidade();
            cidade.setCodCidade(cliente.getCidade().getCodCidade());
            CidadeDAO cidadeDAO = new CidadeDAO();
            cidade = cidadeDAO.selectById(cidade);            
            
            rs = stmt.executeQuery();            
            while (rs.next()) {
                Cliente nCliente = new Cliente();

                nCliente.setCodCliente(rs.getInt("codCliente"));
                nCliente.setNome(rs.getString("nome"));
                nCliente.setEndereco(rs.getString("endereco"));
                nCliente.setEstado(estado);
                nCliente.setCidade(cidade);
                nCliente.setTelefone(rs.getString("telefone"));
                nCliente.setCelular(rs.getString("celular"));
                nCliente.setPeriodo(rs.getInt("periodo"));
                ClienteXVeiculo clienteXVeiculo = new ClienteXVeiculo();
                ClienteXveiculoDAO clienteXveiculoDAO = new ClienteXveiculoDAO();
                clienteXVeiculo.setCliente(nCliente);
                nCliente.setListaVeiculo(clienteXveiculoDAO.selectByCliente(clienteXVeiculo));                
                
                lista.add(nCliente);

            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }

        return lista;
    }
}
