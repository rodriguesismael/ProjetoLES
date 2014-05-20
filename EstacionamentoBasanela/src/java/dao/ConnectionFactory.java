/*
 * Classe ConnectionFactory
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Alvaro Augusto Roberto
 * @since 12/04/2014
 */
public class ConnectionFactory {

    private static final String STR_DRIVER = "com.mysql.jdbc.Driver";
<<<<<<< HEAD
    private static final String DATABASE = "basanelapark";//basanelapark
    private static final String STR_CON = "jdbc:mysql://127.0.0.1:3306/" + DATABASE;//127.0.0.1:3306/
    private static final String USER = "root";//root
    private static final String PASSWORD = "19930730";//19930730
=======
    private static final String DATABASE = "basanelapark";
    private static final String STR_CON = "jdbc:mysql://127.0.0.1:3306/" + DATABASE;
    private static final String USER = "root";
    private static final String PASSWORD = "";//19930730
>>>>>>> c1d52b73abbaff3725283169d9c4428db710efc3

    public static Connection getConexao() {
        Connection conn = null;
        try {
            Class.forName(STR_DRIVER);
            conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void closeAll(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeAll(Connection conn, Statement stmt) {
        try {
            if (conn != null) {
                closeAll(conn);
            }
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (conn != null || stmt != null) {
                closeAll(conn, stmt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
