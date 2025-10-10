/**
 * ClassName: Dog
 * Package: PACKAGE_NAME
 * Description:
 *
 * @Author alec
 * @Create 2024/3/1 11:27
 * @Version 1.0
 */
package polymorphism;

import java.util.ArrayList;
import java.util.List;

public class Dog implements Animal {

    public void eat(){
        System.out.println("狗勾 啃啃啃");
    }

    public void travel(){
        System.out.println("狗勾 走走走");
    }

    public int noOfLegs(){
        return 0;
    }

    /*
    如果需要运行这个包的 main 函数，需要如下步骤:
    1. 因为我用的Maven构建的项目，所以需要在moduleName/src/main/java下创建animal包
    2. 在包中创建对应的类，并在首行进行包声明 package animals; 包名必须与文件名相同
    3. 完成类和接口的功能
    4. 编译
    5. 打开终端，进入当前模块
    6. 用"-cp"告知JVM类所在的位置并运行 (java -cp target/classes animals.Dog)
    */
    public static void main(String args[]){
        List<Animal> animals = new ArrayList<>();
        animals.add(new Dog());
        animals.add(new Cat());
        for(Animal animal : animals){
            animal.eat();
            animal.travel();
        }
    }
}
