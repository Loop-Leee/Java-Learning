package reflection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class ProxyDemo implements CommandLineRunner {

    @Value("${com.example.userServiceImpl}")
    private String implClass;

    @Override
    public void run(String... args) throws Exception {
        // 启动时传入 -DimplClass=UserServiceImplA 或 UserServiceImplB
        String implClassName = System.getProperty("implClass");
        // 启动时没有传入 -DimplClass 则使用配置文件中的 implClass
        if (implClassName == null) {
            implClassName = implClass;
        }

        System.out.println("使用实现类: " + implClassName);

        // 运行时用反射加载类
        Class<?> clazz = Class.forName(implClassName);
        UserService target = (UserService) clazz.getDeclaredConstructor().newInstance();

        // 创建代理
        UserService proxy = (UserService) Proxy.newProxyInstance(
            clazz.getClassLoader(),
            clazz.getInterfaces(),
            new MyInvocationHandler(target)
        );

        proxy.addUser("Alice");
    }
}