package interfaces;

import java.sql.*;
import java.util.Properties;

public class SqlInterface  {
    // init database constants
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private String dataBaseURL;

    // init connection object
    protected Connection connection;
    // init properties object
    private Properties properties = new Properties();

    // create properties
    private Properties getProperties() {
        return properties;
    }
    public void setUser(String user){
        properties.setProperty("user", user);
    }

    public void setPassword(String password){
        properties.setProperty("password", password);
    }

    public void setMaxPool(String maxPool){
        properties.setProperty("MaxPooledStatements", maxPool);
    }

    public void setDataBaseURL(String dataBaseURL){
        this.dataBaseURL = dataBaseURL;
    }

    public void setProperties(Properties SqlProperties){
        this.properties = SqlProperties;

    }
    // connect database
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(dataBaseURL);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ResultSet sendQuery(String query) throws SQLException {

        this.connection = this.connect();
        Statement stmt =this.connection.createStatement();
        ResultSet result = stmt.executeQuery(query);
        return result;
    }

    public void sendUpdate(String query) throws SQLException{
        this.connection = this.connect();
        Statement stmt =this.connection.createStatement();
        stmt.executeUpdate(query);
    }

    public void sendUpdateWithParameters(PreparedStatement statement) throws SQLException{
        try{
            this.connection = this.connect();
            statement.executeUpdate();
            this.disconnect();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
