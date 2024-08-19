package praktikum.user;

public class DeleteUser {

    private String token;

    public DeleteUser(String token){
        this.token = token;
    }

    public String getUserToken() {
        return token;
    }

    public void setUserToken(String token) {
        this.token = token;
    }
}
