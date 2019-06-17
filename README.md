# gym-club-web-flux
# 说明  
> servlet版本[gym-club](https://github.com/imjasming/jee-ex-gym-club-system)
> Gym Club 项目为前后端分离项目，前端项目`jee-ex-gym-club-system-web`地址：[gym-club-system-web](https://github.com/imjasming/jee-ex-gym-club-system-web)  
# 项目介绍  
本项目为【Java EE课程作业】后端项目，基于反应式的 Spring Boot + Spring Security 实现；
详细见[设计文档]()
[security细节](https://github.com/imjasming/gym-club-web-flux/tree/master#security)
## 项目团队
张小明（16301026），程威（16301032）
# 项目结构  
```
.
src
├─main
│  ├─java
│  │  └─com
│  │      └─gymclub
│  │          └─api
│  │              │  GymClubApiApplication.java ----------------------- main函数入口
│  │              ├─annotation
│  │              │      RateLimitAspect.java ------------------------- reteLimited 的切面注解
│  │              │      
│  │              ├─components
│  │              │      RateLimitAop.java ---------------------------- reteLimited 切面实现
│  │              │      
│  │              ├─config
│  │              │      RedisConfig.java ----------------------------- redis 缓存配置
│  │              │      Swagger2Config.java -------------------------- swagger 基本配置
│  │              │      
│  │              ├─controller ---------------------------------------- Reactive处理器组件包
│  │              │      GymHandler.java ------------------------------ 响应gym list处理器
│  │              │      HateoasHandler.java -------------------------- Hateoas处理器
│  │              │      LoginHandler.java ---------------------------- 不作登录认证，仅仅返回注册的第三方登录地址
│  │              │      TrainerHandler.java -------------------------- 响应获取trainer资源的处理器
│  │              │      UserInfoHandler.java ------------------------- 用户信息处理器
│  │              │      UserSignUpHandler.java ----------------------- 用户注册处理器
│  │              │      
│  │              ├─domain -------------------------------------------- 实体类包
│  │              │  │  Equipment.java
│  │              │  │  Gym.java
│  │              │  │  Role.java
│  │              │  │  Trainer.java
│  │              │  │  UmUser.java
│  │              │  │  UserInfo.java
│  │              │  │  
│  │              ├─dto ----------------------------------------------- 数据传输对象包
│  │              │  │  CommonRestResult.java
│  │              │  │  GithubAuthServiceResult.java
│  │              │  │  PageParam.java
│  │              │  │  PwdChangeDTO.java
│  │              │  │  RestResponse.java
│  │              │  │  UserProfile.java
│  │              │  │  UserSignUpRequest.java
│  │              │  │  
│  │              │  └─hateoas
│  │              │      │  EquipmentResource.java
│  │              │      │  Greeting.java
│  │              │      │  GymResource.java
│  │              │      │  TrainResource.java
│  │              │      │  UserResource.java
│  │              │      │  
│  │              │      └─hatoasResourceAssembler
│  │              │              EquipmentResourceAssembler.java
│  │              │              GymResourceAssembler.java
│  │              │              TrainerResourceAssembler.java
│  │              │              UserResourceAssembler.java
│  │              │              
│  │              ├─repository ---------------------------------------- MongoDB数据访问对象
│  │              │  │  EquipmentRepository.java
│  │              │  │  GymRepository.java
│  │              │  │  MyRoleRepository.java
│  │              │  │  TrainerRepository.java
│  │              │  │  UserInfoRepository.java
│  │              │  │  UserRepository.java
│  │              │  │  
│  │              ├─router ------------------------------------------- webFlux路由配置类
│  │              │      HateoasRouter.java
│  │              │      Routers.java -------------------------------- 所有的api接口路由配置
│  │              │      TrainerRouter.java
│  │              │      UserInfoRouter.java
│  │              │      
│  │              ├─security 
│  │              │  │  MyReactiveUserDetailsService.java ------------ spring security认证获取用户
│  │              │  │  OauthClient.java ----------------------------- oauth client实体类
│  │              │  │  OAuthClientRepository.java ------------------- oauth client数据访问对象
│  │              │  │  WebFluxSecurityConfig.java ------------------- 应用全局安全控制配置
│  │              │  │  
│  │              │  ├─authenticate
│  │              │  │      MyAuthenticationManager.java ------------- 自定义处理认证请求的接口
│  │              │  │      
│  │              │  ├─authorize
│  │              │  │      MyGrantedAuthoritiesExcutor.java --------- 没用到
│  │              │  │      
│  │              │  ├─bo
│  │              │  │      MyUserDetails.java ----------------------- security业务处理对象，把 User 转化为 UserDetails 用于 SS 认证
│  │              │  │      OAuthClientDetails.java ------------------ 第三方登录的接口信息实体类，但本次用 inMemory 的方式
│  │              │  │      
│  │              │  └─handler
│  │              │          MyLoginAuthenticationSuccessHandler.java -- spring security认证（usernamePassword认证）成功后处理器，返回 token
│  │              │          OAuthTokenUtil.java --------------------- 从认证成功后信息（Principle）构造出 OAuthAccessToken
│  │              │          
│  │              ├─service
│  │              │  │  DataService.java ----------------------------- 处理Trainer 数据服务类接口
│  │              │  │  UserService.java ----------------------------- 处理用户信息服务类接口
│  │              │  │  UserSignUpService.java ----------------------- 处理用户登录服务类接口
│  │              │  │  
│  │              │  └─impl ------------------------------------------ 以下为上述服务类接口的实现
│  │              │          DataServiceImpl.java
│  │              │          UserServiceImpl.java
│  │              │          UserSignUpServiceImpl.java
│  │              │          
│  │              └─util
│  │                      RedisOperator.java ------------------------- 封装调用 redis 的工具类
│  │                      RequestUtil.java --------------------------- 封装从 ServerRequest 获取 queryParameter 等的工具类
│  │                      
│  └─resources
│      │  application.yml -------------------------------------------- spring boot 配置文件
│      │  
│      ├─img --------------------------------------------------------- 静态资源图片
│      │      gym.jpg
│      │      head.png
│      │      trainericon.jpg
│              
└─test --------------------------------------------------------------- 单元测试类
    ├─java
    │  └─com
    │      └─gymclub
    │          └─api
    │                  GymClubApiTest.java
    │                  
    └─resources
```
# Security  
## 1. form login（表单登录）：  
完全交由 spring security 认证，只是提供了 UserDetailsService 供获取 UserDetails，和认证后处理器 LoginAuthenticationSuccessHandler  
以 JWT 形式返回 Token( OAuthAccessToken)
< 表单登录接口： POST /login  
## 2. OAuth：
自定义提供了 Converter，AuthenticationManager，和 CLientRegistrationRepository，Token 也为JWT。ClientRegistrationRepository 是以  
inMemory 方式提供 client。登录流程为前端获取server提供的第三方登录url后，请求 code，再将 code POST 到 server，交由 spring security 处理  
< OAuth登录接口：POST {baseUrl}/login/oauth2/code/{registrationId}，如：POST 127.0.0.1:8000/login/oauth2/code/github?code=
