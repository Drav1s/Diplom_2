package praktikum.user;

import org.apache.commons.lang3.RandomStringUtils;

public class EditUser {

    private String token;
    private String email;
    private String password;
    private String name;

    public EditUser(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public EditUser(String token){
        this.token = token;

    }

    public String getUserToken() {
        return token;
    }

    public void setUserToken(String token) {
        this.token = token;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public static EditUser editer() {
        return new EditUser(RandomStringUtils.randomAlphabetic(8) + "@yandex.ru", RandomStringUtils.randomAlphabetic(8), RandomStringUtils.randomAlphabetic(8));
    }

    public static EditUser from(EditUser editUser) {
        return new EditUser(editUser.getEmail(), editUser.getPassword(), editUser.getName());
    }
}
