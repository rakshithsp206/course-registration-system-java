import java.sql.*;
import java.util.Scanner;
public class Student extends User 
{   
    int Sem;
    private Connection con=DBconnection.getInstance().getConnection();
    
    public Student(String email) {
        super(email);
    }
    
    Scanner sc=new Scanner(System.in);
    
    public void menu(){
        
        System.out.print("Semester: ");
        Sem=sc.nextInt();
        System.out.println();

        while (true) { 
            System.out.println("=====================MENU=====================");
            System.out.println("1.View Available Courses");
            System.out.println("2.Register for Courses");
            System.out.println("3.View Schedule");
            System.out.println("4.Track Academic Progress");
            System.out.println("5.Drop Courses");
            System.out.println("6.Submit Complaints");
            System.out.println("7.Give Feedback");
            System.out.println("0.Logout");
            System.out.print("Enter your Choice: ");
            
            int choice=sc.nextInt();
            sc.nextLine();
            System.out.println("==============================================");

            switch(choice){
                case 1:
                    ViewCourses();
                    break;
                case 2:
                    Register();
                    break;
                case 3:
                    ViewSchedule();
                    break;
                case 4:
                    TrackProgress();
                    break;
                case 5:
                    DropCourse();
                    break;
                case 6:
                    Complaint();
                    break;
                case 7:
                    giveFeedback();
                    break;
                case 0:
                    System.out.println();
                    System.out.println("===========LOGGED OUT SUCCESSFULLY============");
                    return;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }

    }

    private void ViewCourses(){
        System.out.println();
        try {
            String query="Select * from courses where sem=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, Sem);
            ResultSet rs=ps.executeQuery();
            TablePrinter.printResultSet(rs);
        
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void Register(){
        System.out.println();
        try {
            System.out.print("Enter Course ID to Register: ");
            String code=sc.next();
            String query="INSERT INTO student(email,courseid,sem) VALUES(?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, code);
            ps.setInt(3, Sem);

            ps.executeUpdate();
            System.out.println("---REGISTERED SUCCESSFULLY---");
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void ViewSchedule(){
        System.out.println();
        try {
            String query="SELECT courseid FROM student WHERE email=? AND sem=?";
            PreparedStatement ps=con.prepareStatement(query); 
            ps.setString(1, email);
            ps.setInt(2, Sem);

            ResultSet rs=ps.executeQuery();
            ResultSetMetaData meta=rs.getMetaData();
            int cols=meta.getColumnCount();
            
            StringBuilder qry=new StringBuilder("SELECT code,proffessor,timings,location FROM courses WHERE code IN(");

            for(int i=0;i<cols;i++){
                qry.append("?");
                if(i<cols-1){
                    qry.append(",");
                }
            }
            qry.append(")");

            ps=con.prepareStatement(qry.toString());
            for(int i=0;i<cols;i++){
                rs.next();
                ps.setString(i+1,rs.getString(1));
            }

            rs=ps.executeQuery();
            TablePrinter.printResultSet(rs);
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void DropCourse(){
        System.out.println();
        try {
            System.out.print("Enter Course ID to be DROPPED: ");
            String code=sc.next();

            String query="DELETE FROM student WHERE email=? AND courseid=?";
            PreparedStatement ps=con.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, code);

            ps.executeUpdate();
            System.out.println("---DROPPED SUCCESSFULLY---");
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void Complaint(){
        System.out.println();
        try {
            System.out.println("Please type your complaint...");
            String complaint=sc.nextLine();
            String query="INSERT INTO complaints(description) VALUES(?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, complaint);
            ps.executeUpdate();
            System.out.println("---COMPLAINT REGISTERED SUCCESSFULLY---");
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
    }

    private void TrackProgress(){
        System.out.println();
        while (true) { 
            System.out.println("----------------------------------------------");
            System.out.println("1.SGPA");
            System.out.println("2.CGPA");
            System.out.println("0.Go Back");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();
            sc.nextLine();
            System.out.println("----------------------------------------------");

            CalculateGPA calc=new CalculateGPA(email, Sem);
            switch(choice){
                case 1:
                    System.out.print("Enter Semester for SGPA: ");
                    int sem=sc.nextInt();
                    System.out.println("SGPA for Semester "+sem+"is"+calc.calculateSGPA(sem));
                    break;
                case 2:
                    System.out.println("Your CGPA is "+calc.calculateCGPA());
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    System.out.println();
                    return;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private void giveFeedback(){
        System.out.println();
        System.out.print("Enter Course Code: ");
        String code=sc.next();
        sc.nextLine();
        System.out.print("Feedback: ");
        String feedback=sc.nextLine();
        try {
            String query="INSERT INTO feedback VALUES(?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, code);
            ps.setString(2, feedback);

            ps.executeUpdate();
            System.out.println("---FEEDBACK SUCCESSFUL---");
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

