package factory;
import models.Administrator;
import models.Professor;
import models.Student;
import models.User;
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
        }
        return user;
    }
}
