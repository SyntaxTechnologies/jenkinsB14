package utils;

import java.sql.*;

public class DBUtility {

    private static ResultSet rset;

    public static ResultSet getResultSet(String sqlQuery) {

        Connection conn=null;
        Statement statement=null;
        try {
            conn = DriverManager.getConnection(
                    ConfigReader.getPropertyValue("dbUrl"),
                    ConfigReader.getPropertyValue("dbUsername"),
                    ConfigReader.getPropertyValue("dbPassword"));
            statement = conn.createStatement();
            rset = statement.executeQuery(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

        return rset;
    }


}
