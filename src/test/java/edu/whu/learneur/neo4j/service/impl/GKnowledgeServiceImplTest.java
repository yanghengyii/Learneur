package edu.whu.learneur.neo4j.service.impl;

import edu.whu.learneur.neo4j.domain.Relation;
import edu.whu.learneur.neo4j.domain.Knowledge;
import edu.whu.learneur.neo4j.dto.KnowledgeAndRelations;
import edu.whu.learneur.neo4j.service.GKnowledgeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class GKnowledgeServiceImplTest {

    @Autowired
    GKnowledgeService gKnowledgeService;

    @Test
    void getTagById() {

    }

    @Test
    void getTagByName() {

    }

    @Test
    void addTag() {
        Knowledge c = new Knowledge("c","c");
        Knowledge java = new Knowledge("java","java");
        Knowledge dataStructure = new Knowledge("dataStructure","dataStructure");
        Knowledge sysProgram = new Knowledge("sysProgram","sysProgram");
        Knowledge oop = new Knowledge("oop","oop");
//        Relation common = new Relation("common","common",java);
//        Relation pre = new Relation("pre","pre",dataStructure);
//        Relation include = new Relation("include","include",oop);
//        Relation associated = new Relation("associated","associated",sysProgram);
//        c.setCommons(
//                new ArrayList<Relation>(){{
//                    add(common);
//                }}
//        );
//        c.setPreKnowledges(
//                new ArrayList<Relation>(){{
//                    add(pre);
//                }}
//        );
//        c.setIncludes(
//                new ArrayList<Relation>(){{
//                    add(include);
//                }}
//        );
//        c.setAssociateds(
//                new ArrayList<Relation>(){{
//                    add(associated);
//                }}
//        );


        gKnowledgeService.addTag(c);
    }

    @Test
    void testGetTagByName() {
        List<Knowledge> c = gKnowledgeService.getTagByName("c");
        System.out.println(c);
    }

    @Test
    void getGraphByName() {
        KnowledgeAndRelations c = gKnowledgeService.getGraphByName("c");
        System.out.println(c);
    }

    @Test
    void updateTagById() {
        Knowledge o = gKnowledgeService.updateTagById(new Knowledge("ccc","ccc"),5L);
        System.out.println(o);
    }

    @Test
    void updateRelationById() {
//        Map[] o = gKnowledgeService.updateRelationById(3L,"include","includesss");
//        System.out.println(o);
    }


    @Test
    void getFirst25Knowledge() {
        List<Knowledge> o = gKnowledgeService.getFirst25Knowledge();
    }

//    @Test
//    void testUpdateRelationById() {
//        Map[] o = gKnowledgeService.updateRelationById(5L,"OKKKKK","includes");
//        System.out.println(o);
//    }

    @Test
    void updateRelationById2() {
        List<Relation> o = gKnowledgeService.updateRelationById(7L,"adadasdqsd","includes");
        System.out.println(o);
    }

    @Test
    void testAddRelation() {
        Relation o = gKnowledgeService.addRelation(5L,6L,"include","include");
        System.out.println(o);
    }

    @Test
    void testGetGraphByName() {
        KnowledgeAndRelations o = gKnowledgeService.getGraphByName("c");
        System.out.println(o);
    }

    @Test
    void addRelationByNames() {
        gKnowledgeService.addTag(new Knowledge("spring","Spring是Java EE编程领域的一个轻量级开源框架，该框架由一个叫Rod Johnson的程序员在 2002 年最早提出并随后创建，是为了解决企业级编程开发中的复杂性，实现敏捷开发的应用型框架 。"));
        gKnowledgeService.addTag(new Knowledge("spring基础语法","Spring基础语法是指Spring中主要使用的基础语法，主要基于Java"));
        gKnowledgeService.addTag(new Knowledge("java","Java是一门面向对象的编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。"));
        gKnowledgeService.addTag(new Knowledge("java基础语法","一个Java程序可以认为是一系列对象的集合，而这些对象通过调用彼此的方法来协同工作。"));
        gKnowledgeService.addTag(new Knowledge("maven","aven 翻译为\\'专家\\'、\\'内行\\'，是 Apache 下的一个纯 Java 开发的开源项目。基于项目对象模型（缩写：POM）概念，Maven利用一个中央信息片断能管理一个项目的构建、报告和文档等步骤。"));
        gKnowledgeService.addTag(new Knowledge("jar包","在软件领域，JAR文件（Java归档，英语：Java Archive）是一种软件包文件格式，通常用于聚合大量的Java类文件、相关的元数据和资源（文本、图片等）文件到一个文件，以便开发Java平台应用软件或库。"));
        gKnowledgeService.addTag(new Knowledge("pom.xml","POM是项目对象模型(Project Object Model)的简称,它是Maven项目中的文件，使用XML表示，名称叫做pom.xml。作用类似ant的build.xml文件，功能更强大。该文件用于管理：源代码、配置文件、开发者的信息和角色、问题追踪系统、组织信息、项目授权、项目的url、项目的依赖关系等等。事实上，在Maven世界中，project可以什么都没有，甚至没有代码，但是必须包含pom.xml文件。"));
        gKnowledgeService.addTag(new Knowledge("maven repository","在 Maven 的术语中，仓库是一个位置（place）。Maven 仓库是项目中依赖的第三方库，这个库所在的位置叫做仓库。在 Maven 中，任何一个依赖、插件或者项目构建的输出，都可以称之为构件。Maven 仓库能帮助我们管理构件（主要是JAR），它就是放置所有JAR文件（WAR，ZIP，POM等等）的地方。"));
        gKnowledgeService.addTag(new Knowledge("spring Core","Spring core是用来负责发现、创建并处理bean之间的关系的一个工具包。core把bean的创建、bean的互相注入的方法定义完毕，上层服务只需要进行调用；提供功能但不调用就是spring core的存在意义。"));
        gKnowledgeService.addTag(new Knowledge("bean","Spring Bean是被实例的，组装的及被Spring 容器管理的Java对象。"));
        gKnowledgeService.addTag(new Knowledge("annotation","Java 语言中的类、方法、变量、参数和包等都可以被标注。和 Javadoc 不同，Java 标注可以通过反射获取标注内容。在编译器生成类文件时，标注可以被嵌入到字节码中。Java 虚拟机可以保留标注内容，在运行时可以获取到标注内容 。 当然它也支持自定义 Java 标注。"));
        gKnowledgeService.addTag(new Knowledge("spring Injection","通过spring工厂和配置文件为spring的成员变量赋值"));
        gKnowledgeService.addTag(new Knowledge("spring boot","Spring Boot是由Pivotal团队提供的全新框架，其设计目的是用来简化新Spring应用的初始搭建以及开发过程。"));
        gKnowledgeService.addTag(new Knowledge("ioc container","IoC全称为inversion of control（控制反转），依赖注入是一种IOC的表现形式，主要表现为让第三方来管理自己的依赖。"));
        gKnowledgeService.addTag(new Knowledge("http/https","HTTP协议是超文本传输协议的缩写，英文是Hyper Text Transfer Protocol。它是从WEB服务器传输超文本标记语言(HTML)到本地浏览器的传送协议。"));
        gKnowledgeService.addTag(new Knowledge("tomcat","Tomcat 服务器是一个免费的开放源代码的Web 应用服务器，属于轻量级应用服务器，在中小型系统和并发访问用户不是很多的场合下被普遍使用，是开发和调试JSP 程序的首选。"));
        gKnowledgeService.addTag(new Knowledge("jetty","Jetty 是一个开源的servlet容器，它为基于Java的web容器，例如JSP和servlet提供运行环境。"));
        gKnowledgeService.addTag(new Knowledge("spring snitialier","Spring Initializer 是帮助我们快速生成 Spring Boot 项目的工具，它是以 Web 网站的形式对外提供的，但是它的 API 也是公开的，因此我们可以在各种 IDE 平台，编辑器平台中见到它的身影。"));
        gKnowledgeService.addTag(new Knowledge("spring controller","在Spring中 Controller注解用于指示Spring类的实例是一个控制器，相对于实现Controller接口变得更加简单。而且实现Controller接口只能处理一个单一的请求，而是用@Controller注解可以支持同时处理多个请求动作，更加灵活。"));
        gKnowledgeService.addTag(new Knowledge("restful api","RESTful API 是两个计算机系统用于通过互联网安全地交换信息的接口。大多数业务应用程序必须与其他内部和第三方应用程序进行通信才能执行各种任务。"));
        gKnowledgeService.addTag(new Knowledge("swagger","Swagger 是一个规范且完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务。"));
        gKnowledgeService.addTag(new Knowledge("web api","Web API是网络应用程序接口。包含了广泛的功能，网络应用通过API接口，可以实现存储服务、消息服务、计算服务等能力，利用这些能力可以进行开发出强大功能的web应用。"));
        gKnowledgeService.addTag(new Knowledge("http status code","当浏览者访问一个网页时，浏览者的浏览器会向网页所在服务器发出请求。当浏览器接收并显示网页前，此网页所在的服务器会返回一个包含 HTTP 状态码的信息头（server header）用以响应浏览器的请求。"));
        gKnowledgeService.addTag(new Knowledge("Postman","Postman是一款可用于测试接口的HTTP工具"));
        gKnowledgeService.addTag(new Knowledge("Spring Service","Service是项目中用于处理业务逻辑的，因为每种数据在做某种操作时，应该都有某些规则"));
        gKnowledgeService.addTag(new Knowledge("Vue","Vue是一套用于构建用户界面的渐进式JavaScript框架。"));
        gKnowledgeService.addTag(new Knowledge("react","React 是一个用于构建用户界面的 JAVASCRIPT 库。"));
        gKnowledgeService.addTag(new Knowledge("HTML","HTML的全称为超文本标记语言，是一种标记语言。它包括一系列标签．通过这些标签可以将网络上的文档格式统一，使分散的Internet资源连接为一个逻辑整体。"));
        gKnowledgeService.addTag(new Knowledge("JavaScript","JavaScript（简称“JS”） 是一种具有函数优先的轻量级，解释型或即时编译型的编程语言。"));
        gKnowledgeService.addTag(new Knowledge("CSS","CSS (Cascading Style Sheets，层叠样式表），是一种用来为结构化文档（如 HTML 文档或 XML 应用）添加样式（字体、间距和颜色等）的计算机语言，CSS 文件扩展名为 .css。"));
        gKnowledgeService.addTag(new Knowledge("Spring Data","Spring 的一个子项目。用于简化数据库访问，支持NoSQL和关系数据库存储。其主要目标是使数据库的访问变得方便快捷。"));
        gKnowledgeService.addTag(new Knowledge("NEO4j","Neo4j是一个高性能的,NOSQL图形数据库，它将结构化数据存储在网络上而不是表中。它是一个嵌入式的、基于磁盘的、具备完全的事务特性的Java持久化引擎。"));
        gKnowledgeService.addTag(new Knowledge("MyBatis","MyBatis 是一款优秀的持久层框架，它支持自定义 SQL、存储过程以及高级映射。"));
        gKnowledgeService.addTag(new Knowledge("MyBatis-Plus","MyBatis-Plus（简称 MP）是一个 MyBatis的增强工具，在 MyBatis 的基础上只做增强不做改变，为简化开发、提高效率而生。"));
        gKnowledgeService.addTag(new Knowledge("MyBatis-Plus code-generation","可用于自动生成代码的库"));
        gKnowledgeService.addTag(new Knowledge("ORM","对象关系映射（Object Relational Mapping，简称ORM）模式是一种为了解决面向对象与关系数据库存在的互不匹配的现象的技术。"));
        gKnowledgeService.addTag(new Knowledge("JPA","JPA 是Java Persistence API的缩写，是一套由Java官方制定的ORM标准。当制定这套标准以后，市场上就出现很多JPA框架。如：OpenJPA（apache），EclipseTop（linktop）(eclipse)，Hibernate。"));
        gKnowledgeService.addTag(new Knowledge("JPARepository","JpaRepository接口同时拥有了基本CRUD功能以及分页功能。"));
        gKnowledgeService.addTag(new Knowledge("JDBC","JDBC API是一个Java API，可以访问任何类型表列数据，特别是存储在关系数据库中的数据。JDBC代表Java数据库连接。"));
        gKnowledgeService.addTag(new Knowledge("SQL","SQL (Structured Query Language) 是具有数据操纵和数据定义等多种功能的数据库语言，这种语言具有交互性特点，能为用户提供极大的便利，数据库管理系统应充分利用SQL语言提高计算机应用系统的工作质量与效率。"));
        gKnowledgeService.addTag(new Knowledge("Redis","Redis（Remote Dictionary Server )，即远程字典服务，是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value数据库，并提供多种语言的API。"));
        gKnowledgeService.addTag(new Knowledge("junit","JUnit是一个Java语言的单元测试框架。"));
        gKnowledgeService.addTag(new Knowledge("Spring AOP","Spring框架的关键组件之一是面向方面编程(AOP)。 面向方面的编程需要将程序逻辑分解成不同的部分。 跨越应用程序的多个点的功能被称为交叉切割问题，这些交叉关切在概念上与应用程序的业务逻辑分开。"));
        gKnowledgeService.addTag(new Knowledge("Exception Handler","@ExceptionHandler注解一般是用来自定义异常的。可以认为它是一个异常拦截器（处理器）。"));
        gKnowledgeService.addTag(new Knowledge("Spring Security","Spring Security 是spring项目之中的一个安全模块，可以非常方便与spring项目无缝集成。"));
        gKnowledgeService.addTag(new Knowledge("Authentication","在Spring Security中，通过Authentication来封装用户的验证请求信息，Authentication可以是需要验证和已验证的用户请求信息封装。"));
        gKnowledgeService.addTag(new Knowledge("TWT Authentication","TWT Authentication是一个基于Spring Security的身份验证框架，它提供了一个简单的身份验证API，可以轻松地将身份验证集成到任何应用程序中。"));
        gKnowledgeService.addTag(new Knowledge("Authorization","授权决定了您访问系统的能力以及达到的程度。验证成功后，系统验证您的身份后，即可授权您访问系统资源。"));
        gKnowledgeService.addTag(new Knowledge("Shiro","Apache Shiro 是 Java 的一个安全框架。"));
        gKnowledgeService.addTag(new Knowledge("JWT","JWT全称JSON Web Token，是javaweb官方提供的一种封装用户数据，保证权限访问的字符串。"));
        gKnowledgeService.addTag(new Knowledge("cookie","Cookie，有时也用其复数形式 Cookies。类型为“小型文本文件”，是某些网站为了辨别用户身份，进行Session跟踪而储存在用户本地终端上的数据（通常经过加密），由用户客户端计算机暂时或永久保存的信息 。"));
        gKnowledgeService.addTag(new Knowledge("session","在计算机专业术语中，Session是指一个终端用户与交互系统进行通信的时间间隔，通常指从注册进入系统到注销退出系统之间所经过的时间。以及如果需要的话，可能还有一定的操作空间。"));
        gKnowledgeService.addTag(new Knowledge("cache","缓存（cache），原始意义是指访问速度比一般随机存取存储器（RAM）快的一种高速存储器，通常它不像系统主存那样使用DRAM技术，而使用昂贵但较快速的SRAM技术。缓存的设置是所有现代计算机系统发挥高性能的重要因素之一。"));
        gKnowledgeService.addTag(new Knowledge("算法","算法（Algorithm）是指解题方案的准确而完整的描述，是一系列解决问题的清晰指令，算法代表着用系统的方法描述解决问题的策略机制。"));
        gKnowledgeService.addTag(new Knowledge("高级语言","高级语言（High-level programming language）是一种独立于机器，面向过程或对象的语言。高级语言是参照数学语言而设计的近似于日常会话的语言。"));
        gKnowledgeService.addTag(new Knowledge("C++","C++ 是一种高级语言，它是由 Bjarne Stroustrup 于 1979 年在贝尔实验室开始设计开发的。C++ 进一步扩充和完善了 C 语言，是一种面向对象的程序设计语言。"));
        gKnowledgeService.addTag(new Knowledge("C","C 语言是一种通用的、面向过程式的计算机程序设计语言。1972 年，为了移植与开发 UNIX 操作系统，丹尼斯·里奇在贝尔电话实验室设计开发了 C 语言。"));
        gKnowledgeService.addTag(new Knowledge("Python","Python 是一种解释型、面向对象、动态数据类型的高级程序设计语言。"));
        gKnowledgeService.addTag(new Knowledge("递归","程序调用自身的编程技巧称为递归（ recursion）。"));
        gKnowledgeService.addTag(new Knowledge("动态规划","动态规划（Dynamic Programming，DP）是运筹学的一个分支，是求解决策过程最优化的过程。"));
        gKnowledgeService.addTag(new Knowledge("归纳法","归纳法一般指归纳推理，是一种由个别到一般的推理。由一定程度的关于个别事物的观点过渡到范围较大的观点，由特殊具体的事例推导出一般原理、原则的解释方法。"));
        gKnowledgeService.addTag(new Knowledge("分治法","分治法，字面意思是“分而治之”，就是把一个复杂的1问题分成两个或多个相同或相似的子问题，再把子问题分成更小的子问题直到最后子问题可以简单地直接求解，原问题的解即子问题的解的合并"));
        gKnowledgeService.addTag(new Knowledge("最先割技术","最先割技术包含了贪心算法和图的遍历"));
        gKnowledgeService.addTag(new Knowledge("图的遍历","按照一定顺序访问图中所有顶点的过程称为图的遍历。"));
        gKnowledgeService.addTag(new Knowledge("贪心算法","贪心算法（greedy algorithm，又称贪婪算法）是指，在对问题求解时，总是做出在当前看来是最好的选择。也就是说，不从整体最优上加以考虑，算法得到的是在某种意义上的局部最优解。"));
        gKnowledgeService.addTag(new Knowledge("最小生成树","在给定一张无向图，如果在它的子图中，任意两个顶点都是互相连通，并且是一个树结构，那么这棵树叫做生成树。当连接顶点之间的图有权重时，权重之和最小的树结构为最小生成树！"));
        gKnowledgeService.addTag(new Knowledge("Huffman","霍夫曼（Huffman）编码是一种编码方式，主要用于数据文件的压缩。它的主要思想是放弃文本文件的普通保存方式：不再使用7位或8位二进制数表示每一个字符，而是用较少的比特表示出现频率高的字符，用较多的比特表示出现频率低的字符。"));
        gKnowledgeService.addTag(new Knowledge("BFS","广度优先搜索（也称宽度优先搜索，缩写BFS，以下采用广度来描述）是连通图的一种遍历策略。因为它的思想是从一个顶点V0开始，辐射状地优先遍历其周围较广的区域，因此得名。"));
        gKnowledgeService.addTag(new Knowledge("DFS","沿着树的深度遍历树的节点，尽可能深的搜索树的分支。当节点v的所在边都己被探寻过或者在搜寻时结点不满足条件，搜索将回溯到发现节点v的那条边的起始节点。"));
        gKnowledgeService.addTag(new Knowledge("离散数学","离散数学（Discrete mathematics）是研究离散量的结构及其相互关系的数学学科，是现代数学的一个重要分支。离散的含义是指不同的连接在一起的元素，主要是研究基于离散量的结构和相互间的关系，其对象一般是有限个或可数个元素。"));
        gKnowledgeService.addTag(new Knowledge("函数关系","确定性现象之间的关系常常表现为函数关系，即一种现象的数量确定以后，另一种现象的数量也随之完全确定，表现为一种严格的函数关系。"));
        gKnowledgeService.addTag(new Knowledge("集合","集合，简称集，是数学中一个基本概念，也是集合论的主要研究对象。集合论的基本理论创立于19世纪，关于集合的最简单的说法就是在朴素集合论（最原始的集合论）中的定义，即集合是“确定的一堆东西”，集合里的“东西”则称为元素。"));
        gKnowledgeService.addTag(new Knowledge("递推关系","如果数列f的第n项与它前一项或几项的关系可以用一个式子来表示，那么这个公式叫做这个数列的递推公式"));
        gKnowledgeService.addTag(new Knowledge("鸽巢数据","鸽巢原理是离散数学学习种一大难点，主要在于如何去构造鸽子和巢的抽象化，本文是整理了一些笔者学习中遇到的典型例题，加了一些笔者自己的思路，希望能帮助学习离散数学的同学。"));
        gKnowledgeService.addTag(new Knowledge("数据结构","数据结构是计算机存储、组织数据的方式。数据结构是指相互之间存在一种或多种特定关系的数据元素的集合。"));
        gKnowledgeService.addTag(new Knowledge("数组","数组（Array）是有序的元素序列。"));
        gKnowledgeService.addTag(new Knowledge("链表","链表是一种物理存储单元上非连续、非顺序的存储结构，数据元素的逻辑顺序是通过链表中的指针链接次序实现的。"));
        gKnowledgeService.addTag(new Knowledge("图","在数学中，图是描述于一组对象的结构，其中某些对象对在某种意义上是“相关的”。"));
        gKnowledgeService.addTag(new Knowledge("数据结构-树","树是n（n>=0）个结点的有限集。当n = 0时，称为空树。"));
        gKnowledgeService.addTag(new Knowledge("二叉树","二叉树是另一种树形结构，其特点是每个结点至多只有两棵子树( 即二叉树中不存在度大于2的结点)，并且二叉树的子树有左右之分，其次序不能任意颠倒。"));
        gKnowledgeService.addTag(new Knowledge("堆","堆(Heap)是计算机科学中一类特殊的数据结构，是最高效的优先级队列。堆通常是一个可以被看做一棵完全二叉树的数组对象。"));
        gKnowledgeService.addTag(new Knowledge("B树","B-树是一种平衡的多路查找树，注意： B树就是B-树，\"-\"是个连字符号，不是减号 。"));
        gKnowledgeService.addTag(new Knowledge("并查集","并查集被很多OIer认为是最简洁而优雅的数据结构之一，主要用于解决一些元素分组的问题。它管理一系列不相交的集合，并支持两种操作："));
        gKnowledgeService.addTag(new Knowledge("算法复杂度","算法复杂度是指算法在编写成可执行程序后，运行时所需要的资源，资源包括时间资源和内存资源。应用于数学和计算机导论。"));
        gKnowledgeService.addTag(new Knowledge("时间复杂度","一个算法中的语句执行次数称为语句频度或时间频度，表示为T（n），n表示问题的规模"));
        gKnowledgeService.addTag(new Knowledge("空间复杂度","算法的空间复杂度通过计算算法所需的存储空间实现，算法空间复杂度的计算公式记作：S(n)=O(f(n))，其中，n为问题的规模，f(n)为语句关于n所占存储空间的函数。"));
        gKnowledgeService.addRelationByNames("高级语言","C++","include");
        gKnowledgeService.addRelationByNames("C++","Java","Common");
        gKnowledgeService.addRelationByNames("Python","Java","Common");
        gKnowledgeService.addRelationByNames("C","C++","Common");
        gKnowledgeService.addRelationByNames("高级语言","C","include");
        gKnowledgeService.addRelationByNames("高级语言","Java","include");
        gKnowledgeService.addRelationByNames("高级语言","Python","include");
        gKnowledgeService.addRelationByNames("算法","高级语言","associated");
        gKnowledgeService.addRelationByNames("算法","递归","include");
        gKnowledgeService.addRelationByNames("算法","最先割技术","include");
        gKnowledgeService.addRelationByNames("算法","算法复杂度","preKnowledge");
        gKnowledgeService.addRelationByNames("算法","数据结构","preKnowledge");
        gKnowledgeService.addRelationByNames("算法","离散数学","preKnowledge");
        gKnowledgeService.addRelationByNames("离散数学","函数关系","include");
        gKnowledgeService.addRelationByNames("离散数学","集合","include");
        gKnowledgeService.addRelationByNames("离散数学","递推关系","include");
        gKnowledgeService.addRelationByNames("离散数学","鸽巢原理","include");
        gKnowledgeService.addRelationByNames("离散数学","图","include");
        gKnowledgeService.addRelationByNames("递归","归纳法","include");
        gKnowledgeService.addRelationByNames("递归","动态规划","include");
        gKnowledgeService.addRelationByNames("递归","分治法","include");
        gKnowledgeService.addRelationByNames("数据结构","数组","include");
        gKnowledgeService.addRelationByNames("数据结构","链表","include");
        gKnowledgeService.addRelationByNames("数据结构","图","include");
        gKnowledgeService.addRelationByNames("数据结构","数据结构-树","include");
        gKnowledgeService.addRelationByNames("数据结构","并查集","include");
        gKnowledgeService.addRelationByNames("数据结构-树","堆","include");
        gKnowledgeService.addRelationByNames("数据结构-树","二叉树","include");
        gKnowledgeService.addRelationByNames("数据结构-树","B树","include");
        gKnowledgeService.addRelationByNames("数据结构-树","并查集","common");
        gKnowledgeService.addRelationByNames("算法复杂度","时间复杂度","include");
        gKnowledgeService.addRelationByNames("算法复杂度","空间复杂度","include");
        gKnowledgeService.addRelationByNames("最先割技术","贪心算法","include");
        gKnowledgeService.addRelationByNames("最先割技术","图的遍历","include");
        gKnowledgeService.addRelationByNames("贪心算法","最小生成树","include");
        gKnowledgeService.addRelationByNames("图的遍历","DFS","include");
        gKnowledgeService.addRelationByNames("图的遍历","BFS","include");
        gKnowledgeService.addRelationByNames("Spring","Spring AOP","associated");
        gKnowledgeService.addRelationByNames("Spring","Spring Security","associated");
        gKnowledgeService.addRelationByNames("Spring AOP","Exception Handler","associated");
        gKnowledgeService.addRelationByNames("Spring Security","Authorization","include");
        gKnowledgeService.addRelationByNames("Spring Security","Authorization","include");
        gKnowledgeService.addRelationByNames("Spring Security","Shiro","associated");
        gKnowledgeService.addRelationByNames("Spring Security","TWT Authentication","include");
        gKnowledgeService.addRelationByNames("Spring Security","cache","include");
        gKnowledgeService.addRelationByNames("TWT Authentication","cookie","include");
        gKnowledgeService.addRelationByNames("TWT Authentication","session","include");
        gKnowledgeService.addRelationByNames("cookie","session","associated");
        gKnowledgeService.addRelationByNames("Authorization","Authorization","associated");
        gKnowledgeService.addRelationByNames("Spring","Spring Data","include");
        gKnowledgeService.addRelationByNames("Spring","Spring基础语法","include");
        gKnowledgeService.addRelationByNames("Spring","maven","associated");
        gKnowledgeService.addRelationByNames("Spring","Spring Core","include");
        gKnowledgeService.addRelationByNames("Spring","Spring Boot","associated");
        gKnowledgeService.addRelationByNames("Spring基础语法","Java","preKnowledge");
        gKnowledgeService.addRelationByNames("Java","Java基础语法","preKnowledge");
        gKnowledgeService.addRelationByNames("maven","Jar包","associated");
        gKnowledgeService.addRelationByNames("maven","pom.xml","associated");
        gKnowledgeService.addRelationByNames("maven","repository","associated");
        gKnowledgeService.addRelationByNames("Spring Core","bean","associated");
        gKnowledgeService.addRelationByNames("Spring Core","annotation","associated");
        gKnowledgeService.addRelationByNames("Spring Core","Spring Injection","associated");
        gKnowledgeService.addRelationByNames("Spring Core","IOC container","include");
        gKnowledgeService.addRelationByNames("Spring Boot","Vue","associated");
        gKnowledgeService.addRelationByNames("Spring Boot","Spring Service","associated");
        gKnowledgeService.addRelationByNames("Spring Boot","Spring Controller","include");
        gKnowledgeService.addRelationByNames("Spring Boot","Spring Initialier","preKnowledge");
        gKnowledgeService.addRelationByNames("Spring Boot","Tomcat","associated");
        gKnowledgeService.addRelationByNames("Spring Boot","Jetty","associated");
        gKnowledgeService.addRelationByNames("Spring Boot","http/https","preKnowledge");
        gKnowledgeService.addRelationByNames("Vue","react","common");
        gKnowledgeService.addRelationByNames("Vue","HTML","preKnowledge");
        gKnowledgeService.addRelationByNames("Vue","JavaScript","preKnowledge");
        gKnowledgeService.addRelationByNames("Vue","CSS","preKnowledge");
        gKnowledgeService.addRelationByNames("Spring Controller","Restful Api","include");
        gKnowledgeService.addRelationByNames("Restful Api","Web Api","preKnowledge");
        gKnowledgeService.addRelationByNames("Restful Api","HTTP Status code","preKnowledge");
        gKnowledgeService.addRelationByNames("Restful Api","Swagger","associated");
        gKnowledgeService.addRelationByNames("Restful Api","Postman","associated");
        gKnowledgeService.addRelationByNames("Postman","http/https","preKnowledge");
        gKnowledgeService.addRelationByNames("Spring Data","junit","associated");
        gKnowledgeService.addRelationByNames("Spring Data","Redis","include");
        gKnowledgeService.addRelationByNames("Spring Data","JDBC","include");
        gKnowledgeService.addRelationByNames("Spring Data","JPA","include");
        gKnowledgeService.addRelationByNames("Spring Data","MyBatis","associated");
        gKnowledgeService.addRelationByNames("Spring Data","NEO4j","include");
        gKnowledgeService.addRelationByNames("JDBC","SQL","preKnowledge");
        gKnowledgeService.addRelationByNames("JPA","SQL","preKnowledge");
        gKnowledgeService.addRelationByNames("JPA","ORM","include");
        gKnowledgeService.addRelationByNames("JPA","JPARepository","include");
        gKnowledgeService.addRelationByNames("MyBatis","MyBatis-Plus","associated");
        gKnowledgeService.addRelationByNames("MyBatis-Plus","MyBatis-Plus code-generation","include");
        gKnowledgeService.addRelationByNames("MyBatis-Plus","ORM","include");

    }

    @Test
    void initData() {
        gKnowledgeService.addTag(new Knowledge("c","c"));
    }
}
