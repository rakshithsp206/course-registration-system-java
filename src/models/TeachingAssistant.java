package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import DB.DBconnection;

public class TeachingAssistant extends Student {
    Scanner sc=new Scanner(System.in);
    private Connection con=DBconnection.getInstance().getConnection();

    public TeachingAssistant(String email) {
        super(email);
    }

    @Override
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
            System.out.println("8.Assign Grades");
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
                case 8:
                    assignGrades();
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

    private void assignGrades(){
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

}
