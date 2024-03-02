import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * ClassName: ArrayListTest
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author alec
 * @Create 2024/3/1 16:48
 * @Version 1.0
 */
public class ArrayListTest {

    public static void main(String[] args) {
        ArrayList<String> sites = new ArrayList<>();
        sites.add("Google");
        sites.add("Taobao");
        sites.add("Runoob");
        sites.add("Zhihu");
        Iterator<String> it = sites.iterator();
        while(it.hasNext()){
            String i = it.next();
            if(i.length() <= 5){
                it.remove();
            }
        }
        Collections.sort(sites);
        System.out.println(sites);
    }
}
