/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.modeloDAO;

import modelo.modelo.Modelo;

import dao.ConnectionFactory;
import dao.marcaDAO.MarcaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.marca.Marca;

/**
 *
 * @author Ismael
 */
public class ModeloDAO {

    public static final String INSERT = "INSERT INTO Modelo (codMarca,descricao) VALUES (?,?)";
    public static final String UPDATE = "UPDATE Modelo SET descricao = ? WHERE codModelo=?";
    public static final String DELETE = "DELETE FROM Modelo WHERE codModelo = ?";
    public static final String SELECTALL = "SELECT codModelo, codMarca, descricao FROM Modelo";
    public static final String SELECTBYID = "SELECT codModelo, codMarca, descricao FROM Modelo WHERE codModelo = ?";
    public static final String SELECTBYMARCA = "SELECT codModelo, descricao FROM Modelo WHERE codMarca = ?";

    public int insert(Modelo modelo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int codModelo = 0;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setInt(1, modelo.getMarca().getCodMarca());
            stmt.setString(2, modelo.getDescricao());
            stmt.executeUpdate();
            rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS codModelo FROM Modelo");
            if (rs.next()) {
                codModelo = rs.getInt("codModelo");
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
        return codModelo;
    }

    public void update(Modelo modelo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(UPDATE);
            stmt.setString(1, modelo.getDescricao());
            stmt.setInt(2, modelo.getCodModelo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void delete(Modelo modelo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(DELETE);
            stmt.setInt(1, modelo.getCodModelo());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public List<Modelo> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Modelo> lista = new ArrayList<Modelo>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Modelo modelo = new Modelo();
                Marca marca = new Marca();
                MarcaDAO marcaDao = new MarcaDAO();
                marca.setCodMarca(rs.getInt("codMarca"));
                marca = marcaDao.selectById(marca);
                modelo.setCodModelo(rs.getInt("codModelo"));
                modelo.setMarca(marca);
                lista.add(modelo);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public Modelo selectById(Modelo modelo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setInt(1, modelo.getCodModelo());
            rs = stmt.executeQuery();
            if (rs.next()) {
                modelo.setCodModelo(rs.getInt("codModelo"));
                Marca marca = new Marca();
                MarcaDAO marcaDAO = new MarcaDAO();
                marca.setCodMarca(rs.getInt("codMarca"));
                marca = marcaDAO.selectById(marca);
                modelo.setMarca(marca);
                modelo.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return modelo;
    }

    public List<Modelo> selectByMarca(Modelo modelo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Modelo> lista = new ArrayList<Modelo>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYMARCA);
            stmt.setInt(1, modelo.getMarca().getCodMarca());
            rs = stmt.executeQuery();
            Marca marca = new Marca();
            MarcaDAO marcaDAO = new MarcaDAO();
            marca.setCodMarca(modelo.getMarca().getCodMarca());
            marca = marcaDAO.selectById(marca);
            while (rs.next()) {
                Modelo m = new Modelo();
                m.setCodModelo(rs.getInt("codModelo"));
                m.setDescricao(rs.getString("descricao"));
                m.setMarca(marca);
                lista.add(m);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }
}
