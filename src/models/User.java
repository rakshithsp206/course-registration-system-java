package models;
public abstract class User {
    protected String email;
    public User(String email){
        this.email=email;
    }
    public abstract void menu();
}
