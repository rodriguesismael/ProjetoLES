/*
 * Classe CidadeDAO
 */
package dao.cidadeDAO;

import modelo.cidade.Cidade;
import dao.ConnectionFactory;
import dao.estadoDAO.EstadoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.estado.Estado;

/**
 *
 * @author Ismael
 */
public class CidadeDAO {

    public static final String INSERT = "INSERT INTO Cidade (codEstado, descricao) VALUES (?,?)";
    public static final String UPDATE = "UPDATE Cidade SET codEstado = ?, descricao = ? WHERE codCidade = ?";
    public static final String DELETE = "DELETE FROM Cidade WHERE codCidade = ?";
    public static final String SELECTALL = "SELECT codCidade, codEstado, descricao FROM Cidade ORDER BY descricao";
    public static final String SELECTBYID = "SELECT codCidade, codEstado, descricao FROM Cidade WHERE codCidade = ?";
    public static final String SELECTBYESTADO = "SELECT codCidade, codEstado, descricao FROM Cidade WHERE codEstado = ? ORDER BY descricao";

    public void insert(Cidade cidade) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setInt(1, cidade.getEstado().getCodEstado());
            stmt.setString(2, cidade.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void update(Cidade cidade) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(UPDATE);
            stmt.setInt(1, cidade.getEstado().getCodEstado());
            stmt.setString(2, cidade.getDescricao());
            stmt.setInt(3, cidade.getCodCidade());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void delete(Cidade cidade) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(DELETE);
            stmt.setInt(1, cidade.getCodCidade());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public List<Cidade> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cidade> lista = new ArrayList<Cidade>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Cidade cidade = new Cidade();
                cidade.setCodCidade(rs.getInt("codCidade"));
                Estado estado = new Estado();
                EstadoDAO estadoDAO = new EstadoDAO();
                estado.setCodEstado(rs.getInt("codEstado"));
                estado = estadoDAO.selectById(estado);
                cidade.setEstado(estado);
                cidade.setDescricao(rs.getString("descricao"));
                lista.add(cidade);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public Cidade selectById(Cidade cidade) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setInt(1, cidade.getCodCidade());
            rs = stmt.executeQuery();
            if (rs.next()) {
                cidade.setCodCidade(rs.getInt("codCidade"));
                Estado estado = new Estado();
                EstadoDAO estadoDAO = new EstadoDAO();
                estado.setCodEstado(rs.getInt("codEstado"));
                estado = estadoDAO.selectById(estado);
                cidade.setEstado(estado);
                cidade.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return cidade;
    }

    public List<Cidade> selectByEstado(Cidade cidade) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cidade> lista = new ArrayList<Cidade>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYESTADO);
            stmt.setInt(1, cidade.getEstado().getCodEstado());
            rs = stmt.executeQuery();
            // Recupera os dados do Estado - INICIO
            Estado estado = new Estado();
            EstadoDAO estadoDAO = new EstadoDAO();
            estado.setCodEstado(cidade.getEstado().getCodEstado());
            estado = estadoDAO.selectById(estado);
            // Recupera os dados do Estado - FIM
            while (rs.next()) {
                Cidade nCidade = new Cidade();
                nCidade.setCodCidade(rs.getInt("codCidade"));
                nCidade.setEstado(estado);
                nCidade.setDescricao((rs.getString("descricao")));
                lista.add(nCidade);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }
}
