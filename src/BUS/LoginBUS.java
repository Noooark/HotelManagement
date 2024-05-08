package BUS;

public class LoginBUS {
    DAO.LoginDAO dao = new DAO.LoginDAO();
    public Boolean AddAccount(int phone,String fullname,String usr,String pass,String conf,String email) throws Exception {
        return dao.AddAccount(phone, fullname, usr, pass, conf, email);
    }
    public Boolean DeleteAcccount(int ID) throws Exception{
        return dao.DeleteAcccount(ID);
    }
    public Boolean LoginCheck(String usr,String pass) throws Exception{
        return dao.LoginCheck(usr, pass);
    }
}
