
import java.sql.*;
import java.util.Scanner;

public class Professor extends User{
    Scanner sc=new Scanner(System.in);
    private Connection con=DBconnection.getInstance().getConnection();
    public Professor(String email) {
        super(email);
    }
    
    @Override
    public void menu(){
        while (true) { 
            System.out.println("=====================MENU=====================");
            System.out.println("1.Manage Courses");
            System.out.println("2.View Enrolled Students");
            System.out.println("3.View Feedback");
            System.out.println("0.Logout");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();
            sc.nextLine();
            System.out.println("==============================================");

            switch(choice){
                case 1:
                    System.out.println();
                    ManageCourses();
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    viewStudents();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    viewFeedback();
                    System.out.println();
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

    private void ManageCourses(){
        while (true) { 
            System.out.println("----------------------------------------------");
            System.out.println("1.View Courses");
            System.out.println("2.Update Timings");
            System.out.println("3.Update Credits");
            System.out.println("4.Update Pre-requsites");
            System.out.println("0.Go back");
            System.out.print("Enter your Choice: ");
            int choice=sc.nextInt();
            sc.nextLine();
            System.out.println("----------------------------------------------");
            switch(choice){
                case 1:
                    System.out.println();
                    ViewCourses();
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    UpdateTimings();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    UpdateCredit();
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    UpdatePre();
                    System.out.println();
                    break;
                case 0:
                    System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
        }
    }

    private void ViewCourses(){
        System.out.print("Enter Your Name: ");
        String name=sc.nextLine();
        try {
            String query="Select * from courses where professor=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, name);
            ResultSet rs=ps.executeQuery();

            TablePrinter.printResultSet(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void UpdateTimings(){
        System.out.print("Enter Course Code to Update: ");
        String code=sc.next();
        sc.nextLine();
        System.out.print("Enter new Timings: ");
        String time=sc.nextLine();
        try {
            String query="UPDATE courses SET timings=? WHERE code=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, time);
            ps.setString(2, code);
            ps.executeUpdate();
            System.out.println("---UPDATED SUCCESSFULLY---");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void UpdateCredit(){
        System.out.print("Enter Course Code to Update: ");
        String code=sc.next();
        sc.nextLine();
        System.out.print("Enter new Credit: ");
        int credit=sc.nextInt();
        try {
            String query="UPDATE courses SET credit=? WHERE code=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1, credit);
            ps.setString(2, code);
            ps.executeUpdate();
            System.out.println("---UPDATED SUCCESSFULLY---");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void UpdatePre(){
        System.out.print("Enter Course Code to Update: ");
        String code=sc.next();
        sc.nextLine();
        System.out.print("Enter new Timings: ");
        String pre=sc.nextLine();
        try {
            String query="UPDATE courses SET pre=? WHERE code=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, pre);
            ps.setString(2, code);
            ps.executeUpdate();
            System.out.println("---UPDATED SUCCESSFULLY---");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewStudents(){
        System.out.print("Enter Course Code: ");
        String code=sc.next();
        try {
            String query="SELECT email FROM student WHERE courseid=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, code);
            ResultSet rs=ps.executeQuery();
            System.out.println("---STUDENT ID LIST---");
            while(rs.next()){
                System.out.printf("\t%.8s",rs.getString(1).toUpperCase());
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void viewFeedback(){
        System.out.print("Enter Course Code: ");
        String code=sc.next();
        try {
            String query="SELECT * FROM feedback WHERE course_code=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, code);
            ResultSet rs=ps.executeQuery();
            TablePrinter.printResultSet(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

