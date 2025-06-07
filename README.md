学生管理系统 (Student Management System)
项目概述
    本项目基于 Java 的教育管理工具，旨在高效管理学生信息、用户账户和数据备份。项目采用 JDK 9 开发，结合 Java 集合框架（List）实现灵活的数据处理，融入多线程技术，通过封装的 JDBC 层和 Apache Commons 工具库实现与 MySQL 数据库的安全、可靠交互。

文件夹结构
工作区默认包含两个文件夹，其中：
src：用于维护源代码的文件夹，util用于封装JDBC。
lib：用于维护依赖项的文件夹。
db.properties：用于封装Mysql配置文件
date.csv：数据备份于恢复存储文件


核心功能
1. 用户账户管理
    注册：支持新用户（管理员、教师、学生）注册，密码使用 bcrypt 加密存储，确保安全性。
    登录：基于用户名和密码的身份验证。


2. 学生信息管理
    CRUD 操作：支持学生基本信息（学号、姓名、性别等）的创建、读取、更新和删除。
    高效数据处理：基于 Java 集合框架（List）和 Apache Commons 工具库实现学生数据的内存管理和操作，优化数据管理和查询。
   并发支持：通过多线程技术（Thread/ExecutorService），确保高并发场景下的流畅操作。

3. 数据备份与恢复
   结合 Apache Commons DBUtils 简化 JDBC 操作，提高备份/恢复效率。


技术栈

编程语言：Java (JDK 9)
数据库：MySQL 5.7.29
数据访问：JDBC（封装为模块化 DAO 层），Apache Commons DBUtils：优化 JDBC 查询和结果集处理。
数据结构：Java 集合框架（List），用于高效内存数据管理，Apache Commons Collections：提供增强的集合操作功能。
并发处理：Java 多线程技术（Thread/ExecutorService）
依赖管理：
MySQL Connector/J：JDBC 驱动，用于数据库连接
Apache Commons DBUtils（数据库操作工具）
Junit（单元模块测试，确保项目功能稳定）


开发工具：IntelliJ IDEA 2021.1
操作系统：跨平台支持（Windows、Linux、macOS）


项目亮点

模块化设计：通过 DAO 模式和 Apache Commons DBUtils 封装 JDBC实现代码的高内聚低耦合，易于维护和扩展。
高并发支持：多线程架构优化了数据操作性能，适合多用户同时访问。
安全性：采用使用 PreparedStatement 防止 SQL 注入。
数据处理效率：Apache Commons Collections 提供强大的 List 操作功能，提升内存数据处理性能。
数据可靠性：完善的备份与恢复机制，确保数据不丢失。


快速开始
前提条件
    Java 环境：JDK 9 或以上版本（推荐 Oracle JDK 或 OpenJDK）。
MySQL：MySQL 5.7.29 或以上版本，需运行并配置好。
    依赖项：
MySQL Connector/J（位于 lib/mysql-connector-java-5.1.6jar）
Apache Commons


安装步骤
克隆项目：
git clone https://github.com/Frankf-ux/student-management-system.git
cd student-management-system


配置 MySQL 数据库：
    确保数据库名 student_db 与你的 db.properties 文件一致。

配置 JDBC 连接：
    修改 src/config/db.properties 文件，设置数据库连接参数：
url =jdbc:mysql://localhost:3306/student_db?characterEncoding=utf8
user=your_username
password = your_password
driver = com.mysql.jdbc.Driver


添加依赖：
    确保 lib/ 文件夹包含以下 JAR 文件：
mysql-connector-java-8.0.33.jar
commons-io-2.4.jar


项目结构
student-management-system/
├── src/
│   ├── com.google/
│   │   ├── main/
│   │   │   └── RunApplication.java    # 程序入口类，负责启动应用
│   │   ├── model/
│   │   │   └── Student.java           # 学生实体类，定义学生信息结构
│   │   ├── service/
│   │   │   ├── FunctionService.java   # 通用功能服务类，处理核心业务逻辑
│   │   │   └── StudentService.java    # 学生服务类，管理学生信息 CRUD 操作
│   │   ├── util/
│   │   │   └── JDBCUtils.java         # JDBC 工具类，封装数据库连接和操作
│   └── db.properties                  # 数据库配置文件，存储连接参数
│   └── date.csv                       # 数据文件，可能用于导入或导出数据
├── lib/
│   ├── mysql-connector-java-8.0.33.jar  # MySQL JDBC 驱动
│   ├── jbcrypt-0.4.jar                  # 密码加密库
│   ├── commons-dbutils-1.7.jar          # Apache Commons DBUtils
│   └── commons-collections4-4.4.jar     # Apache Commons Collections
├── sql/
│   └── schema.sql                       # 数据库表结构和初始化脚本
├── README.md                            # 项目说明文档
└── .gitignore                           # Git 忽略文件

说明：如果这里你想编写界面的，实际上，也一样，你要在外界创建一个图形类，然后在这里初始化图形类即可。

本项目采用 MIT License，允许自由使用、修改和分发。

联系方式
作者：Frank-ux
邮箱：lylwssl0911@gmail.com
GitHub：https://github.com/Frank-ux
Issues：如有问题，请在 GitHub 提交 Issue。

