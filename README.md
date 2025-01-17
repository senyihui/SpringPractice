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

## chp5 简单MVC
我们所编写的控制器将会带有@Controller注解，这会使其成为组件扫描时的候选bean。因此，我们不需要在配置类中显式声明任何的控制器。

在Spring MVC中，控制器只是方法上添加了@RequestMapping注解的类，这个注解声明了它们所要处理的请求。

你可以看到，home()方法其实并没有做太多的事情：它返回了一个String类型的“home”。这个String将会被Spring MVC解读为要渲染的视图名称。DispatcherServlet会要求视图解析器将这个逻辑名称解析为实际的视图。

### 定义类级别的请求处理
处理器方法上的@RequestMapping注解会对类级别上的@RequestMapping的声明进行补充。

*image*

现在，HomeController的home()方法能够映射到对“/”和“/homepage”的GET请求

Model实际上就是一个Map（也就是key-value对的集合），它会传递给视图，这样数据就能渲染到客户端了。

*code*
这个版本与其他的版本有些差别。它并没有返回视图名称，也没有显式地设定模型，这个方法返回的是Spittle列表。当处理器方法像这样返回对象或集合时，这个值会放到模型中，模型的key会根据其类型推断得出（在本例中，也就是spittleList）。

### 接受请求的输入
Spring MVC允许以多种方式将客户端中的数据传送到控制器的处理器方法中，包括：
* 查询参数（Query Parameter）
* 路径变量（Path Variable）
* 表单参数（Form Parameter）

这个处理器方法将会处理形如“/spittles/show?spittle_id=12345”这样的请求。尽管这也可以正常工作，但是从面向资源的角度来看这并不理想。在理想情况下，要识别的资源（Spittle）应该通过URL路径进行标示，而不是通过查询参数。对“/spittles/12345”发起GET请求要优于对“/spittles/show?spittle_id=12345”发起请求。前者能够识别出要查询的资源，而后者描述的是带有参数的一个操作——本质上是通过HTTP发起的RPC。

为了实现这种路径变量，Spring MVC允许我们在@RequestMapping路径中添加占位符。占位符的名称要用大括号（“{”和“}”）括起来。路径中的其他部分要与所处理的请求完全匹配，但是占位符部分可以是任意的值。

如果方法的参数名碰巧与占位符的名称相同，因此我们可以去掉@PathVariable中的value属性

## chp6 渲染web视图

对于大部分视图解析器来讲，每一项都对应Java Web应用中特定的某种视图技术。InternalResourceViewResolver一般会用于JSP，TilesViewResolver用于Apache Tiles视图，而FreeMarkerViewResolver和VelocityViewResolver分别对应FreeMarker和Velocity模板视图。

Spring提供了两个JSP标签库，用来帮助定义Spring MVC Web的视图。其中一个标签库会用来渲染HTML表单标签，这些标签可以绑定model中的某个属性。另外一个标签库包含了一些工具类标签，我们随时都可以非常便利地使用它们。

你可能会想知道“${}”和“*{}”括起来的表达式到底有什么区别。“${}”表达式（如${spitter}）是变量表达式（variable expression）。一般来讲，它们会是对象图导航语言（Object-Graph Navigation Language，OGNL）表达式（http://commons.apache.org/proper/commons-ognl/）。但在使用Spring的时候，它们是SpEL表达式。在${spitter}这个例子中，它会解析为key为spitter的model属性。

而对于“*{}”表达式，它们是选择表达式（selection expression）。变量表达式是基于整个SpEL上下文计算的，而选择表达式是基于某一个选中对象计算的。在本例的表单中，选中对象就是<form>标签中th:object属性所设置的对象：模型中的Spitter对象。因此，“*{firstName}”表达式就会计算为Spitter对象的firstName属性。

## chp7 Spring MVC 高级技术
pass

## chp8 Spring Web Flow

pass

## chp9 保护Web应用

pass   

## chp10 Spring JDBC

Spring将数据访问过程中固定的和可变的部分明确划分为两个不同的类：模板（template）和回调（callback）。模板管理过程中固定的部分，而回调处理自定义的数据访问代码。图10.2展现了这两个类的职责。

