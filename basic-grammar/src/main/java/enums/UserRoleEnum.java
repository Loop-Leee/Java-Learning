package enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lloop
 * @Create 2025/11/5 10:06
 */
public enum UserRoleEnum {

    SUPER_ADMIN(1, "超级管理员"),
    ADMIN(2, "管理员"),
    USER(3, "普通用户"),
    GUEST(4, "访客"),
    BLOCKED(5, "封禁用户");

    private final int code;
    private final String description;

    UserRoleEnum(int code, String description) {
        this.code = code;
        this.description = description;
        System.out.println("UserRoleEnum 构造中: code = " + code + ", description = " + description);
    }

    /**
     * 判断是否是超级管理员
     */
    public static boolean isSuperAdmin(int code) {
        return code == SUPER_ADMIN.code;
    }

    /**
     * 判断是否是管理员
     */
    public static boolean isAdmin(int code) {
        return code == ADMIN.code;
    }

    /**
     * 根据 code 获取 description
     */
    public static String getDescription(int code) {
        for (UserRoleEnum value : values()) {
            if (value.code == code) {
                return value.description;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 根据 code 获取 description
     */
    public static String getDescriptionByCode(int code) {
        for (UserRoleEnum value : values()) {
            if (value.code == code) {
                return value.description;
            }
        }
        return null;
    }

    /**
     * 获取值列表
     */
    public static List<String> getDescriptionList() {
        return Arrays.stream(values())
                .map(UserRoleEnum::getDescription)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println("-----------------");
        UserRoleEnum.getDescriptionList().forEach(System.out::println);
        System.out.println("-----------------");
        System.out.println(UserRoleEnum.ADMIN.getDescription());
        System.out.println("-----------------");
        System.out.println(UserRoleEnum.getDescriptionByCode(2));
    }

}
