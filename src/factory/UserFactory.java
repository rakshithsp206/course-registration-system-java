package factory;
import models.*;
public class UserFactory {
    private User user;
    public User getUser(int role,String email){
        switch(role){
            case 1:
                user=new Student(email);
                break;
            case 2:
                user=new Professor(email);
                break;
            case 3:
                user=new Administrator(email);
                break;
            case 4:
                user=new TeachingAssistant(email);
                break;
            default:
                System.out.println("Invalid Role");
                break;
        }
        return user;
    }
}
