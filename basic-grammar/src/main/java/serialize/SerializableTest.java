package serialize;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * ClassName: serialize.SerializableTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author alec
 * @Create 2024/3/2 11:07
 * @Version 1.0
 */
public class SerializableTest {

    public static void main(String[] args) {
        Employee e = new Employee();
        e.name = "Loop Lee";
        e.address = "China HN";
        e.SSN = 11122333;
        e.number = 12345678;

        try {
            FileOutputStream fos = new FileOutputStream("employee.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(e);
            oos.close();
            fos.close();
            System.out.printf("Serialized data is saved in employee.ser");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
