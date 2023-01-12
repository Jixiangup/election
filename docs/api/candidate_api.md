<details>
  <summary><span style="font-size: 16px;font-weight: bold;">编辑候选人</span></summary>

#### 请求方法

> POST `${endpoint}/api/candidate/editor`

添加或更新新的候选人

> Request Headers

```
Content-Type: application/json
```

#### 请求参数

> 参数位置: body

|   字段名    |   类型   |            描述             |    属性    |
|:--------:|:------:|:-------------------------:|:--------:|
|    id    | number | 候选人主键id(如果为空或小于1则新增 反之更新) | Optional |
| nickname | string |          新的候选人昵称          | Required |

#### 其他参数

> 参数位置: header

|  字段名   |   类型    |         描述         |    属性    |
|:------:|:-------:|:------------------:|:--------:|
| userid | string  | 操作用户id, 用于验证用户操作权限 | Required |

#### 响应参数

| 字段名 |   类型   |   描述    |    属性    |
|:---:|:------:|:-------:|:--------:|
|  无  | number | 候选人主键ID | Required |


#### 响应示例

```json
{
  "code": 0,
  "message": "successful",
  "success": true,
  "type": 0,
  "target": null,
  "requestId": "554c16e8-f3d4-4bad-b4a1-59f325ebe152",
  "timestamp": 1673542746728,
  "data": 3
}
```
</details>

---