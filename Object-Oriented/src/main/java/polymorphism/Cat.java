package polymorphism;

/**
 * @Author lloop
 * @Create 2025/10/10 16:52
 */
public class Cat implements Animal{
    @Override
    public void eat() {
        System.out.println("猫咪 吃吃吃");
    }

    @Override
    public void travel() {
        System.out.println("猫咪 gogogo");
    }
}
