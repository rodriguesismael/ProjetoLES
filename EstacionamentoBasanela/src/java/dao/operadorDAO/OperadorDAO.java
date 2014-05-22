/*
 * classe OperadorDAO
 */
package dao.operadorDAO;

import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.operador.Operador;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class OperadorDAO {

    private final String INSERT = "INSERT INTO Operador (nome, login, senha, administrador, status) VALUES (?,?,MD5(?),?,?)";
    private final String UPDATE = "UPDATE Operador SET nome = ?, login = ?, administrador = ?, status = ? WHERE codOperador = ?";
    private final String UPDATECOMSENHA = "UPDATE Operador SET nome = ?, login = ?, senha = MD5(?), administrador = ?, status = ? WHERE codOperador = ?";
    private final String SELECTALL = "SELECT codOperador, nome, login, administrador, status FROM Operador ORDER BY nome";
    private final String SELECTBYID = "SELECT codOperador, nome, login, administrador, status FROM Operador WHERE codOperador = ?";
    private final String SELECTBYLOGIN = "SELECT codOperador FROM Operador WHERE login = ?";
    private final String LOGIN = "SELECT codOperador, nome, login, administrador, status FROM Operador WHERE login = ? and senha = MD5(?) AND status = ?";

    public void insert(Operador operador) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, operador.getNome());
            stmt.setString(2, operador.getLogin());
            stmt.setString(3, operador.getSenha());
            stmt.setBoolean(4, operador.isAdministrador());
            stmt.setBoolean(5, operador.isStatus());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void update(Operador operador) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(UPDATE);
            stmt.setString(1, operador.getNome());
            stmt.setString(2, operador.getLogin());
            stmt.setBoolean(3, operador.isAdministrador());
            stmt.setBoolean(4, operador.isStatus());
            stmt.setInt(5, operador.getCodOperador());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void updateComSenha(Operador operador) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(UPDATECOMSENHA);
            stmt.setString(1, operador.getNome());
            stmt.setString(2, operador.getLogin());
            stmt.setString(3, operador.getSenha());
            stmt.setBoolean(4, operador.isAdministrador());
            stmt.setBoolean(5, operador.isStatus());
            stmt.setInt(6, operador.getCodOperador());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public Operador login(Operador operador) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        con = ConnectionFactory.getConexao();
        try {
            stmt = con.prepareStatement(LOGIN);
            stmt.setString(1, operador.getLogin());
            stmt.setString(2, operador.getSenha());
            stmt.setBoolean(3, true);
            rs = stmt.executeQuery();
            if (rs.next()) {
                operador.setCodOperador(rs.getInt("codOperador"));
                operador.setNome(rs.getString("nome"));
                operador.setLogin(rs.getString("login"));
                operador.setAdministrador(rs.getBoolean("administrador"));
                operador.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return operador;
    }

    public List<Operador> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Operador> lista = new ArrayList<Operador>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Operador operador = new Operador();
                operador.setCodOperador(rs.getInt("codOperador"));
                operador.setNome(rs.getString("nome"));
                operador.setLogin(rs.getString("login"));
                operador.setAdministrador(rs.getBoolean("administrador"));
                operador.setStatus(rs.getBoolean("status"));
                lista.add(operador);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public Operador selectById(Operador operador) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setInt(1, operador.getCodOperador());
            rs = stmt.executeQuery();
            if (rs.next()) {
                operador.setCodOperador(rs.getInt("codOperador"));
                operador.setNome(rs.getString("nome"));
                operador.setLogin(rs.getString("login"));
                operador.setAdministrador(rs.getBoolean("administrador"));
                operador.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return operador;
    }

    public boolean selectByLogin(Operador operador) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean existeLogin = false;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYLOGIN);
            stmt.setString(1, operador.getLogin());
            rs = stmt.executeQuery();
            if (rs.next()) {
                existeLogin = true;
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return existeLogin;
    }
}
