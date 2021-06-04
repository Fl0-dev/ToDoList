package dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *Classe qui contient l'ouverture
 * de connexion à la DB
 */
public class JdbcTools {
    private static final String URL =Settings.getPropriete("url");

    /**
     * méthode qui permet l'ouverture de la connexion
     * @return la connection
     * @throws SQLException
     */
    public static Connection recupConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        return connection;
    }

}