![image-20201205194616046](images/image-20201205194616046.png)

无论选择Spring的哪种数据访问方式，你都需要配置一个数据源的引用。Spring提供了在Spring上下文中配置数据源bean的多种方式，包括：

- 通过JDBC驱动程序定义的数据源

- 通过JNDI查找的数据源

- 连接池的数据源

- 嵌入式的数据源

  嵌入式数据库作为应用的一部分运行，而不是应用连接的独立数据库服务器。尽管在生产环境的设置中，它并没有太大的用处，但是对于开发和测试来讲，嵌入式数据库都是很好的可选方案。这是因为每次重启应用或运行测试的时候，都能够重新填充测试数据。

### JDBC模板

在这里，JdbcSpitterRepository类上使用了@Repository注解，这表明它将会在组件扫描的时候自动创建。它的构造器上使用了@Inject注解，因此在创建的时候，会自动获得一个JdbcOperations对象。JdbcOperations是一个接口，定义了JdbcTemplate所实现的操作。通过注入JdbcOperations，而不是具体的JdbcTemplate，能够保证JdbcSpitterRepository通过JdbcOperations接口达到与JdbcTemplate保持松耦合。

## chp11 使用对象-关系映射持久化数据

在数据持久化的世界中，JDBC就像自行车。对于份内的工作，它能很好地完成并且在一些特定的场景下表现出色。但随着应用程序变得越来越复杂，对持久化的需求也变得更复杂。我们需要将对象的属性映射到数据库的列上，并且需要自动生成语句和查询，这样我们就能从无休止的问号字符串中解脱出来。此外，我们还需要一些更复杂的特性：

* 延迟加载（Lazy loading）：随着我们的对象关系变得越来越复杂，有时候我们并不希望立即获取完整的对象间关系。举一个典型的例子，假设我们在查询一组PurchaseOrder对象，而每个对象中都包含一个LineItem对象集合。如果我们只关心PurchaseOrder的属性，那查询出LineItem的数据就毫无意义。而且这可能是开销很大的操作。延迟加载允许我们只在需要的时候获取数据。
* 预先抓取（Eager fetching）：这与延迟加载是相对的。借助于预先抓取，我们可以使用一个查询获取完整的关联对象。如果我们需要PurchaseOrder及其关联的LineItem对象，预先抓取的功能可以在一个操作中将它们全部从数据库中取出来，节省了多次查询的成本。
* 级联（Cascading）：有时，更改数据库中的表会同时修改其他表。回到我们订购单的例子中，当删除Order对象时，我们希望同时在数据库中删除关联的LineItem。

一些可用的框架提供了这样的服务，这些服务的通用名称是对象/关系映射（object-relational mapping，ORM）。在持久层使用ORM工具，可以节省数千行的代码和大量的开发时间。ORM工具能够把你的注意力从容易出错的SQL代码转向如何实现应用程序的真正需求。

### 在Spring中集成Hibernate

Hibernate是在开发者社区很流行的开源持久化框架。它不仅提供了基本的对象关系映射，还提供了ORM工具所应具有的所有复杂功能，比如缓存、延迟加载、预先抓取以及分布式缓存。

### Spring Persistence API (JPA)

简单来讲，基于JPA的应用程序需要使用EntityManagerFactory的实现类来获取EntityManager实例。JPA定义了两种类型的实体管理器：

* 应用程序管理类型（Application-managed）：当应用程序向实体管理器工厂直接请求实体管理器时，工厂会创建一个实体管理器。在这种模式下，程序要负责打开或关闭实体管理器并在事务中对其进行控制。这种方式的实体管理器适合于不运行在Java EE容器中的独立应用程序。

* 容器管理类型（Container-managed）：实体管理器由Java EE创建和管理。应用程序根本不与实体管理器工厂打交道。相反，实体管理器直接通过注入或JNDI来获取。容器负责配置实体管理器工厂。这种类型的实体管理器最适用于Java EE容器，在这种情况下会希望在persistence.xml指定的JPA配置之外保持一些自己对JPA的控制。

### Spring Data

实际上，我们并不需要实现findByUsername()。方法签名已经告诉Spring Data JPA足够的信息来创建这个方法的实现了。

