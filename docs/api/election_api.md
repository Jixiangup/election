<details>
  <summary><span style="font-size: 16px;font-weight: bold;">编辑选举</span></summary>

#### 请求方法

> POST `${endpoint}/api/election/editor`

添加或更新选举

> Request Headers

```
Content-Type: application/json
```

#### 请求参数

> 参数位置: body

|     字段名      |      类型       |                   描述                    |    属性    |
|:------------:|:-------------:|:---------------------------------------:|:--------:|
|      id      |    number     |        选举主键id(如果为空或小于1则新增 反之更新)         | Optional |
|    status    |    number     | 当前[选举状态](api_constant.md#选举状态), 不传使用默认值 | Optional |
| candidateIds | array[number] |    参加本次选举的候选人主键集合`候选人主键id`如: [1,2,3]    | Required |

#### 其他参数

> 参数位置: header

|  字段名   |   类型    |         描述         |    属性    |
|:------:|:-------:|:------------------:|:--------:|
| userid | string  | 操作用户id, 用于验证用户操作权限 | Required |

#### 响应参数

| 字段名 |   类型   |   描述   |    属性    |
|:---:|:------:|:------:|:--------:|
|  无  | number | 选举主键ID | Required |


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
  "data": 1
}
```
</details>

---

<details>
  <summary><span style="font-size: 16px;font-weight: bold;">开始选举</span></summary>

#### 请求方法

> GET `${endpoint}/api/election/start`

开始选举

> Request Headers

```
Content-Type: application/json
```

#### 请求参数

> 参数位置: query

| 字段名 |   类型   |   描述   |    属性    |
|:---:|:------:|:------:|:--------:|
| id  | number | 选举主键id | Required |

#### 其他参数

> 参数位置: header

|  字段名   |   类型    |         描述         |    属性    |
|:------:|:-------:|:------------------:|:--------:|
| userid | string  | 操作用户id, 用于验证用户操作权限 | Required |

#### 响应参数

无

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
  "data": null
}
```
</details>

---

<details>
  <summary><span style="font-size: 16px;font-weight: bold;">结束选举</span></summary>

#### 请求方法

> GET `${endpoint}/api/election/finish`

结束选举

> Request Headers

```
Content-Type: application/json
```

#### 请求参数

> 参数位置: query

| 字段名 |   类型   |   描述   |    属性    |
|:---:|:------:|:------:|:--------:|
| id  | number | 选举主键id | Required |

#### 其他参数

> 参数位置: header

|  字段名   |   类型    |         描述         |    属性    |
|:------:|:-------:|:------------------:|:--------:|
| userid | string  | 操作用户id, 用于验证用户操作权限 | Required |

#### 响应参数

无

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
  "data": null
}
```
</details>

---

<details>
  <summary><span style="font-size: 16px;font-weight: bold;">发起投票</span></summary>

#### 请求方法

> POST `${endpoint}/api/election/vote`

结束选举

> Request Headers

```
Content-Type: application/json
```

#### 请求参数

> 参数位置: body

|     字段名     |   类型   |     描述      |    属性    |
|:-----------:|:------:|:-----------:|:--------:|
| electionId  | number | 被投票的选举主键id  | Required |
|   userid    | number |  发起投票的用户id  | Required |
| candidateId | number | 被投票的候选人主键id | Required |

#### 其他参数

无

#### 响应参数

无

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
  "data": null
}
```
</details>

---

<details>
  <summary><span style="font-size: 16px;font-weight: bold;">分页获取选举详情</span></summary>

#### 请求方法

> POST `${endpoint}/api/election/page/vote_detail`

分页获取选举中的候选人投票详情

> Request Headers

```
Content-Type: application/json
```

#### 请求参数

> 参数位置: body

|     字段名     |   类型   |             描述             |    属性    |
|:-----------:|:------:|:--------------------------:|:--------:|
|   current   | number |         当前页码,默认为1          | Optional |
| queryCount  | number |        每页查询总数 默认为20        | Optional |
|     key     | string | 查询关键字 不填不生效 用户的邮件或者身份证模糊查询 | Optional |
| electionId  | number |            选举id            | Required |
| candidateId | string |           候选人ID            | Required |
|   userid    | number |         发起查询的用户id          | Required |

#### 其他参数

无

#### 响应参数

> 以下参数为 [分页响应对象](api_constant.md#分页对象)的记录参数对象类型 存在于`records`中

|  字段名   |   类型   |    描述     |    属性    |
|:------:|:------:|:---------:|:--------:|
| userid | number |  投票用户id   | Required |
| email  | string |  投票用户邮箱   | Required |
| idCard | string | 投票用户身份证号码 | Required |

#### 响应示例

```json
{
  "code": 0,
  "message": "successful",
  "success": true,
  "type": 0,
  "target": null,
  "requestId": "00c73cef-f65c-45dd-9f55-3c258c181e35",
  "timestamp": 1673544306379,
  "data": {
    "current": 1,
    "queryCount": 20,
    "currentCount": 2,
    "total": 2,
    "page": 1,
    "records": [
      {
        "userid": 1,
        "email": "x6886886@163.com",
        "idCard": "B123456(7)"
      },
      {
        "userid": 2,
        "email": "bnytezz@gmail.com",
        "idCard": "B123456(8)"
      }
    ]
  }
}
```
</details>

---

<details>
  <summary><span style="font-size: 16px;font-weight: bold;">获取候选人在选举中的票数</span></summary>

#### 请求方法

> GET `${endpoint}/api/election/vote/candidate/count`

结束选举

> Request Headers

```
Content-Type: application/json
```

#### 请求参数

> 参数位置: query

|     字段名      |   类型   |     描述     |    属性    |
|:------------:|:------:|:----------:|:--------:|
| election_id  | number | 被投票的选举主键id | Required |
| candidate_id | number | 发起投票的用户id  | Required |

#### 其他参数

> 参数位置: header

|  字段名   |   类型    |         描述         |    属性    |
|:------:|:-------:|:------------------:|:--------:|
| userid | string  | 操作用户id, 用于验证用户操作权限 | Required |

#### 响应参数

| 字段名 |   类型   |  描述  |    属性    |
|:---:|:------:|:----:|:--------:|
|  无  | number | 获票总数 | Required |

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
  "data": 1
}
```
</details>

---

<details>
  <summary><span style="font-size: 16px;font-weight: bold;">获取选举结果</span></summary>

#### 请求方法

> GET `${endpoint}/api/election/result`

获取选举结果

> Request Headers

```
Content-Type: application/json
```

#### 请求参数

> 参数位置: query

|   字段名    |   类型   |     描述     |    属性    |
|:--------:|:------:|:----------:|:--------:|
|    id    | number | 被投票的选举主键id | Required |

#### 其他参数

> 参数位置: header

|  字段名   |   类型    |         描述         |    属性    |
|:------:|:-------:|:------------------:|:--------:|
| userid | string  | 操作用户id, 用于验证用户操作权限 | Required |

#### 响应参数

|     字段名     |   类型   |  描述   |    属性    |
|:-----------:|:------:|:-----:|:--------:|
| electionId  | number | 选举人ID | Required |
| candidateId | number | 候选人ID | Required |
|    count    | number |  获票数  | Required |

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
  "data": [
    {
      "electionId": 1,
      "candidateId": 1,
      "count": 2
    },
    {
      "electionId": 1,
      "candidateId": 2,
      "count": 1
    }
  ]
}
```
</details>

---