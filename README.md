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

|    字段名    |   类型    |                    描述                    |    属性    |
|:---------:|:-------:|:----------------------------------------:|:--------:|
|   code    | number  | 响应[状态码](docs/api/api_constant.md#异常CODE) | Required |
|  message  | string  |                   用户邮箱                   | Required |
|  success  | string  |                  用户身份证                   | Required |
|   type    | boolean |                  是否为管理员                  | Required |
|  target   | boolean |                  是否为管理员                  | Required |
| requestId | boolean |                  是否为管理员                  | Required |
| timestamp | boolean |                  是否为管理员                  | Required |
|   date    | boolean |                  是否为管理员                  | Required |

## 名词解释

- ${endpoint} 表示为你的服务器部署地址(如: `http:127.0.0.1:8080`)

### [用户](docs/api/UserAPI.md)

### 候选人

### 选举

