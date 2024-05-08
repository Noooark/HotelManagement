package DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {
    private Connection conn;
    public DBconnect() throws Exception {
        String url = "jdbc:mysql://localhost:3306/hotelmanagement";
        String user = "root";
        String password = "holeanhkhoi123";
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Connecting Successfully.");
    }
    public Connection getConn() {

        return conn;
    }
}
