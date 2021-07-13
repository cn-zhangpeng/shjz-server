# 山海芥子
### 1. 介绍

个人空间服务端

### 2. 相关地址

* knife4j 接口文档地址：http://localhost:8888/doc.html（默认开放）
    * knife4j 文档地址：https://doc.xiaominfo.com/knife4j/
* druid 监控登录地址: http://localhost:8888/druid/login.html
* druid 监控首页: http://localhost:8888/druid/index.html
* actuator 监控查看: 
    * url: http://localhost:8888/actuator
    * 可通过 `jconsole` 查看
    
### 3. ES Mapping

* spring data elasticsearch 会进行类映射，添加 _class 字段 。
* 为了方便与 Java 更好地集成，添加额外的 id 字段，该值与 document 的 _id 值一致。

```json
{
  "blog-article" : {
    "mappings" : {
      "dynamic" : "strict",
      "properties" : {
        "_class": {
          "type": "keyword"
        },
        "id": {
          "type": "keyword"
        },
        "content" : {
          "type" : "text",
          "analyzer" : "ik_max_word"
        },
        "title" : {
          "type" : "text",
          "analyzer" : "ik_max_word"
        }
      }
    }
  }
}
```

