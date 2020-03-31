# 2020最新版SpringCloud学习



## 视频地址  

###  [尚硅谷2020最新版SpringCloud](https://www.bilibili.com/video/av93813318)


## 笔记
doc目录


3) github下载失败

## 2. 启动前准备
### 2.1 数据库
* 执行sql脚本 doc/db2020.sql
* 修改数据库的配置

```text
cloud-provider-payment8001\src\main\resources\application.yml中
mysql的用户名和密码
```

### 2.2 修改hosts
找到C:\Windows\System32\drivers\etc路径下的hosts文件,添加

```text
127.0.0.1 eureka7001.com
127.0.0.1 eureka7002.com
```

## 3 软件
* Zookeeper
* consul
* JMeter
* RabbitMq
* zipkin-server






