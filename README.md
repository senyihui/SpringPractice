# 《Spring 实战 》Version 4

## chp1

### 依赖注入
通过DI，对象的依赖关系将由系统中负责协调各对象的第三方组件在创建对象的时候进行设定。对象无需自行创建或管理它们的依赖关系，如图1.1所示，依赖关系将被自动注入到需要它们的对象当中去。

![image-20201121095135191](images/image-20201121095135191.png)

### 应用切面
如图1.3所示，我们可以把切面想象为覆盖在很多组件之上的一个外壳。应用是由那些实现各自业务功能的模块组成的。借助AOP，可以使用各种功能层去包裹核心业务层。这些层以声明的方式灵活地应用到系统中，你的核心应用甚至根本不知道它们的存在。这是一个非常强大的理念，可以将安全、事务和日志关注点与核心业务逻辑相分离。

![image-20201121095154844](images/image-20201121095154844.png)

简单的BraveKnight类开始变得复杂，如果你还需要应对没有吟游诗人时的场景，那代码会变得更复杂。但利用AOP，你可以声明吟游诗人必须歌颂骑士的探险事迹，而骑士本身并不用直接访问Minstrel的方法。

### 使用模板
Spring旨在通过模板封装来消除样板式代码。Spring的JdbcTemplate使得执行数据库操作时，避免传统的JDBC样板代码成为了可能。

### 容器
理解容器将有助于理解对象是如何被管理的。

Spring容器并不是只有一个。Spring自带了多个容器实现，可以归为两种不同的类型。bean工厂（由org.springframework. beans. factory.eanFactory接口定义）是最简单的容器，提供基本的DI支持。应用上下文（由org.springframework.context.ApplicationContext接口定义）基于BeanFactory构建，并提供应用框架级别的服务，例如从属性文件解析文本信息以及发布应用事件给感兴趣的事件监听者。

* bean工厂
* context(preferred)

## chp2 装配bean
在Spring中，对象无需自己查找或创建与其所关联的其他对象。相反，容器负责把需要相互协作的对象引用赋予各个对象。例如，一个订单管理组件需要信用卡认证组件，但它不需要自己创建信用卡认证组件。订单管理组件只需要表明自己两手空空，容器就会主动赋予它一个信用卡认证组件。

创建应用对象之间协作关系的行为通常称为装配（wiring），这也是依赖注入（DI）的本质。

* Spring配置的可选方案:
    * 在XML中进行显式配置
    * 在Java中进行显式配置
    * 隐式的bean发现机制和自动装配
      1. 为了指定不同的基础包，你所需要做的就是在@ComponentScan的value属性中指明包的名称
      2. 如果在你的代码中没有进行null检查的话，这个处于未装配状态的属性有可能会出现NullPointerException。
    

具体的内容书中讲得十分详细

另；Java配置与xml混用，即mixedconfig包下的代码，按照书中内容构建有些问题。

## chp3 高级装配

### 环境与Profile

尽管每个DataSource bean都被声明在一个profile中，并且只有当规定的profile激活时，相应的bean才会被创建，但是可能会有其他的bean并没有声明在一个给定的profile范围内。没有指定profile的bean始终都会被创建，与激活哪个profile没有关系。

Spring提供了@ActiveProfiles注解，我们可以使用它来指定运行测试时要激活哪个profile。在集成测试时，通常想要激活的是开发环境的profile。

在条件化创建bean方面，Spring的profile机制是一种很棒的方法，这里的条件要基于哪个profile处于激活状态来判断。Spring 4.0中提供了一种更为通用的机制来实现条件化的bean定义，在这种机制之中，条件完全由你来确定。

### 条件化的bean

假设你希望一个或多个bean只有在应用的类路径下包含特定的库时才创建。或者我们希望某个bean只有当另外某个特定的bean也声明了之后才会创建。我们还可能要求只有某个特定的环境变量设置之后，才会创建某个bean。

设置给@Conditional的类可以是任意实现了Condition接口的类型。可以看出来，这个接口实现起来很简单直接，只需提供matches()方法的实现即可。如果matches()方法返回true，那么就会创建带有@Conditional注解的bean。如果matches()方法返回false，将不会创建这些bean。

### 处理自动装配的歧义性

* 标示首选的bean
* 限定自动装配的bean

### bean的作用域

在默认情况下，Spring应用上下文中所有bean都是作为以单例（singleton）的形式创建的。也就是说，不管给定的一个bean被注入到其他bean多少次，每次所注入的都是同一个实例。

Spring定义了多种作用域，可以基于这些作用域创建bean，包括：

* 单例（Singleton）：在整个应用中，只创建bean的一个实例。
* 原型（Prototype）：每次注入或者通过Spring应用上下文获取的时候，都会创建一个新的bean实例。
* 会话（Session）：在Web应用中，为每个会话创建一个bean实例。
* 请求（Rquest）：在Web应用中，为每个请求创建一个bean实例。

在Web应用中，如果能够实例化在会话和请求范围内共享的bean，那将是非常有价值的事情。例如，在典型的电子商务应用中，可能会有一个bean代表用户的购物车。如果购物车是单例的话，那么将会导致所有的用户都会向同一个购物车中添加商品。另一方面，如果购物车是原型作用域的，那么在应用中某一个地方往购物车中添加商品，在应用的另外一个地方可能就不可用了，因为在这里注入的是另外一个原型作用域的购物车。

### 运行时注入

有时候硬编码是可以的，但有的时候，我们可能会希望避免硬编码值，而是想让这些值在运行时再确定。为了实现这些功能，Spring提供了两种在运行时求值的方式：

* 属性占位符（Property placeholder）。
* Spring表达式语言（SpEL）。

## chp4 Spring AOP

