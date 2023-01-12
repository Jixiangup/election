# election

投票选举项目, 验证香港身份证之后为至少2人的选举进行投票

# 快速开始

### 环境配置

- [Oracle Java 17.0](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven 3.8.6](https://maven.apache.org/download.cgi)
- [MySQL 8.0.28](https://www.mysql.com/downloads/)
- [Git](https://git-scm.com/downloads)

> 部署之前请确保你的JDK MAVEN等必要环境已经配置`环境变量`

### 系统配置介绍

[参考配置文件](https://github.com/bnyte/election/blob/main/election-api/src/main/resources/application.yaml)

|      属性名称      |      描述      |                                                  默认值                                                  | 是否必填 |
|:--------------:|:------------:|:-----------------------------------------------------------------------------------------------------:|:----:|
|   MYSQL_URL    | mysql的jdbc地址 | jdbc:mysql://127.0.0.1:3306/election?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai |  Y   |
| MYSQL_PASSWORD |   mysql密码    |                                                123456                                                 |  Y   |
| MYSQL_USERNAME |   mysql用户名   |                                                 root                                                  |  Y   |
|   REDIS_HOST   |  redis IP地址  |                                               127.0.0.1                                               |  Y   |
| REDIS_PASSWORD |   redis 密码   |                                                   无                                                   |  N   |
|   REDIS_PORT   |   redis 端口   |                                                 6379                                                  |  Y   |
| REDIS_DATABASE |  redis 数据库   |                                                   0                                                   |  N   |
|   MAIL_HOST    |  发件邮箱smtp地址  |                                         无(示例: smtp.gmail.com)                                         |  Y   |
| MAIL_USERNAME  |  发件邮箱的用户名称   |                                       无(示例: bnytezz@gmail.com)                                        |  Y   |
| MAIL_PASSWORD  |   发件邮箱的密钥    |                                             无(示例: 123456)                                             |  Y   |


### 本地部署

- clone 源代码
  ```shell
  git clone https://github.com/bnyte/election.git
  ```
- 修改配置
  
  请根据 [系统配置介绍](#系统配置介绍) 按照自身环境修改相应配置。

- 初始化数据库

  [执行Init脚本](https://github.com/bnyte/election/blob/main/sql/init.sql)

- 打包
  
  ```shell
  cd election
  mvn clean package
  ```

- 运行

  ```shell
  java -jar election-api/target/election-api-1.0.0.jar
  ```

  或者你也可以指定外部配置文件运行
  
    ```shell
    cd ${your_config_path}
    touch application.yaml
    vi application.yaml
    ```
  
  添加并修改配置到 `${your_config_path}/application.yaml`
  
  参考 [参考配置文件](https://github.com/bnyte/election/blob/main/election-api/src/main/resources/application.yaml) 修改自己的配置

  运行你的项目

  ```shell
  java -jar election-api/target/election-api-1.0.0.jar --spring.config.location="/tmp/your/config/path/application.yaml"
  ```
  
# API Docs

所有请求采用`RESTFUL`风格进行交互

## 全局响应参数

|    字段名    |   类型    |                              描述                               |    属性    |
|:---------:|:-------:|:-------------------------------------------------------------:|:--------:|
|   code    | number  |           响应[状态码](docs/api/api_constant.md#异常CODE)            | Required |
|  message  | string  |                             响应描述                              | Required |
|  success  | boolean |                  本次是否成功, 前端校验用, 也可以使用code判断                   | Required |
|   type    | number  |         请求完成之后的[通知类型](docs/api/api_constant.md#通知类型)          | Required |
|  target   | string  | 请求完成之后的跳转值, 见[通知类型](docs/api/api_constant.md#通知类型)的`target`描述 | Optional |
| requestId | string  |                    本次请求id, 用于快速定位追踪请求日志信息                     | Required |
| timestamp | number  |                         毫秒(13位)请求时间戳                          | Required |
|   data    |   any   |           响应数据, 根据每个API响应 数据类型结构不同, 详见每个API的`响应参数`            | Optional |

> 下文中所有`响应参数`就是上述的 `data` 参数类型

### 响应示例

```json
{
    "code": 0,
    "message": "successful",
    "success": true,
    "type": 0,
    "target": null,
    "requestId": "610ba23d-9549-4be7-9890-c24cc806b8d6",
    "timestamp": 1673520719096,
    "date": {
        "id": 12,
        "email": "c.ehcb@qq.com",
        "idCard": "C000000(0)",
        "admin": false
    }
}
```

## 名词解释

- ${endpoint} 表示为你的服务器部署地址(如: `http:127.0.0.1:8080`)

### [用户](docs/api/user_api.md)

### [候选人](docs/api/candidate_api.md)

### 选举

