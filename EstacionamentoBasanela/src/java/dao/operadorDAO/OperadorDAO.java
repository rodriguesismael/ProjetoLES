/*
 * classe OperadorDAO
 */
package dao.operadorDAO;

import dao.ConnectionFactory;
import dao.tipoAcessoDAO.TipoAcessoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.operador.Operador;
import modelo.tipoAcesso.TipoAcesso;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class OperadorDAO {
    
    private final String INSERT = "";
    private final String UPDATE = "";
    private final String SELECTALL = "";
    private final String SELECTBYID = "";
    private final String LOGIN = "";
    
    public Operador login(Operador operador) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        con = ConnectionFactory.getConexao();
        try {
            stmt = con.prepareStatement(LOGIN);
            rs = stmt.executeQuery();
            if (rs.next()) {
                operador.setCodOperador(rs.getInt("codOperador"));
                operador.setNome(rs.getString("nome"));
                operador.setLogin(rs.getString("login"));
                operador.setSenha(rs.getString("senha"));
                TipoAcesso tipoAcesso = new TipoAcesso();
                TipoAcessoDAO tipoAcessoDAO = new TipoAcessoDAO();
                tipoAcesso.setCodTipoAcesso(rs.getInt("codTipoAcesso"));
                tipoAcesso = tipoAcessoDAO.selectByID(tipoAcesso);
                operador.setTipoAcesso(tipoAcesso);
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return operador;
    }
}
