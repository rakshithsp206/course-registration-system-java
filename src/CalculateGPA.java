import java.sql.*;

class SGPA{
    String email;
    double gpa;
    int creditSum;
    int Sem;
    private Connection con=DBconnection.getInstance().getConnection();
    public SGPA(int Sem,String email) {
        this.email=email;
        this.Sem=Sem;
        this.gpa=0;
        this.creditSum=0;
    }
    public double calculate(){
        try {
            String query="SELECT grade,credit FROM student WHERE email=? AND sem=?";
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setString(1, email);
            ps.setInt(2, Sem);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                double grade=rs.getDouble(1);
                double credit=rs.getInt(2);
                gpa+=grade*credit;
                creditSum+=credit;
            }
            gpa/=creditSum;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return gpa;
    }
}

public class CalculateGPA {
    String email;
    int Sem;
    private Connection con=DBconnection.getInstance().getConnection();
    public CalculateGPA(String email,int Sem) {
        this.email = email;
        this.Sem=Sem;
    }
    
    public double calculateSGPA(int Sem){
        return new SGPA(Sem,email).calculate();
    }
    
    public double calculateCGPA(){
        double gpa=0;
        int CreditSum=0;
        for(int i=1;i<Sem;i++){
            SGPA sgpa=new SGPA(i,email);
            sgpa.calculate();
            gpa=sgpa.gpa*sgpa.creditSum;
            CreditSum=sgpa.creditSum;
        }
        gpa/=CreditSum;
        return gpa;
    }
}
