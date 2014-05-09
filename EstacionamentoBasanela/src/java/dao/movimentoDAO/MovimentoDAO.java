/*
 * Classe MovimentoDAO
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

    public static final String INSERT = "INSERT INTO Movimento (codCliente, placa, dataInicio, dataTermino) VALUES(?,?,?,?)";
    public static final String SELECTALL = "SELECT codMovimento, codCliente, placa, dataInicio, dataTermino FROM Movimento";
    public static final String SELECTBYID = "SELECT codMovimento, codCliente, placa, dataInicio, dataTermino FROM Movimento WHERE codMovimento = ?";
    public static final String SELECTBYCLIENTE = "SELECT codMovimento, codCliente, placa, dataInicio, dataTermino FROM Movimento WHERE codCliente = ?";
    public static final String SELECTBYVEICULO = "SELECT codMovimento, codCliente, placa, dataInicio, dataTermino FROM Movimento WHERE placa = ?";
    public static final String SELECTEMMOVIMENTO = "SELECT codMovimento FROM Movimento WHERE placa = ? AND dataTermino IS NULL";

    public void insert(Movimento movimento) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, movimento.getCliente().getCpf());
            stmt.setString(2, movimento.getVeiculo().getPlaca());
            stmt.setString(3, movimento.getDataInicio());
            stmt.setString(4, movimento.getDataTermino());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public List<Movimento> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movimento> lista = new ArrayList<Movimento>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Movimento movimento = new Movimento();
                movimento.setCodMovimento(rs.getInt("codMovimento"));
                Cliente cliente = new Cliente();
                ClienteDAO clienteDAO = new ClienteDAO();
                cliente.setCpf(rs.getString("codCliente"));
                if (cliente.getCpf() != null) {
                    cliente = clienteDAO.selectById(cliente);
                }
                movimento.setCliente(cliente);
                Veiculo veiculo = new Veiculo();
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo = veiculoDAO.selectById(veiculo);
                movimento.setVeiculo(veiculo);
                movimento.setDataInicio(rs.getString("dataInicio"));
                movimento.setDataTermino(rs.getString("dataTermino"));
                lista.add(movimento);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public Movimento selectById(Movimento movimento) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setInt(1, movimento.getCodMovimento());
            rs = stmt.executeQuery();
            if (rs.next()) {
                movimento.setCodMovimento(rs.getInt("codMovimento"));
                Cliente cliente = new Cliente();
                ClienteDAO clienteDAO = new ClienteDAO();
                cliente.setCpf(rs.getString("codCliente"));
                if (cliente.getCpf()!= null) {
                    cliente = clienteDAO.selectById(cliente);
                }
                movimento.setCliente(cliente);
                Veiculo veiculo = new Veiculo();
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo = veiculoDAO.selectById(veiculo);
                movimento.setVeiculo(veiculo);
                movimento.setDataInicio(rs.getString("dataInicio"));
                movimento.setDataTermino(rs.getString("dataTermino"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return movimento;
    }

    public List<Movimento> selectByCliente(Movimento movimento) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movimento> lista = new ArrayList<Movimento>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYCLIENTE);
            stmt.setString(1, movimento.getCliente().getCpf());
            rs = stmt.executeQuery();
            Cliente cliente = new Cliente();
            ClienteDAO clienteDAO = new ClienteDAO();
            cliente.setCpf(movimento.getCliente().getCpf());
            cliente = clienteDAO.selectById(cliente);
            while (rs.next()) {
                Movimento novoMovimento = new Movimento();
                novoMovimento.setCodMovimento(rs.getInt("codMovimento"));
                novoMovimento.setCliente(cliente);
                Veiculo veiculo = new Veiculo();
                VeiculoDAO veiculoDAO = new VeiculoDAO();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo = veiculoDAO.selectById(veiculo);
                novoMovimento.setVeiculo(veiculo);
                novoMovimento.setDataInicio(rs.getString("dataInicio"));
                novoMovimento.setDataTermino(rs.getString("dataTermino"));
                lista.add(novoMovimento);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public List<Movimento> selectByVeiculo(Movimento movimento) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Movimento> lista = new ArrayList<Movimento>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYVEICULO);
            stmt.setString(1, movimento.getVeiculo().getPlaca());
            rs = stmt.executeQuery();
            Veiculo veiculo = new Veiculo();
            VeiculoDAO veiculoDAO = new VeiculoDAO();
            veiculo.setPlaca(movimento.getVeiculo().getPlaca());
            veiculo = veiculoDAO.selectById(veiculo);
            while (rs.next()) {
                Movimento novoMovimento = new Movimento();
                novoMovimento.setCodMovimento(rs.getInt("codMovimento"));
                novoMovimento.setDataInicio(rs.getString("dataInicio"));
                novoMovimento.setDataTermino(rs.getString("dataTermino"));
                novoMovimento.setVeiculo(veiculo);
                Cliente cliente = new Cliente();
                ClienteDAO clienteDAO = new ClienteDAO();
                cliente.setCpf(rs.getString("codCliente"));
                if (cliente.getCpf()!= null) {
                    cliente = clienteDAO.selectById(cliente);
                    novoMovimento.setCliente(cliente);
                }
                lista.add(novoMovimento);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public boolean selectEmMovimento(Movimento movimento) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean retorno = false;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTEMMOVIMENTO);
            stmt.setString(1, movimento.getVeiculo().getPlaca());
            rs = stmt.executeQuery();
            if (rs.next()) {
                retorno = true;
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return retorno;
    }
}
