/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 * @author ismael
 */
public class VeiculoDAO {
    public static final String INSERT ="INSERT INTO Veiculo (placa,tipo,codMarca,codModelo) VALUES(?,?,?,?)";
    public static final String UPDATE ="UPDATE Veiculo SET tipo = ?,codMarca = ?,codModelo = ? WHERE placa = ?";
    public static final String DELETE ="DELETE FROM Veiculo WHERE placa = ?";
    public static final String SELECTALL ="SELECT placa,tipo,codMarca,codModelo FROM Veciulo";
    public static final String SELECTBYID = "SELECT placa,tipo,codMarca,codModelo FROM Veciulo WHERE placa = ?";
    public static final String SELECTBYMARCA = "SELECT placa,tipo,codMarca,codModelo FROM Veciulo WHERE codMarca = ?";
    public static final String SELECTBYMODELO = "SELECT placa,tipo,codMarca,codModelo FROM Veciulo WHERE codModelo = ?";
    
    public void insert(Veiculo veiculo) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getTipo());
            stmt.setInt(3, veiculo.getMarca().getCodMarca());
            stmt.setInt(4, veiculo.getModelo().getCodModelo());
            stmt.executeUpdate();
            
        }catch(SQLException e){
            throw e;
        } finally{
            ConnectionFactory.closeAll(con, stmt);
        }
            
    }

    public void update(Veiculo veiculo) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(UPDATE);
            stmt.setString(1, veiculo.getTipo());
            stmt.setInt(2, veiculo.getMarca().getCodMarca());
            stmt.setInt(3, veiculo.getModelo().getCodModelo());
            stmt.setString(4, veiculo.getPlaca());
            stmt.executeUpdate();
            
        }catch(SQLException e){
            throw e;
        } finally{
            ConnectionFactory.closeAll(con, stmt);
        }
            
    }
    
    public void delete(Veiculo veiculo) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(DELETE);
            stmt.setString(1, veiculo.getPlaca());
            stmt.executeUpdate();            
        }catch(SQLException e){
            throw e;
        } finally{
            ConnectionFactory.closeAll(con, stmt);
        }
            
    }    
    
    public List<Veiculo> selectAll() throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Veiculo> lista = new ArrayList<Veiculo>();
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while(rs.next()){
                Veiculo veiculo = new Veiculo();
                Marca marca = new Marca();
                marca.setCodMarca(rs.getInt("codMarca"));
                MarcaDAO mDAO = new MarcaDAO();
                marca = mDAO.selectById(marca);
                
                Modelo modelo = new Modelo();
                modelo.setCodModelo(rs.getInt("codModelo"));
                ModeloDAO mdDAO = new ModeloDAO();
                modelo = mdDAO.selectById(modelo);
                
                veiculo.setMarca(marca);
                veiculo.setModelo(modelo);
                lista.add(veiculo);
            }
                    
        }catch (SQLException e){
            throw e;
        }finally{
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        
        return lista;
    }
    
    public Veiculo selectById(Veiculo veiculo) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setString(1, veiculo.getPlaca());
            rs = stmt.executeQuery();
            if(rs.next()){
                Marca marca = new Marca();
                marca.setCodMarca(rs.getInt("codMarca"));
                MarcaDAO mDAO = new MarcaDAO();
                marca = mDAO.selectById(marca);
                
                Modelo modelo = new Modelo();
                modelo.setCodModelo(rs.getInt("codModelo"));
                ModeloDAO mdDAO = new ModeloDAO();
                modelo = mdDAO.selectById(modelo);
                
                veiculo.setPlaca(rs.getString("codPlaca"));
                veiculo.setMarca(marca);
                veiculo.setModelo(modelo);
                
            }
        }catch(SQLException e){
            throw e;
        }finally{
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return veiculo;
    }
    
    public List<Veiculo> selectByMarca(Veiculo veiculo) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Veiculo> lista = new ArrayList<Veiculo>();
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYMARCA);
            stmt.setInt(1, veiculo.getMarca().getCodMarca());
            rs = stmt.executeQuery();
            
            Marca marca = new Marca();
            marca.setCodMarca(veiculo.getMarca().getCodMarca());
            MarcaDAO mDAO = new MarcaDAO();
            marca = mDAO.selectById(marca);            
            while(rs.next()){
                Veiculo nVeiculo = new Veiculo();
                
                Modelo modelo = new Modelo();
                modelo.setCodModelo(rs.getInt("codModelo"));
                ModeloDAO mdDAO = new ModeloDAO();
                modelo = mdDAO.selectById(modelo);
                
                nVeiculo.setMarca(marca);
                nVeiculo.setModelo(modelo);
                lista.add(nVeiculo);
            }
                    
        }catch (SQLException e){
            throw e;
        }finally{
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        
        return lista;
    }
    
    public List<Veiculo> selectByModelo(Veiculo veiculo) throws SQLException{
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Veiculo> lista = new ArrayList<Veiculo>();
        try{
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTBYMODELO);
            stmt.setInt(1, veiculo.getModelo().getCodModelo());
            rs = stmt.executeQuery();

            Modelo modelo = new Modelo();
            modelo.setCodModelo(veiculo.getModelo().getCodModelo());
            ModeloDAO mdDAO = new ModeloDAO();
            modelo = mdDAO.selectById(modelo);            
            while(rs.next()){
                Veiculo nVeiculo = new Veiculo();
                Marca marca = new Marca();
                marca.setCodMarca(rs.getInt("codMarca"));
                MarcaDAO mDAO = new MarcaDAO();
                marca = mDAO.selectById(marca);               
                
                nVeiculo.setMarca(marca);
                nVeiculo.setModelo(modelo);
                lista.add(nVeiculo);
            }
                    
        }catch (SQLException e){
            throw e;
        }finally{
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        
        return lista;
    }    
    
}
