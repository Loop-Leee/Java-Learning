/**
 * ClassName: EnumTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author alec
 * @Create 2024/2/28 11:14
 * @Version 1.0
 */
class FreshJice{
    enum FreshJuiceSize {SMALL, MEDIUM, LARGE}
    FreshJuiceSize size;
}

public class EnumTest {
    enum Color
    {
        RED, GREEN,BLUE;
        private Color(){
            System.out.println("Constructor called for : :" + this.toString());
        }

        public void colorInfo(){
            System.out.println("Universal Color");
        }
    }
    public static void main(String[] args) {
        Color c1 = Color.RED;
        System.out.println(c1);
        c1.colorInfo();


        /*FreshJice juice = new FreshJice();
        juice.size = FreshJice.FreshJuiceSize.MEDIUM;*/
    }

}
