package com.google.service;

import com.google.model.Student;
import com.google.util.JDBCUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Scanner scanner = new Scanner(System.in);
    private static File file = new File("E:\\Java\\Project\\smyfinal\\date.csv");

    // 0.用户注册（加密并存储密码）模块
    public static void registerUser() {
        System.out.println("----------请按提示依次输入相关信息进行注册----------\n" + "请输入你的用户名：");

        String userName = scanner.next();
        System.out.println("请输入你的密码（number）：");
        int password = scanner.nextInt();

        try {
            // 使用PreparedStatement防止SQL注入
            connection = JDBCUtils.getConnection();
            String sql = "insert into registeruser(userName, password) values(?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, password);

            int resultSet = preparedStatement.executeUpdate();
            if (resultSet != 0) {
                System.out.println("user register success!");

            }
            System.out.println("------------Welcome to the interface------------\n" + "输入你的操作选项：注册新用户（r） | 登录（l） | 退出（q）");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 1.登录验证（验证用户名和密码）模块
    public static void loginUser() {
        System.out.println("----------欢迎登录，请依次输入你的用户名和密码:----------");

        while (true) {
            System.out.print("用户名: ");
            String userName = scanner.next();
            System.out.print("密码: ");
            String passWord = scanner.next();
            try {
                connection = JDBCUtils.getConnection();

                // 只查找与输入匹配的用户记录，提高效率
                String sql = "select * from registeruser where username = ? and password = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, userName);
                preparedStatement.setString(2, passWord);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println("User verification is successful, welcome to log in!");
                    break; // 登录成功，退出循环

                } else {
                    System.out.println("Wrong username or password, please try again!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    JDBCUtils.close(connection, preparedStatement);
                    // 不关闭 scanner，因为还在循环中用
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 0.printMenu模块，如果这里你想编写界面的，实际上，也一样，你要在外界创建一个图形类，然后在这里初始化图形类即可。
    public static void init() throws Throwable {
        System.out.println("------------Welcome Student Management System------------");
        Thread.sleep(500);
        System.out.println("1.添加学生。\n" + "2.删除学生。\n" + "3.更改学生部分信息。\n" + "4.更改学生信息。\n" + "5.查询学生信息。\n" + "6.显示所有学生信息。\n" + "0.退出系统！");
        Thread.sleep(500);

        System.out.println("输入操作编号（0-6），以开始操作：");

    }

    // 1.添加学生信息模块
    public static void addStudent() {
        System.out.println("------------1.请添加学生信息：------------");

        try {
            // 获取数据库连接和操作对象
            connection = JDBCUtils.getConnection();
            // sql插入语句
            String sql = "insert into info(id, name, age) values(?, ?, ?)";

            System.out.println("请输入学生学号（id），回车结束输入:");
            int id = scanner.nextInt();
            System.out.println("请输入学生姓名（name），回车结束输入:");
            String name = scanner.next();
            System.out.println("请输入学生年龄（age），回车结束输入：");
            int age = scanner.nextInt();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);

            int resultSet = preparedStatement.executeUpdate();
            // 判断是否插入成功
            if (resultSet > 0) {
                System.out.println("insert info success!");
            } else {
                System.out.println("Failed to insert information!");
            }
            System.out.println("请输入下一步的操作编号（0-6）：");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                JDBCUtils.close(connection, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 2.删除学生信息模块
    public static void deleteStudent() {
        System.out.println("------------2.删除学生信息：------------\n" + "请输入学生学号（id）以删除学生信息：");

        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from info where id=?";
            int id = scanner.nextInt();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int resultSet = preparedStatement.executeUpdate();

            if (resultSet > 0) {
                System.out.println("delete info success!");
            } else {
                System.out.println("未找到该学生！");
            }
            System.out.println("请输入下一步的操作编号（0-6）：");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
                //scanner.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 3.更改学生部分信息模块
    public static void updateName() {
        System.out.println("----------更改学生部分信息----------\n" + "请输入要更改学生的学号（id），回车结束输入:");

        try {
            connection = JDBCUtils.getConnection();
            int id = scanner.nextInt();

            System.out.println("请输入新的姓名（name），回车结束输入:");
            String name = scanner.next();

            String sql = "update info set name=? where id=?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("Information updated successfully!");
            } else {
                System.out.println("Information update failed! Check if the student ID is entered correctly.");
            }
            System.out.println("请输入下一步的操作编号（0-6）：");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 4.更改学生信息模块
    public static void updateAll() {
        System.out.println("----------更改学生信息----------\n" + "请输入要更改学生的学号（id），回车结束输入:");

        try {
            connection = JDBCUtils.getConnection();
            int id = scanner.nextInt();

            System.out.println("请输入新的姓名（name），回车结束输入:");
            String name = scanner.next();
            System.out.println("请输入新的年龄（age），回车结束输入:");
            int age = scanner.nextInt();

            String sql = "update info set name=?,age=? where id=?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, id);

            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                System.out.println("Information updated successfully!");
            } else {
                System.out.println("Information update failed! Check if the student ID is entered correctly.");
            }
            System.out.println("请输入下一步的操作编号（0-6）：");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 5.查询学生信息模块
    public static void selectById() {
        System.out.println("----------查询学生信息----------\n" + "请输入要查询学生信息的id:");
        int key = scanner.nextInt();

        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from info where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, key);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                System.out.println("id:" + id + ", name:" + name + ", age:" + age);
            }
            if (resultSet != null) {
                System.out.println("select success!");
            }
            System.out.println("请输入下一步的操作编号（0-6）：");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 6.显示所有学生信息
    public static void selectAll() {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from info";
            preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                int age = resultSet.getInt(3);
                System.out.println("id:" + id + ", name:" + name + ", age:" + age);
            }
            System.out.println("查询完成！");
            System.out.println("请输入下一步的操作编号（0-6）：");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 数据备份
    public static void backupStudent() {
        ArrayList<Student> arrayList = new ArrayList<>();

        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from info";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                arrayList.add(new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
            // 序列到文件
            try {
                // 使用Apache Common IO jar包写入文件

                if (!file.exists()) {
                    file.createNewFile();
                }
                FileUtils.writeLines(file, arrayList, true);
                System.out.println("备份成功，文件保存至：" + file);

            } catch (IOException e) {
                System.err.println("备份文件失败" + e.getMessage());
            }
            System.out.println("请输入下一步的操作编号（0-2）：");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 数据恢复
    public static void restoreStudent(){

        try {
            // 使用 Apache Commons IO 读取文件
            List<String> lines = FileUtils.readLines(file, "UTF-8");

            // 获取数据库连接
            connection = JDBCUtils.getConnection();
            String insertSQL = "INSERT INTO info (id, name, age) VALUES (?, ?, ?)";
            String truncateSQL = "TRUNCATE TABLE info";
            preparedStatement = connection.prepareStatement(truncateSQL);
            preparedStatement.executeUpdate(); // 清空表，避免主键冲突

            // 准备插入语句
            preparedStatement = connection.prepareStatement(insertSQL);
            List<Student> students = new ArrayList<>();

            // 解析文件内容
            for (String line : lines) {
                try {
                    String[] data = line.split(",", -1); // -1 保留空字段
                    if (data.length != 3) {
                        System.err.println("跳过无效行：" + line);
                        continue;
                    }
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].replace("\\,", ","); // 还原转义逗号
                    int age = Integer.parseInt(data[2].trim());
                    students.add(new Student(id, name, age));
                } catch (NumberFormatException e) {
                    System.err.println("解析行失败：" + line + "，错误：" + e.getMessage());
                }
            }

            // 批量插入数据库
            for (Student student : students) {
                try {
                    preparedStatement.setInt(1, student.getId());
                    preparedStatement.setString(2, student.getName());
                    preparedStatement.setInt(3, student.getAge());
                    preparedStatement.addBatch();
                } catch (SQLException e) {
                    System.err.println("准备插入学生失败：" + student + "，错误：" + e.getMessage());
                }
            }
            preparedStatement.executeBatch();
            System.out.println("恢复成功，共恢复 " + students.size() + " 条记录");
            System.out.println("请输入下一步的操作编号（0-2）：");


        } catch (IOException e) {
            System.err.println("读取备份文件失败：" + file + "，错误：" + e.getMessage());
        } catch (SQLException e) {
            System.err.println("数据库操作失败：" + e.getMessage());
        } finally {
            try {
                JDBCUtils.close(connection, preparedStatement);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

