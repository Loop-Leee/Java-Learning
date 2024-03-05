package com.alec.learning;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ClassName: DateTimeTest
 * Package: com.alec.learning
 * Description:
 *
 * @Author Alec
 * @Create 2024/3/4 20:38
 * @Version 1.0
 */
public class DateTimeTest {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        System.out.println(localDateTime.withYear(2000).withMonth(7));
        System.out.println(localDateTime);
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
    }

}
