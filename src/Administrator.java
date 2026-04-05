import java.sql.*;
import java.util.Scanner;

public class Administrator extends User{
    Scanner sc=new Scanner(System.in);
    private Connection con=DBconnection.getInstance().getConnection();

    public Administrator(String email) {
        super(email);
    }
    @Override

    public void menu(){
        while (true) { 
            System.out.println("=====================MENU=====================");
            System.out.println("1.Manage Course Catalog");
            System.out.println("2.Manage Student Records");
            System.out.println("3.Assign Professors to Courses");
            System.out.println("4.Handle Complaints");
            System.out.println("0.Logout");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();
            sc.nextLine();
            System.out.println("==============================================");
            switch (choice) {
                case 1:
                    System.out.println();
                    ManageCourse();
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    ManageStudents();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    assignProfessor();
                    System.out.println();
                    break;
                case 4:
                    System.out.println();
                    HandleComplaint();
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

    private void ManageCourse(){
        while (true) { 
            System.out.println("----------------------------------------------");
            System.out.println("1.Add Course");
            System.out.println("2.Delete Course");
            System.out.println("3.View Courses");
            System.out.println("0.Go Back");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();
            sc.nextLine();
            System.out.println("----------------------------------------------");
            switch(choice){
                case 1:
                    System.out.println();
                    addCourse();
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    deleteCourse();
                    System.out.println();
                    break;
                case 3:
                    System.out.println();
                    viewCourse();
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

    private void viewCourse(){
        try {
            Statement stmt=con.createStatement();
            String query="Select * from courses";
            ResultSet rs=stmt.executeQuery(query);
            TablePrinter.printResultSet(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addCourse(){
        System.out.print("Enter Course Code: ");
        String code=sc.next();
        System.out.print("Enter Course Title: ");
        String title=sc.next();
        System.out.println("Enter Semester: ");
        int sem=sc.nextInt();

        try {
            String query="INSERT INTO courses(code,title,sem) VALUES(?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, code);
            ps.setString(2, title);
            ps.setInt(3, sem);
            ps.executeUpdate();
            System.out.println("---COURSE ADDED SUCCESSFULLY---");
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    }

    private void deleteCourse(){
        System.out.print("Enter Course Code: ");
        String code=sc.next();
        try {
            String query="DELETE FROM courses WHERE code=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, code);
            ps.executeUpdate();
            System.out.println("---COURSE DELETED SUCCESSFULLY---");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void ManageStudents(){
        System.out.print("Enter Student ID: ");
        String id=sc.next();
        System.out.print("Enter Course Code: ");
        String code=sc.next();
        System.out.print("Enter Grade: ");
        double grade=sc.nextDouble();
        try {
            String query="UPDATE student SET grade=? WHERE email LIKE ? AND courseid=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(2, id+"%");
            ps.setString(3, code);
            ps.setDouble(1, grade);
            ps.executeUpdate();
            System.out.println("---GRADE ASSIGNED SUCCESSFULLY---");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void assignProfessor(){
        System.out.print("Enter Course Code: ");
        String code=sc.next();
        sc.nextLine();
        System.out.println("Enter Professor name: ");
        String name=sc.nextLine();
        try {
            String query="UPDATE courses SET professor=? WHERE code=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, code);
            ps.executeUpdate();
            System.out.println("---PROFESSOR ASSIGNED SUCCESSFULLY---");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void HandleComplaint(){
        while (true) { 
            System.out.println("----------------------------------------------");
            System.out.println("1.View Complaints");
            System.out.println("2.Update Complaint Status");
            System.out.println("0.Go Back");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();
            sc.nextLine();
            System.out.println("----------------------------------------------");
            switch(choice){
                case 1:
                    System.out.println();
                    ViewComplaint();
                    System.out.println();
                    break;
                case 2:
                    System.out.println();
                    UpdateStatus();
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

    private void ViewComplaint(){
        try {
            Statement stmt=con.createStatement();
            String query="Select id,description,status from complaints";
            ResultSet rs=stmt.executeQuery(query);
            TablePrinter.printResultSet(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void UpdateStatus(){
        System.out.print("Enter Complaint ID: ");
        String id=sc.next();
        try {
            String query="UPDATE complaints SET status='RESOLVED' WHERE id=?";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("---STATUS UPDATED SUCCESSFULLY---");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
