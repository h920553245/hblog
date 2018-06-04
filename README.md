# hblog
hergua's blog  apparoched
### hblog技术点说明
框架说明
----
-  核心层：Spring Boot
-  控制层：Spring MVC
-  持久层：JPA (java persistance api)![](http://www.hergua.cn/image/TIM截图20180523090733.png)
----
亮点说明
----
 1. hblog使用markdown编辑器，相比较于其他的富文本编辑器更具有灵活性
 2. 前端框架使用Hexo开源博客前端框架
 3. 前端模板使用thymeleaf模板，相比较JSP界面源码更加的直观，同时它与html5相互兼容，不需要更改html文件的后缀，不需要更改任何格式即可兼容使用
 4. css框架使用Twitter开源的bootstrap框架，拥有更美观的界面
 5. 后端核心层框架使用spring boot 相较于单纯的spring所需要配置的文件更少，调用更加灵活 ，大包更加的便捷
 6. 后端控制层使用spring mvc ，spring MVC 作为spring家族的持久层担当，配合上jdk1.5以后的注解规范，使得映射更加的简便，灵活。
 7. 后端持久层框架使用JPI规范，其底层实现由hibernate或者其他orm框架实现，只需要编写数据层的接口，即可调用父类接口当中的实现。同时支持直接使用HQL语言以及原生sql语句查询，解放双手!
