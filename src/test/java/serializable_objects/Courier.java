package serializable_objects;

import java.util.Random;

public class Courier {

    private String login;
    private String password;
    private String firstName;
    private String id;

    public Courier() {
    }

    public Courier(boolean isRandom){
        if (isRandom) {
            setRandomLogin();
            setRandomPassword();
            setRandomName();
        }
    }

    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRandomLogin(){
        Random random = new Random();
        int randomNumber = random.nextInt();
        String login = String.format("login-%d",randomNumber);
        setLogin(login);
    }
    public void setRandomPassword(){
        Random random = new Random();
        int randomNumber = random.nextInt();
        String password = String.format("password-%d",randomNumber);
        setPassword(password);
    }
    public void setRandomName(){
        Random random = new Random();
        int randomNumber = random.nextInt();
        String name = String.format("login-%d",randomNumber);
        setFirstName(name);
    }
    public void setRandomId(){
        Random random = new Random();
        int randomNumber = random.nextInt();
        String id = String.format("login-%d",randomNumber);
        setId(id);
    }
}
