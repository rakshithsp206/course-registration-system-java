import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("===================WELCOME====================");
        System.out.println("Enter as:");
        System.out.println("1.Student");
        System.out.println("2.Professor");
        System.out.println("3.Administrator");
        System.out.print("Enter your role: ");
        int role=sc.nextInt();
        System.out.println("----------------------------------------------");
        System.out.println();

        System.out.println("1.Login");
        System.out.println("2.SignUp");
        System.out.print("Enter your choice: ");
        int choice=sc.nextInt();
        System.out.println("----------------------------------------------");
        System.out.println();

        Start st=new Start();
        switch(choice){
            case 1:
                try {
                    st.login();
                } catch (InvalidLoginException e) {
                    System.out.println(e.getMessage());
                    return;
                }
                break;
            case 2:
                if(role==3){
                    System.out.println("ERROR: SignUp not Allowed for Admin");
                    return;
                }
                st.signup();
                break;
        }
        String email=st.email;
        System.out.println("----------------------------------------------");
        System.out.println();

        User user=new UserFactory().getUser(role, email);
        user.menu();

        DBconnection.getInstance().closeConnection();
    }
}
