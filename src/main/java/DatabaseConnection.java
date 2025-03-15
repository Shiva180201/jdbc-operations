import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private Connection connection;

    DatabaseConnection(String filepath) {
        try {
            //Properties for reading properties as key-value pair
            Properties properties = new Properties();

            //Properties object requires one File Input Stream to open and read the data byte by byte
            FileInputStream fis = new FileInputStream(filepath);
            properties.load(fis);

            //Getting properties
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String driver = properties.getProperty("db.driver");

            //For starting JDBC driver
            Class.forName(driver);

            //To establish a Database Connection
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("\nDatabase Connection Established");

        } catch (Exception e) {
            System.out.println("Database Connection not established "+e.getMessage());
            e.printStackTrace();
        }
    }

    //Method to Retrieve the Connection for other classes
    public Connection getConnection() {
        return connection;
    }

    //Method to close the Connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("\nDatabase Connection Closed");
            }
        } catch (SQLException e) {
            System.out.println("Error while closing connection: "+e.getMessage());
        }
    }
}
