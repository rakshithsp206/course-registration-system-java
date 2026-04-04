import java.sql.*;
import java.util.Scanner;

public class Start {
    Scanner sc=new Scanner(System.in);
    private Connection con=DBconnection.getInstance().getConnection();
    String email;

    public void login(){
        System.out.print("Enter Email-ID: ");
        email=sc.next();
        System.out.print("Enter password: ");
        String password=sc.next();

        try {
            String query="SELECT * FROM user WHERE email=? AND password=?";
            PreparedStatement ps=con.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs=ps.executeQuery();

            if(rs.next())
                System.out.println("---LOGIN SUCCESSFUL---");
            else{
                System.out.println("---INCORRECT EMAIL ID OR PASSWORD---");
            }
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void signup(){
        System.out.print("Enter Email-ID: ");
        email=sc.next();
        System.out.print("Enter password: ");
        String password=sc.next();

        try {
            String query="INSERT INTO user VALUES(?,?)";
            PreparedStatement ps=con.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);

            ps.executeUpdate();
            System.out.println("---SIGNUP SUCCESSFUL---");
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
