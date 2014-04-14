/*
 * Classe MarcaDAO
 */
package dao.marcaDAO;

import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.marca.Marca;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class MarcaDAO {

    private static final String INSERT = "INSERT INTO Marca (descricao) VALUES (?)";
    private static final String UPDATE = "UPDATE Marca SET descricao = ? WHERE codMarca = ?";
    private static final String DELETE = "DELETE Marca WHERE codMarca = ?";
    private static final String SELECTALL = "SELECT codMarca, descricao FROM Marca ORDER BY descricao";
    private static final String SELECTBYID = "SELECT codMarca, descricao FROM Marca WHERE codMarca = ?";

    public void insert(Marca marca) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, marca.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void update(Marca marca) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(UPDATE);
            stmt.setString(1, marca.getDescricao());
            stmt.setInt(2, marca.getCodMarca());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void delete(Marca marca) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(DELETE);
            stmt.setInt(1, marca.getCodMarca());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public List<Marca> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Marca> lista = new ArrayList<Marca>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setCodMarca(rs.getInt("codMarca"));
                marca.setDescricao(rs.getString("descricao"));
                lista.add(marca);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public Marca selectById(Marca marca) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setInt(1, marca.getCodMarca());
            rs = stmt.executeQuery();
            if (rs.next()) {
                marca.setCodMarca(rs.getInt("codMarca"));
                marca.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return marca;
    }
}
