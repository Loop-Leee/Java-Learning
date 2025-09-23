package reflection;

public class UserServiceImplB implements UserService {
    public void addUser(String name) {
        System.out.println("B add user: " + name);
    }
}