# 2020最新版SpringCloud学习



## 视频地址  

###  [尚硅谷2020最新版SpringCloud](https://www.bilibili.com/video/av93813318)

##  boot和cloud版本选型

### springcloud和Spring boot关系

Spring Cloud 版本：  **Hoxton SR3**

**Release train Spring Boot compatibility**

| Release Train | Boot Version |
| :------------ | :----------- |
| Hoxton        | 2.2.x        |

## 父工程project空间搭建

### 父工程搭建步骤

1，New project

2,	聚合总父工程名称

3，maven版本选择

4，字符编码

5, java编译版本选择8

8，File Type过滤 编辑->File Types 添加

###  父POM文件

1，设置<packaging>pom</packaging>

2，删除原有src文件夹

3，统一管理jar包版本

```properties
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.sorurce>1.8</maven.compiler.sorurce>
    <maven.compiler.target>1.8</maven.compiler.target>
    <mybatis.spring.boot.version>2.1.2</mybatis.spring.boot.version>
    <hutool-all.version>5.1.0</hutool-all.version>
</properties>
```

4，dependencyManagement子模块继承之后，提供作用：锁定本+子modle不用写groupId和version

```xml
<dependencies>
        <dependency>
            <!---spring boot 2.2.5-->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.2.5.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <!--spring cloud Hoxton.SR3-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Hoxton.SR3</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
           <!--mybatis-spring- 2.1.2-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis.spring.boot.version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
        </dependency>
    </dependencies>

</dependencyManagement>

  <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.2.5.RELEASE</version>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
```



## eureka 

### 服务端配置

pom：

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
     </dependency>
     <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
     </dependency>
```

application.yml

```yml
#端口
server:
  port: 7001

eureka:
  instance:
    hostname: eureka7001.com #服务端的实例名
  client:
  #false表示不向注册中心注册自己
    register-with-eureka: false 
    #false表示自己端是注册中心，我是维护服务实例，不需要去检索服务
    fetch-registry: false
    service-url:
    #注册和查询服务的地址
#      defaultZone: http://eureka7002.com:7002/eureka/
      defaultZone: http://eureka7001.com:7001/eureka/
      
# 关闭自我保护
eureka.server.enable-self-preservation=false
# 清理无效节点时间（10 * 1000）
eureka.server.eviction-interval-timer-in-ms=10000
# 刷新readCacheMap的时间
eureka.server.response-cache-update-interval-ms=3000
```

启动类

增加`@EnableEurekaServer`注解

### 客户端注册

pom:

```xml
<!--eureka client-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

修改yml文件

```yaml
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:7001/eureka/
#      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/
  instance:
  #访问信息显示ip
    prefer-ip-address: true
     #访问信息显示微服务名
    instance-id: payment8001
```

启动类

增加`@EnableEurekaClient`注解

服务发现Discovery

```java
@Resource
private DiscoveryClient discoveryClient;

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("****elment:{}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }
```



## Ribbon

### 概述

官网 https://github.com/Netflix/ribbon

spring cloud替换方案

`<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-loadbalancer</artifactId>
  <version>2.2.2.RELEASE</version>
</dependency>`

Ribbon本地负载均衡VS Nginx服务端负载均衡

软件负载均衡有Nginx、LVS、硬件F5











