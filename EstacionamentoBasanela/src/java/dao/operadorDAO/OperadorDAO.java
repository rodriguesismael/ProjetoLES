/*
 * classe OperadorDAO
 */
package dao.operadorDAO;

import dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.operador.Operador;

/**
 *
 * @author Alvaro Augusto Roberto
 */
public class OperadorDAO {

    private final String INSERT = "";
    private final String UPDATE = "";
    private final String SELECTALL = "";
    private final String SELECTBYID = "";
    private final String LOGIN = "SELECT codOperador, nome, login, administrador, status FROM Operador WHERE login = ? and senha = MD5(?) AND status = ?";

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
}
