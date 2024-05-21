package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    ResultSet result;
    int check;
    public boolean AddAccount(int phone,String fullname,String usr,String pass,String conf,String email) throws Exception{
        Connection conn= new DBconnect().getConn();
        String query="INSERT INTO account ( phone,fullname,username,password,confirmpass,email ) VALUE(?,?,?,?,?,?)";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setInt(1,phone);
        ps.setString(2,fullname);
        ps.setString(3,usr);
        ps.setString(4,pass);
        ps.setString(5,conf);
        ps.setString(6,email);
        check = ps.executeUpdate();
        conn.close();
        return check>0;
    }
    public boolean DeleteAcccount(int ID) throws Exception {
        Connection conn= new DBconnect().getConn();
        String query="DELETE FORM account WHERE id =?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setInt(1,ID);
        check = ps.executeUpdate();
        conn.close();
        return check>0;
    }
    public boolean LoginCheck(String usr,String pass) throws Exception{
        Connection conn= new DBconnect().getConn();
        String query ="SELECT * FROM account WHERE username=? AND password=?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1,usr);
        ps.setString(2,pass);
        result = ps.executeQuery();
        while(result.next())
        {
            if(result.getString ("username").equals(usr) && result.getString("password").equals(pass))
            {
                return true;
            }
        }
        result.close();
        conn.close();
        return false;

    }
//    public void ResetPass(String newpass,String usr) throws Exception
//    {
//        Connection conn= new DBconnect().getConn();
//        String query = "UPDATE account SET password=? WHERE username=?";
//        PreparedStatement ps=conn.prepareStatement(query);
//        ps.setString(1,newpass);
//        ps.setString(2,usr);
//        check= ps.executeUpdate();
//        conn.close();
//    }
            public boolean ResetPass(String newpass, String usr) throws Exception {
                String query = "UPDATE account SET password=? WHERE username=?";
                try (Connection conn = new DBconnect().getConn();
                     PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, newpass);
                    ps.setString(2, usr);
                    int check = ps.executeUpdate();
                    return check > 0;
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
}