当创建Repository实现的时候，Spring Data会检查Repository接口的所有方法，解析方法的名称，并基于被持久化的对象来试图推测方法的目的。本质上，Spring Data定义了一组小型的领域特定语言（domain-specific language ，DSL），在这里，持久化的细节都是通过Repository方法的签名来描述的。

![image-20201205195129312](images/image-20201205195129312.png)

当Spring Data JPA为Repository接口生成实现的时候，它还会查找名字与接口相同，并且添加了Impl后缀的一个类。如果这个类存在的话，Spring Data JPA将会把它的方法与Spring Data JPA所生成的方法合并在一起。对于SpitterRepository接口而言，要查找的类名为SpitterRepositoryImpl。

# chp12 使用NoSQL数据库

## MongoDB

MongoDB是最为流行的开源文档数据库之一。Spring Data MongoDB提供了三种方式在Spring应用中使用MongoDB：

* 通过注解实现对象-文档映射；
* 使用MongoTemplate实现基于模板的数据库访问；
* 自动化的运行时Repository生成功能。

 为了访问需要认证的MongoDB服务器，MongoClient在实例化的时候必须要有一个MongoCredential的列表。在程序清单12.3中，我们为此创建了一个MongoCredential。为了将凭证信息的细节放在配置类外边，它们是通过注入的Environment对象解析得到的。

@Document和@Id注解类似于JPA的@Entity和@Id注解。我们将会经常使用这两个注解，对于要以文档形式保存到MongoDB数据库的每个Java类型都会使用这两个注解

我们可以看到，Order类添加了@Document注解，这样它就能够借助MongoTemplate或自动生成的Repository进行持久化。其id属性上使用了@Id注解，用来指定它作为文档的ID。除此之外，customer属性上使用了@Field注解，这样的话，当文档持久化的时候customer属性将会映射为名为client的域

并且如果我们不使用@Field注解进行设置的话，那么文档域中的名字将会与对应的Java属性相同。

![image-20201206142004728](images/image-20201206142004728.png)

## Neo4j

文档型数据库会将数据存储到粗粒度的文档中，而图数据库会将数据存储到多个细粒度的节点中，这些节点之间通过关系建立关联。图数据库中的一个节点通常会对应数据库中的一个概念（concept），它会具备描述节点状态的属性。连接两个节点的关联关系可能也会带有属性。

## Redis

Redis是一种特殊类型的数据库，它被称之为key-value存储。顾名思义，key-value存储保存的是键值对。实际上，key-value存储与哈希Map有很大的相似性。可以不太夸张地说，它们就是持久化的哈希Map。

# chp13 启用对缓存的支持

Spring对缓存的支持有两种方式：

* 注解驱动的缓存
* XML声明的缓存

使用Spring的缓存抽象时，最为通用的方式就是在方法上添加`@Cacheable`和`@CacheEvict`注解。

## 填充缓存

我们可以看到，`@Cacheable`和`@CachePut`注解都可以填充缓存，但是它们的工作方式略有差异。

`@Cacheable`首先在缓存中查找条目，如果找到了匹配的条目，那么就不会对方法进行调用了。如果没有找到匹配的条目，方法会被调用并且返回值要放到缓存之中。而`@CachePut`并不会在缓存中检查匹配的值，目标方法总是会被调用，并将返回值添加到缓存之中。

与`@Cacheable`和`@CachePut`不同，`@CacheEvict`能够应用在返回值为void的方法上，而`@Cacheable`和`@CachePut`需要非void的返回值，它将会作为放在缓存中的条目。因为`@CacheEvict`只是将条目从缓存中移除，因此它可以放在任意的方法上，甚至void方法。

## 小结

在这个过程中，我们讨论了缓存实际上是一种面向切面的行为。Spring将缓存实现为一个切面。在使用XML声明缓存规则时，这一点非常明显：我们必须要将缓存通知绑定到一个切点上。

# chp14 保护方法应用

在Spring Security中实现方法级安全性的最常见办法是使用特定的注解，将这些注解应用到需要保护的方法上。这样有几个好处，最重要的是当我们在编辑器中查看给定的方法时，能够很清楚地看到它的安全规则。

Spring Security提供了三种不同的安全注解：

