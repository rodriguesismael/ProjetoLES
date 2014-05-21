/*
 * Classe FaturaDAO
 */
package dao.faturaDAO;

import modelo.fatura.Fatura;
import dao.ConnectionFactory;
import modelo.movimento.Movimento;
import dao.movimentoDAO.MovimentoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.veiculo.Veiculo;

/**
 *
 * @author Ismael Rodrigues
 */
public class FaturaDAO {

    public static final String INSERT = "INSERT INTO Fatura (codMovimento,dataVencimento,status) VALUES(?,?,?)";
    public static final String SELECTALL = "SELECT codFatura,codMovimento,dataVencimento,dataPagamento FROM Fatura";
    public static final String SELECTBYID = "SELECT codFatura,codMovimento,dataVencimento,dataPagamento FROM Fatura WHERE codFatura = ?";
    public static final String SELECTINADIMPLENTEPORVEICULO = "SELECT a.codFatura, b.codMovimento, c.placa FROM Fatura AS a INNER JOIN FaturaXMovimento AS b ON a.codFatura = b.codFatura INNER JOIN Movimento AS c ON b.codMovimento = c.codMovimento WHERE c.placa = ? AND a.dataPagamento IS NULL";

    public void insert(Fatura fatura) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(INSERT);
            //stmt.setInt(1, fatura.getMovimento().getCodMovimento());
            stmt.setString(2, fatura.getDataVencimento());
            //fatura é gerada om status de 0: não paga
            stmt.setInt(3, 0);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt);
        }
    }

    public List<Fatura> selectAll() throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Fatura> lista = new ArrayList<Fatura>();
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Fatura fatura = new Fatura();
                fatura.setCodFatura(rs.getInt("codFatura"));
                fatura.setDataVencimento(rs.getString("dataVencimento"));
                fatura.setDataPagamento(rs.getString("dataPagamento"));
                Movimento movimento = new Movimento();
                movimento.setCodMovimento(rs.getInt("codMovimento"));
                MovimentoDAO movDAO = new MovimentoDAO();
                movimento = movDAO.selectById(movimento);
//                fatura.setMovimento(movimento);
                lista.add(fatura);

            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return lista;
    }

    public boolean selectInadimplentesPorVeiculo(Veiculo veiculo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean faturaAberta = false;
        try {
            con = ConnectionFactory.getConexao();
            stmt = con.prepareStatement(SELECTINADIMPLENTEPORVEICULO);
            stmt.setString(1, veiculo.getPlaca());
            rs = stmt.executeQuery();
            if (rs.next()) {
                faturaAberta = true;
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            ConnectionFactory.closeAll(con, stmt, rs);
        }
        return faturaAberta;
    }
}
