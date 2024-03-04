/**
 * ClassName: ThreadTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author alec
 * @Create 2024/3/2 21:32
 * @Version 1.0
 */
public class ThreadTest {

    public static void main(String[] args) {
        RunnableDemo R1 = new RunnableDemo("Thread-1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo("Thread-2");
        R2.start();
    }
}
