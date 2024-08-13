package praktikum.user;

import org.apache.commons.lang3.RandomStringUtils;

public class CreateUser {

    private String email;
    private String password;
    private String name;

    public CreateUser(String email, String password, String name){
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public CreateUser(){

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



    public static CreateUser creater() {
        return new CreateUser(RandomStringUtils.randomAlphabetic(8) + "@yandex.ru", "Saskeqwerty", "Андрей");
    }

    public static CreateUser from(CreateUser user) {
        return new CreateUser(user.getEmail(), user.getPassword(), user.getName());
    }
}