* Spring Security自带的@Secured注解；
* JSR-250的@RolesAllowed注解；
* 表达式驱动的注解，包括@PreAuthorize、@PostAuthorize、@PreFilter和@PostFilter

@Secured注解会使用一个String数组作为参数。每个String值是一个权限，调用这个方法至少需要具备其中的一个权限。通过传递进来ROLE_SPITTER，我们告诉Spring Security只允许具有ROLE_SPITTER权限的认证用户才能调用addSpittle ()方法

但是，@PreAuthorize的功能并不限于这个简单例子所展现的。@PreAuthorize的String类型参数是一个SpEL表达式。借助于SpEL表达式来实现访问决策，我们能够编写出更高级的安全性约束。例如，Spittr应用程序的一般用户只能写140个字以内的Spittle，而付费用户不限制字数

# chp15 使用远程服务

作为一个Java开发者，我们有多种可以使用的远程调用技术，包括：

* 远程方法调用（Remote Method Invocation，RMI）
* Caucho的Hessian和Burlap；
* Spring基于HTTP的远程服务；
* 使用JAX-RPC和JAX-WS的Web Service

远程调用是客户端应用和服务端之间的会话。在客户端，它所需要的一些功能并不在该应用的实现范围之内，所以应用要向能提供这些功能的其他系统寻求帮助。而远程应用通过远程服务暴露这些功能。

![image-20201210200250369](images/image-20201210200250369.png)

在所有的模型中，服务都作为Spring所管理的bean配置到我们的应用中。这是通过一个代理工厂bean实现的，这个bean能够把远程服务像本地对象一样装配到其他bean的属性中去。图15.2展示了它是如何工作的。

![image-20201210200259674](images/image-20201210200259674.png)

在服务器端，我们可以使用表15.1所列出的任意一种模型将Spring管理的bean发布为远程服务。图15.3展示了远程导出器（remote exporter）如何将bean方法发布为远程服务。

![image-20201210200310185](images/image-20201210200310185.png)

## RMI
RmiServiceExporter可以把任意Spring管理的bean发布为RMI服务。如图15.4所示，RmiServiceExporter把bean包装在一个适配器类中，然后适配器类被绑定到RMI注册表中，并且代理到服务类的请求——在本例中服务类也就是SpitterServiceImpl。

![image-20201210200322100](images/image-20201210200322100.png)

Spring的RmiProxyFactoryBean是一个工厂bean，该bean可以为RMI服务创建代理。使用RmiProxyFactoryBean引用SpitterService的RMI服务是非常简单的，只需要在客户端的Spring配置中增加如下的@Bean方法

服务的URL是通过RmiProxyFactoryBean的serviceUrl属性来设置的，在这里，服务名被设置为SpitterService，并且声明服务是在本地机器上的；同时，服务提供的接口由serviceInterface属性来指定。图15.5展示了客户端和RMI代理的交互。

![image-20201210200336027](images/image-20201210200336027.png)

RMI是一种实现远程服务交互的好办法，但是它存在某些限制。首先，RMI很难穿越防火墙，这是因为RMI使用任意端口来交互——这是防火墙通常所不允许的。在企业内部网络环境中，我们通常不需要担心这个问题。但是如果在互联网上运行，我们用RMI可能会遇到麻烦。即使RMI提供了对HTTP的通道的支持（通常防火墙都允许），但是建立这个通道也不是件容易的事。

## 使用Hessian和Burlap发布远程服务
Caucho Technology（Resin应用服务器背后的公司）开发了一套应对RMI限制的远程调用解决方案。实际上，Caucho提供了两种解决方案：Hessian和Burlap。

## 使用Spring的HttpInvoker
HTTP invoker是一个新的远程调用模型，作为Spring框架的一部分，能够执行基于HTTP的远程调用（让防火墙不为难），并使用Java的序列化机制（让开发者也乐观其变）。使用基于HTTP invoker的服务和使用基于Hessian/Burlap的服务非常相似。

要记住HTTP invoker有一个重大的限制：它只是一个Spring框架所提供的远程调用解决方案。这意味着客户端和服务端必须都是Spring应用。并且，至少目前而言，也隐含表明客户端和服务端必须是基于Java的。另外，因为使用了Java的序列化机制，客户端和服务端必须使用相同版本的类（与RMI类似）。

