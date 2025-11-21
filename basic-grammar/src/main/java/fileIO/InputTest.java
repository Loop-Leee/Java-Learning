package fileIO;

import java.io.*;

/**
 * ClassName: InputTest
 * Package: com.alec.learning
 * Description:
 *
 * @Author Alec
 * @Create 2024/3/4 14:10
 * @Version 1.0
 */
public class InputTest {
    public static void main(String[] args) {
        try {
            // BufferedReader in = new BufferedReader(new FileReader("./FileInputOutput-learning/test.txt"));
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("./FileInputOutput-learning/test.txt")));
            String str;
            while((str = in.readLine()) != null){
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
