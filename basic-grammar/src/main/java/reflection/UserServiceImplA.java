package reflection;

public class UserServiceImplA implements UserService {
    public void addUser(String name) {
        System.out.println("A add user: " + name);
    }
}