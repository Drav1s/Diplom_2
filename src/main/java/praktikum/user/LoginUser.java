package praktikum.user;

public class LoginUser {


    private String email;
    private String password;

    public LoginUser(String email, String password){
        this.email = email;
        this.password = password;
    }

    public LoginUser(){

    }

    public static LoginUser from(CreateUser user) {
        return new LoginUser(user.getEmail(), user.getPassword());
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
