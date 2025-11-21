# MyBatis Plus 学习示例

本模块演示了 MyBatis Plus 的各种使用方式，包括常规使用、XML SQL、动态 SQL、自动映射和自定义映射。

## 功能特性

### 1. 常规使用方式
- **BaseMapper**：继承 `BaseMapper<T>` 获得基础 CRUD 能力
- **IService**：继承 `IService<T>` 获得增强的服务层能力
- **ServiceImpl**：继承 `ServiceImpl<M, T>` 实现服务层

**示例位置：**
- `UserMapper.java` - 继承 BaseMapper
- `UserService.java` - 继承 IService
- `UserServiceImpl.java` - 继承 ServiceImpl

**主要方法：**
- `save()` - 保存单个实体
- `saveBatch()` - 批量保存
- `getById()` - 根据ID查询
- `list()` - 查询所有
- `updateById()` - 根据ID更新
- `removeById()` - 根据ID删除（逻辑删除）

### 2. XML 实现的 SQL
在 XML 文件中编写 SQL 语句，适合复杂查询。

**示例位置：**
- `OrderMapper.xml` - `selectByUserId` 方法
- `UserMapper.xml` - `insertBatch` 方法

**特点：**
- SQL 与 Java 代码分离
- 便于维护复杂查询
- 支持动态 SQL

### 3. 动态 SQL
根据条件动态生成 SQL 语句，避免拼接字符串。

**XML 方式示例：**
- `OrderMapper.xml` - `selectByCondition` 方法
- `UserMapper.xml` - `selectByCondition` 方法

**注解方式示例：**
- `UserMapper.java` - `findByAgeRange` 方法（使用 `<script>` 标签）

**动态 SQL 标签：**
- `<if>` - 条件判断
- `<choose>`、`<when>`、`<otherwise>` - 多条件选择
- `<where>` - WHERE 子句
- `<set>` - SET 子句
- `<foreach>` - 循环遍历

### 4. 自动映射
MyBatis Plus 自动将数据库字段映射到 Java 属性。

**配置：**
```yaml
mybatis-plus:
  configuration:
    map-underscore-to-camel-case: true  # 开启驼峰命名转换
```

**示例：**
- 数据库字段 `create_time` 自动映射到 Java 属性 `createTime`
- 数据库字段 `user_id` 自动映射到 Java 属性 `userId`

**示例位置：**
- `User.java` - 实体类字段定义
- `UserMapper.java` - `findByUsername` 方法

### 5. 自定义映射（ResultMap）
使用 `<resultMap>` 自定义字段映射关系，适用于复杂查询和多表关联。

**示例位置：**
- `OrderMapper.xml` - `OrderDetailResultMap`
- `OrderMapper.java` - `selectOrderDetail` 方法

**特点：**
- 支持多表关联查询
- 可以自定义字段映射
- 支持嵌套结果映射

## 数据库表结构

### user 表
```sql
CREATE TABLE `user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100),
  `age` INT,
  `create_time` DATETIME,
  `update_time` DATETIME,
  `deleted` INT DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### order 表
```sql
CREATE TABLE `order` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `order_no` VARCHAR(50) NOT NULL,
  `amount` DECIMAL(10,2) NOT NULL,
  `status` INT DEFAULT 0 COMMENT '0-待支付，1-已支付，2-已取消',
  `create_time` DATETIME,
  `update_time` DATETIME,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

## 运行示例

### 1. 配置数据库
修改 `application.yml` 中的数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/learning?...
    username: root
    password: your_password
```

### 2. 运行测试
```bash
cd mybatisplus-learning
mvn test
```

### 3. 运行应用
```bash
mvn spring-boot:run
```

### 4. 测试 API
启动应用后，可以通过以下 API 测试：

**常规使用方式：**
- `GET /api/mybatisplus/user/{id}` - 根据ID查询用户
- `POST /api/mybatisplus/user` - 保存用户
- `PUT /api/mybatisplus/user` - 更新用户
- `DELETE /api/mybatisplus/user/{id}` - 删除用户
- `GET /api/mybatisplus/user/list` - 查询所有用户

**自动映射：**
- `GET /api/mybatisplus/user/username/{username}` - 根据用户名查询

**XML SQL：**
- `GET /api/mybatisplus/order/user/{userId}` - 根据用户ID查询订单

**动态 SQL：**
- `GET /api/mybatisplus/order/condition?userId=1&status=1` - 动态查询订单
- `GET /api/mybatisplus/user/age?minAge=20&maxAge=30` - 根据年龄范围查询

**自定义映射：**
- `GET /api/mybatisplus/order/detail/{orderId}` - 查询订单详情（包含用户信息）

## 项目结构

```
mybatisplus-learning/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/alec/learning/mybatisplus/
│   │   │       ├── MybatisPlusLearningApplication.java  # 启动类
│   │   │       ├── entity/                              # 实体类
│   │   │       │   ├── User.java
│   │   │       │   ├── Order.java
│   │   │       │   └── OrderDetailVO.java
│   │   │       ├── mapper/                              # Mapper 接口
│   │   │       │   ├── UserMapper.java
│   │   │       │   └── OrderMapper.java
│   │   │       ├── service/                             # 服务层
│   │   │       │   ├── UserService.java
│   │   │       │   ├── OrderService.java
│   │   │       │   └── impl/
│   │   │       │       ├── UserServiceImpl.java
│   │   │       │       └── OrderServiceImpl.java
│   │   │       └── controller/                          # 控制器
│   │   │           └── MyBatisPlusDemoController.java
│   │   └── resources/
│   │       ├── application.yml                          # 配置文件
│   │       └── mapper/                                  # XML Mapper 文件
│   │           ├── UserMapper.xml
│   │           └── OrderMapper.xml
│   └── test/
│       └── java/
│           └── com/alec/learning/mybatisplus/
│               └── MyBatisPlusDemoTest.java             # 测试类
└── pom.xml
```

## 关键配置说明

### application.yml
```yaml
mybatis-plus:
  # Mapper XML 文件路径
  mapper-locations: classpath*:/mapper/**/*.xml
  # 实体类包路径
  type-aliases-package: com.alec.learning.mybatisplus.entity
  configuration:
    # 开启驼峰命名转换（自动映射）
    map-underscore-to-camel-case: true
  global-config:
    db-config:
      # 主键类型：AUTO（数据库自增）
      id-type: AUTO
      # 逻辑删除配置
      logic-delete-field: deleted
      logic-delete-value: 1
      logic-not-delete-value: 0
```

## 学习要点

1. **常规使用方式**：优先使用 MyBatis Plus 提供的基础方法，减少代码量
2. **XML SQL**：复杂查询使用 XML 方式，便于维护
3. **动态 SQL**：避免 SQL 注入，提高代码可读性
4. **自动映射**：合理配置驼峰命名转换，减少手动映射
5. **自定义映射**：多表关联查询时使用 ResultMap 自定义映射

## 注意事项

1. 确保数据库表已创建
2. 修改数据库连接配置
3. 逻辑删除需要配置 `deleted` 字段
4. XML Mapper 文件路径需要与配置一致
5. Mapper 接口需要使用 `@Mapper` 注解或配置 `@MapperScan`

