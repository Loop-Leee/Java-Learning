package com.alec.learning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * ClassName: com.alec.learning.LambdaTest
 * Package: com.alec.learning
 * Description:
 *
 * @Author Alec
 * @Create 2024/3/4 14:28
 * @Version 1.0
 */
public class LambdaTest {

    public static void main(String[] args) {

        // 使用 Lambda 表达式计算两个数的和
        MathOperation addition = (a, b) -> a + b;
        // 调用 Lambda 表达式
        Integer result = addition.operation(5, 3);
        System.out.println("5 + 3 = " + result);

        List<String> names  = Arrays.asList("Alice", "Bob", "Charlie");
        // names.forEach(name->System.out.println(name));
        names.forEach(System.out::println);

        // 变量捕获
        int x = 10;
        MyFunction myFunction = y -> System.out.println(x + y);
        myFunction.doSomething(5); // 输出 15

        Supplier<List<String>> supplier = ArrayList::new;
        List<String> list = supplier.get();

        // 四种不同方法的引用
        // 1.构造器引用
        Car car = Car.create(Car::new);
        Car police = Car.create( Car::new );
        List< Car > cars = Arrays.asList( car );
        // 2.静态方法引用
        cars.forEach(Car::collide);
        // 3.类的对象的方法引用
        cars.forEach(Car::repair);
        // 4.特定对象的方法引用
        cars.forEach(police::follow);
    }
}
