<details>
  <summary><span style="font-size: 16px;font-weight: bold;">登记用户</span></summary>

#### 请求方法

> POST `${endpoint}/api/user/register`

登记新用户到系统, 用户的 `邮箱` 和 `身份证`必须是未在系统注册过

> Request Headers

```
Content-Type: application/json
```

#### 请求参数

|  字段名   |   类型    |              描述              |    属性    |
|:------:|:-------:|:----------------------------:|:--------:|
| email  | string  |             登记邮件             | Required |
| idCard | string  | 登记身份证, 符合香港身份证正则如：A123456(7) | Required |
| admin  | boolean | 是否为管理员: true 管理员 false 普通用户  | Optional |

#### 响应参数

|  字段名   |   类型    |   描述   |    属性    |
|:------:|:-------:|:------:|:--------:|
|   id   | number  |  用户主键  | Required |
| email  | string  |  用户邮箱  | Required |
| idCard | string  | 用户身份证  | Required |
| admin  | boolean | 是否为管理员 | Required |


#### 响应示例

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
</details>

---