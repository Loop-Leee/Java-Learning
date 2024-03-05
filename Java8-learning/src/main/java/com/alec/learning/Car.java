package com.alec.learning;

import java.util.function.Supplier;

/**
 * ClassName: Car
 * Package: com.alec.learning
 * Description:
 *
 * @Author Alec
 * @Create 2024/3/4 16:42
 * @Version 1.0
 */

public class Car {
    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}
