package serialize;

/**
 * ClassName: serialize.Employee
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author alec
 * @Create 2024/2/28 11:36
 * @Version 1.0
 */

public class Employee implements java.io.Serializable
{
    public String name;
    public String address;
    public transient int SSN;
    public int number;
    public void mailCheck()
    {
        System.out.println("Mailing a check to " + name
                + " " + address);
    }
}