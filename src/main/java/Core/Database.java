package Core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Cordeiro
 */
public class Database {

    private static String status = "Não conectado...";

    public static Connection createConexao() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

        Connection connection = null;

        String serverName = "localhost"; // caminho do servidor do BD

        String mydatabase = "iclinic"; // nome do DB

        String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

        String username = "root"; // usuário do BD

        String password = ""; // senha de acesso

        connection = DriverManager.getConnection(url, username, password);

        Class.forName("com.mysql.jdbc.Driver");

        //Testa a conexão com o banco
        if (connection != null) {
            status = ("STATUS: Conectado com sucesso!");
        } else {
            status = ("STATUS: Não foi possivel realizar conexão");
        }

        return connection;
    }

    // Responsável por executar selects no banco de dados 
    public static ResultSet execSelect(Connection conn, String sql) throws SQLException {
        Statement query = conn.createStatement();
        return query.executeQuery(sql);
    }

    // Responsável por executar inserts e updates no banco de dados 
    public static java.sql.PreparedStatement prepareSql(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    // Retorna o status da conexão
    public static String statusConection() {
        return status;
    }
}
