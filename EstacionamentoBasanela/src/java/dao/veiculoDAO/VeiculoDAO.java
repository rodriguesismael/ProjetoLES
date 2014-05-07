/*
 * Classe VeiculoDAO
 */
package dao.veiculoDAO;

import modelo.veiculo.Veiculo;
import dao.ConnectionFactory;
import dao.marcaDAO.MarcaDAO;
import dao.modeloDAO.ModeloDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.marca.Marca;
import modelo.modelo.Modelo;

/**
 *
 * @author Ismael Rodrigues
 */
public class VeiculoDAO {

    public static final String INSERT = "INSERT INTO Veiculo (placa, tipo, codMarca, codModelo) VALUES (?,?,?,?)";
    public static final String UPDATE = "UPDATE Veiculo SET tipo = ?, codMarca = ?, codModelo = ? WHERE placa = ?";
    //public static final String DELETE = "DELETE FROM Veiculo WHERE placa = ?";
    public static final String SELECTALL = "SELECT placa, tipo, codMarca, codModelo FROM Veiculo";
    public static final String SELECTBYID = "SELECT placa, tipo, codMarca, codModelo FROM Veiculo WHERE placa = ?";
    public static final String SELECTBYMARCA = "SELECT placa, tipo, codMarca, codModelo FROM Veiculo WHERE codMarca = ?";
    //public static final String SELECTBYMODELO = "SELECT placa, tipo, codMarca, codModelo FROM Veiculo WHERE codModelo = ?";

    public void insert(Veiculo veiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, veiculo.getPlaca());
            stmt.setInt(2, veiculo.getTipo());
            stmt.setInt(3, veiculo.getMarca().getCodMarca());
            stmt.setInt(4, veiculo.getModelo().getCodModelo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public void update(Veiculo veiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(UPDATE);
            stmt.setInt(1, veiculo.getTipo());
            stmt.setInt(2, veiculo.getMarca().getCodMarca());
            stmt.setInt(3, veiculo.getModelo().getCodModelo());
            stmt.setString(4, veiculo.getPlaca());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    /*public void delete(Veiculo veiculo) throws SQLException {
     Connection con = null;
     PreparedStatement stmt = null;
     try {
     con = ConnectionFactory.getConexao();
     stmt = con.prepareStatement(DELETE);
     stmt.setString(1, veiculo.getPlaca());
     stmt.executeUpdate();
     } catch (SQLException e) {
     throw e;
     } finally {
     ConnectionFactory.closeAll(con, stmt);
     }
     }*/
    public List<Veiculo> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Veiculo> lista = new ArrayList<Veiculo>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setTipo(rs.getInt("tipo"));
                Marca marca = new Marca();
                MarcaDAO marcaDAO = new MarcaDAO();
                marca.setCodMarca(rs.getInt("codMarca"));
                marca = marcaDAO.selectById(marca);
                veiculo.setMarca(marca);
                Modelo modelo = new Modelo();
                ModeloDAO modeloDAO = new ModeloDAO();
                modelo.setCodModelo(rs.getInt("codModelo"));
                modelo = modeloDAO.selectById(modelo);
                veiculo.setModelo(modelo);
                lista.add(veiculo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public Veiculo selectById(Veiculo veiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setString(1, veiculo.getPlaca());
            rs = stmt.executeQuery();
            if (rs.next()) {
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setTipo(rs.getInt("tipo"));
                Marca marca = new Marca();
                MarcaDAO marcaDAO = new MarcaDAO();
                marca.setCodMarca(rs.getInt("codMarca"));
                marca = marcaDAO.selectById(marca);
                veiculo.setMarca(marca);
                Modelo modelo = new Modelo();
                ModeloDAO modeloDAO = new ModeloDAO();
                modelo.setCodModelo(rs.getInt("codModelo"));
                modelo = modeloDAO.selectById(modelo);
                veiculo.setModelo(modelo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return veiculo;
    }

    public List<Veiculo> selectByMarca(Veiculo veiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Veiculo> lista = new ArrayList<Veiculo>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYMARCA);
            stmt.setInt(1, veiculo.getMarca().getCodMarca());
            rs = stmt.executeQuery();
            Marca marca = new Marca();
            MarcaDAO marcaDAO = new MarcaDAO();
            marca.setCodMarca(veiculo.getMarca().getCodMarca());
            marca = marcaDAO.selectById(marca);
            while (rs.next()) {
                Veiculo novoVeiculo = new Veiculo();
                novoVeiculo.setPlaca(rs.getString("placa"));
                novoVeiculo.setTipo(rs.getInt("tipo"));
                novoVeiculo.setMarca(marca);
                Modelo modelo = new Modelo();
                ModeloDAO modeloDAO = new ModeloDAO();
                modelo.setCodModelo(rs.getInt("codModelo"));
                modelo = modeloDAO.selectById(modelo);
                novoVeiculo.setModelo(modelo);
                lista.add(novoVeiculo);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    /*public List<Veiculo> selectByModelo(Veiculo veiculo) throws SQLException {
     Connection con = null;
     PreparedStatement stmt = null;
     ResultSet rs = null;
     List<Veiculo> lista = new ArrayList<Veiculo>();
     try {
     con = ConnectionFactory.getConexao();
     stmt = con.prepareStatement(SELECTBYMODELO);
     stmt.setInt(1, veiculo.getModelo().getCodModelo());
     rs = stmt.executeQuery();

     Modelo modelo = new Modelo();
     modelo.setCodModelo(veiculo.getModelo().getCodModelo());
     ModeloDAO mdDAO = new ModeloDAO();
     modelo = mdDAO.selectById(modelo);
     while (rs.next()) {
     Veiculo nVeiculo = new Veiculo();
     Marca marca = new Marca();
     marca.setCodMarca(rs.getInt("codMarca"));
     MarcaDAO mDAO = new MarcaDAO();
     marca = mDAO.selectById(marca);

     nVeiculo.setMarca(marca);
     nVeiculo.setModelo(modelo);
     lista.add(nVeiculo);
     }

     } catch (SQLException e) {
     throw e;
     } finally {
     ConnectionFactory.closeAll(con, stmt, rs);
     }

     return lista;
     }*/
}
