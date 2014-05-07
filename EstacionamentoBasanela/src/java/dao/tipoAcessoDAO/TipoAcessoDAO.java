/*
 * Classe TipoAcessoDAO
 */
package dao.tipoAcessoDAO;

import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.tipoAcesso.TipoAcesso;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class TipoAcessoDAO {

    private final String SELECTALL = "SELECT codTipoAcesso, descricao FROM TipoAcesso ORDER BY descricao";
    private final String SELECTBYID = "SELECT codTipoAcesso, descricao FROM TipoAcesso WHERE codTipoAcesso = ?";

    public List<TipoAcesso> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TipoAcesso> lista = new ArrayList<TipoAcesso>();
        con = ConnectionFactory.getConexao();
        try {
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TipoAcesso tipoAcesso = new TipoAcesso();
                tipoAcesso.setCodTipoAcesso(rs.getInt("codTipoAcesso"));
                tipoAcesso.setDescricao(rs.getString("descricao"));
                lista.add(tipoAcesso);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public TipoAcesso selectById(TipoAcesso tipoAcesso) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        con = ConnectionFactory.getConexao();
        try {
            stmt = con.prepareStatement(SELECTBYID);
            stmt.setInt(1, tipoAcesso.getCodTipoAcesso());
            rs = stmt.executeQuery();
            if (rs.next()) {
                tipoAcesso.setCodTipoAcesso(rs.getInt("codTipoAcesso"));
                tipoAcesso.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return tipoAcesso;
    }
}
