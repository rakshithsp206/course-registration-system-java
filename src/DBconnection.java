import java.sql.*;

public class DBconnection {
    private static DBconnection instance;
    private Connection con;

    private String url = "jdbc:mysql://localhost:3306/mis";
    private String user = "root";
    private String password = "Raxi@206";

    private DBconnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static synchronized DBconnection getInstance(){
        if(instance==null){
            instance=new DBconnection();
        }
        return instance;
    }

    public Connection getConnection(){
        try{
            if(con==null || con.isClosed())
                con = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public void closeConnection(){
        try {
            if(con!=null && !con.isClosed()){
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