在软件开发中，散布于应用中多处的功能被称为横切关注点（cross-cutting concern）。通常来讲，这些横切关注点从概念上是与应用的业务逻辑相分离的（但是往往会直接嵌入到应用的业务逻辑之中）。把这些横切关注点与业务逻辑相分离正是面向切面编程（AOP）所要解决的问题。

如前所述，切面能帮助我们模块化横切关注点。简而言之，横切关注点可以被描述为影响应用多处的功能。例如，安全就是一个横切关注点，应用中的许多方法都会涉及到安全规则。图4.1直观呈现了横切关注点的概念。

![image-20201122143801886](images/image-20201122143801886.png)

在使用面向切面编程时，我们仍然在一个地方定义通用功能，但是可以通过声明的方式定义这个功能要以何种方式在何处应用，而无需修改受影响的类。横切关注点可以被模块化为特殊的类，这些类被称为切面（aspect）。这样做有两个好处：首先，现在每个关注点都集中于一个地方，而不是分散到多处代码中；其次，服务模块更简洁，因为它们只包含主要关注点（或核心功能）的代码，而次要关注点的代码被转移到切面中了。

### 定义AOP术语

描述切面的常用术语有通知（advice）、切点（pointcut）和连接点（join point）。图4.2展示了这些概念是如何关联在一起的。

![image-20201122143900329](images/image-20201122143900329.png)

Spring提供了4种类型的AOP支持：

* 基于代理的经典Spring AOP；
* 纯POJO切面；
* @AspectJ注解驱动的切面；
* 注入式AspectJ切面（适用于Spring各版本）。

前三种都是Spring AOP实现的变体，Spring AOP构建在动态代理基础之上，因此，Spring对AOP的支持局限于方法拦截。

通过在代理类中包裹切面，Spring在运行期把切面织入到Spring管理的bean中。如图4.3所示，代理类封装了目标类，并拦截被通知方法的调用，再把调用转发给真正的目标bean。当代理拦截到方法调用时，在调用目标bean方法之前，会执行切面逻辑。

![image-20201122144021446](images/image-20201122144021446.png)

*Spring只支持方法级别的连接点*

### 通过切点来选择连接点

连接点：我们的应用可能也有数以千计的时机应用通知。这些时机被称为连接点。连接点是在应用执行过程中能够插入切面的一个点。这个点可以是调用方法时、抛出异常时、甚至修改一个字段时。切面代码可以利用这些点插入到应用的正常流程之中，并添加新的行为。

关于Spring AOP的AspectJ切点，最重要的一点就是Spring仅支持AspectJ切点指示器（pointcut designator）的一个子集。让我们回顾下，Spring是基于代理的，而某些切点表达式是与基于代理的AOP无关的。

相同的切点表达式我们重复了四遍，这可真不是什么光彩的事情。这样的重复让人感觉有些不对劲。如果我们只定义这个切点一次，然后每次需要的时候引用它，那么这会是一个很好的方案。

幸好，我们完全可以这样做：@Pointcut注解能够在一个@AspectJ切面内定义可重用的切点。接下来的程序清单4.2展现了新的Audience，现在它使用了@Pointcut。

### 使用注解创建切面

```java
@Aspect
@Component
public class Audience {

    // 定义切点
    @Pointcut("execution(* interfaces.Performance.perform(..))")
    public void performance() {}

    // 前置通知
    @Before("performance()")
    public void silenceCellPhone() {
        System.out.println("手机调静音");
    }

    // 前置通知
    @Before("performance()")
    public void takeSeats() {
        System.out.println("就坐");
    }

    // 后置返回通知
    @AfterReturning("performance()")
    public void applause() {
        System.out.println("鼓掌！！！");
    }

    // 后置异常通知
    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("要求退票退钱");
    }

}
```

我们需要记住的是，Spring的AspectJ自动代理仅仅使用@AspectJ作为创建切面的指导，切面依然是基于代理的。**在本质上，它依然是Spring基于代理的切面。这一点非常重要，因为这意味着尽管使用的是@AspectJ注解，但我们仍然限于代理方法的调用。**如果想利用AspectJ的所有能力，我们必须在运行时使用AspectJ并且不依赖Spring来创建基于代理的切面。

环绕通知是最为强大的通知类型。它能够让你所编写的逻辑将被通知的目标方法完全包装起来。实际上就像在一个通知方法中同时编写前置通知和后置通知。

### 通过注解引入新功能

实际上，利用被称为引入的AOP概念，切面可以为Spring bean添加新方法。

回顾一下，在Spring中，切面只是实现了它们所包装bean相同接口的代理。如果除了实现这些接口，代理也能暴露新接口的话，会怎么样呢？那样的话，切面所通知的bean看起来像是实现了新的接口，即便底层实现类并没有实现这些接口也无所谓。图4.7展示了它们是如何工作的。

![image-20201122144529106](images/image-20201122144529106.png)

### 注入AspectJ切面

但是精心设计且有意义的切面很可能依赖其他类来完成它们的工作。如果在执行通知时，切面依赖于一个或多个类，我们可以在切面内部实例化这些协作的对象。但更好的方式是，我们可以借助Spring的依赖注入把bean装配进AspectJ切面中。

![image-20201122144618287](images/image-20201122144618287.png)

简而言之，Spring不能像之前那样使用<bean>声明来创建一个CriticAspect实例——它已经在运行时由AspectJ创建完成了。Spring需要通过aspectOf()工厂方法获得切面的引用，然后像<bean>元素规定的那样在该对象上执行依赖注入。

## 注意事项

***千万注意`@ComponentScan`中`basePackages`的路径！！***

**Resource 文件夹中的文件要手动重新创建，不要复制黏贴**