## 在Spring中创建JAX-WS Web服务

# chp16 使用Spring MVC创建REST API

## REST基础知识

REST与RPC几乎没有任何关系。RPC是面向服务的，并关注于行为和动作；而REST是面向资源的，强调描述应用程序的事物和名词。

为了理解REST是什么，我们将它的首字母缩写拆分为不同的构成部分：

* 表述性（Representational）：REST资源实际上可以用各种形式来进行表述，包括XML、JSON（JavaScript Object Notation）甚至HTML——最适合资源使用者的任意形式；
* 状态（State）：当使用REST的时候，我们更关注资源的状态而不是对资源采取的行为；
* 转移（Transfer）：REST涉及到转移资源数据，它以某种表述性形式从一个应用转移到另一个应用

更简洁地讲，REST就是将资源的状态以最适合客户端或服务端的形式从服务器端转移到客户端（或者反过来）。

REST中会有行为，它们是通过HTTP方法来定义的。具体来讲，也就是GET、POST、PUT、DELETE、PATCH以及其他的HTTP方法构成了REST中的动作。这些HTTP方法通常会匹配为如下的CRUD动作：

* Create：POST
* Read：GET
* Update：PUT/PATCH
* Delete：DELETE

Spring提供了两种方法将资源的Java表述形式转换为发送给客户端的表述形式：

* 内容协商（Content negotiation）：选择一个视图，它能够将模型渲染为呈现给客户端的表述形式

* 消息转换器（Message conversion）：通过一个消息转换器将控制器所返回的对象转换为呈现给客户端的表述形式
@ResponseBody注解会告知Spring，我们要将返回的对象作为资源发送给客户端，并将其转换为客户端可接受的表述形式。
如果在控制器类上使用@RestController来代替@Controller的话，Spring将会为该控制器的所有处理方法应用消息转换功能。我们不必为每个方法都添加@ResponseBody了。

## 提供资源之外的其它内容
### 发送错误信息到客户端
为了设置响应状态码，我们首先使用ResponseEntity，但是稍后我们借助异常处理器以及@ResponseStatus，避免使用ResponseEntity，从而让代码更加整洁。

### 在响应中设置头部信息
简单信息：添加@ResponseStatus注解

当创建新资源的时候，将资源的URL放在响应的Location头部信息中，并返回给客户端是一种很好的方式。因此，我们需要有一种方式来填充响应头部信息，此时我们的老朋友ResponseEntity就能提供帮助了

我们其实没有必要手动构建URL，Spring提供了UriComponentsBuilder，可以给我们一些帮助。它是一个构建类，通过逐步指定URL中的各种组成部分（如host、端口、路径以及查询），我们能够使用它来构建UriComponents实例。借助UriComponentsBuilder所构建的UriComponents对象，我们就能获得适合设置给Location头部信息的URI。

在处理器方法所得到的UriComponentsBuilder中，会预先配置已知的信息如host、端口以及Servlet内容。它会从处理器方法所对应的请求中获取这些基础信息。基于这些信息，代码会通过设置路径的方式构建UriComponents其余的部分。

## 编写REST客户端
RestTemplate定义了36个与REST资源交互的方法，其中的大多数都对应于HTTP的方法。其实，这里面只有11个独立的方法，其中有十个有三种重载形式，而第十一个则重载了六次，这样一共形成了36个方法。

# chp17 Spring消息
## 发送消息
间接性也是异步消息的关键所在。当一个应用向另一个应用发送消息时，两个应用之间没有直接的联系。相反的是，发送方的应用程序会将消息交给一个服务，由服务确保将消息投递给接收方应用程序。

在异步消息中有两个主要的概念：消息代理（message broker）和目的地（destination）。当一个应用发送消息时，会将消息交给一个消息代理。消息代理实际上类似于邮局。消息代理可以确保消息被投递到指定的目的地，同时解放发送者，使其能够继续进行其他的业务。

尽管不同的消息系统会提供不同的消息路由模式，但是有两种通用的目的地：队列（queue）和主题（topic）。每种类型都与特定的消息模型相关联，分别是点对点模型（队列）和发布/订阅模型（主题）。

