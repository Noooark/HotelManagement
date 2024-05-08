package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    public void ResetPass(String newpass,String email) throws Exception
    {
        Connection conn= new DBconnect().getConn();
        String query = "UPDATE account SET password=? WHERE email=?";
        PreparedStatement ps=conn.prepareStatement(query);
        ps.setString(1,newpass);
        ps.setString(2,email);
        check= ps.executeUpdate();
        conn.close();
    }

}
