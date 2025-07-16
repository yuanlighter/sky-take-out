| **序号** | **名称**     | **说明**                                                     |
| -------- | ------------ | ------------------------------------------------------------ |
| 1        | sky-take-out | maven父工程，统一管理依赖版本，聚合其他子模块                |
| 2        | sky-common   | 子模块，存放公共类，例如：工具类、常量类、异常类等           |
| 3        | sky-pojo     | 子模块，存放实体类pojo、VO(view object)、DTO（data transfer object）等 |
| 4        | sky-server   | 子模块，controller、mapper、service等代码核心模块            |


分析sky-common模块的每个包的作用：

| 名称        | 说明                           |
| ----------- | ------------------------------ |
| constant    | 存放相关常量类                 |
| context     | 存放上下文类                   |
| enumeration | 项目的枚举类存储               |
| exception   | 存放自定义异常类               |
| json        | 处理json转换的类               |
| properties  | 存放SpringBoot相关的配置属性类 |
| result      | 返回结果类的封装               |
| utils       | 常用工具类                     |

分析sky-pojo模块的每个包的作用：

| **名称** | **说明**                                     |
| -------- | -------------------------------------------- |
| DTO      | 数据传输对象，通常用于程序中各层之间传递数据 |
| VO       | 视图对象，为前端展示数据提供的对象           |
| POJO     | 普通Java对象，只有属性和对应的getter和setter |

- 分析sky-server模块的每个包的作用：

  | 名称           | 说明             |
    | -------------- | ---------------- |
  | config         | 存放配置类       |
  | controller     | 存放controller类 |
  | interceptor    | 存放拦截器类     |
  | mapper         | 存放mapper接口   |
  | service        | 存放service类    |
  | SkyApplication | 启动类           |


