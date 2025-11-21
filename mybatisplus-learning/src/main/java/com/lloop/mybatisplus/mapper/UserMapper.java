package com.lloop.mybatisplus.mapper;

import com.lloop.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户 Mapper 接口
 * 演示常规使用方式：继承 BaseMapper 获得基础 CRUD 能力
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户（注解方式）
     * 演示自动映射
     */
    @Select("SELECT * FROM user WHERE username = #{username} AND deleted = 0")
    User findByUsername(@Param("username") String username);

    /**
     * 根据年龄范围查询用户（注解方式）
     * 演示动态 SQL（使用 @Select 注解）
     */
    @Select("<script>" +
            "SELECT * FROM user WHERE deleted = 0 " +
            "<if test='minAge != null'>AND age &gt;= #{minAge}</if> " +
            "<if test='maxAge != null'>AND age &lt;= #{maxAge}</if> " +
            "ORDER BY age ASC" +
            "</script>")
    List<User> findByAgeRange(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);

    /**
     * 批量插入用户（XML 方式实现）
     * 对应 UserMapper.xml 中的 insertBatch 方法
     */
    int insertBatch(@Param("users") List<User> users);
}