Java消息服务（Java Message Service ，JMS）是一个Java标准，定义了使用消息代理的通用API。在JMS出现之前，每个消息代理都有私有的API，这就使得不同代理之间的消息代码很难通用。但是借助JMS，所有遵从规范的实现都使用通用的接口，这就类似于JDBC为数据库操作提供了通用的接口一样。

多年来，JMS一直是Java应用中主流的消息解决方案。但是对于Java和Spring开发者来说，JMS并不是唯一的消息可选方案。在过去的几年中，高级消息队列协议（Advanced Message Queuing Protocol ，AMQP）得到了广泛的关注。因此，Spring也为通过AMQP发送消息提供了支持，这就是我们下面要讲解的内容。

AMQP的生产者并不会直接将消息发布到队列中。AMQP在消息的生产者以及传递信息的队列之间引入了一种间接的机制：Exchange。这种关系如图17.8所示。

![image-20201210200420165](images/image-20201210200420165.png)

# chp18 使用WebSocket和STOMP实现消息功能

WebSocket协议提供了通过一个套接字实现全双工通信的功能。除了其他的功能之外，它能够实现Web浏览器和服务器之间的异步通信。全双工意味着服务器可以发送消息给浏览器，浏览器也可以发送消息给服务器。

Spring 4.0为WebSocket通信提供了支持，包括：

* 发送和接收消息的低层级API；
* 发送和接收消息的高级API；
* 用来发送消息的模板；
* 支持SockJS，用来解决浏览器端、服务器以及代理不支持WebSocket的问题。

# chp19 使用Spring发送Email

# chp20　使用JMX管理Spring Bean
Spring对DI的支持是通过在应用中配置bean属性，这是一种非常不错的方法。不过，一旦应用已经部署并且正在运行，单独使用DI并不能帮助我们改变应用的配置。假设我们希望深入了解正在运行的应用并要在运行时改变应用的配置，此时，就可以使用Java管理扩展（Java Manage- ment Extensions，JMX）了。

JMX这项技术能够让我们管理、监视和配置应用。这项技术最初作为Java的独立扩展，从Java 5开始，JMX已经成为标准的组件。

使用JMX管理应用的核心组件是托管bean（managed bean，MBean）。所谓的MBean就是暴露特定方法的JavaBean，这些方法定义了管理接口。

Spring的MBeanExporter是将Spring Bean转变为MBean的关键。MBeanExporter可以把一个或多个Spring bean导出为MBean服务器（MBean server）内的模型 MBean。MBean服务器（有时候也被称为MBean代理）是MBean生存的容器。对MBean的访问，也是通过MBean服务器来实现的。

如图20.1所示，将Spring bean导出为JMX MBean之后，可以使用基于JMX的管理工具（例如JConsole或者VisualVM）查看正在运行的应用程序，显示bean的属性并调用bean的方法。

图20.1

# chp21 借助Spring Boot简化Spring开发

在Spring家族中，Spring Boot是令人兴奋（也许我敢说它是改变游戏规则的）的新项目。它提供了四个主要的特性，能够改变开发Spring应用程序的方式：

* Spring Boot Starter：它将常用的依赖分组进行了整合，将其合并到一个依赖中，这样就可以一次性添加到项目的Maven或Gradle构建中；
* 自动配置：Spring Boot的自动配置特性利用了Spring 4对条件化配置的支持，合理地推测应用所需的bean并自动化配置它们；
* 命令行接口（Command-line interface，CLI）：Spring Boot的CLI发挥了Groovy编程语言的优势，并结合自动配置进一步简化Spring应用的开发；
* Actuator：它为Spring Boot应用添加了一定的管理特性。




## 注意事项

***千万注意`@ComponentScan`中`basePackages`的路径！！***

**Resource 文件夹中的文件要手动重新创建，不要复制黏贴**

**Hibernate和Spring版本要一致**

maven can't solve, 下载不了jar包，考虑:

配置中央仓库为阿里云

```xml
<mirror>  
      <id>alimaven</id> 
      <mirrorOf>central</mirrorOf> 
      <name>aliyun maven</name>  
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>;           
</mirror>
```

