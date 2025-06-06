package com.google.service;

import java.util.Scanner;


public class FunctionService {

    private static Scanner scanner = new Scanner(System.in);
    // 功能主模块
    public static void functionService() {
        Boolean choice = true;

        while (choice) {

            switch (scanner.next()) {
                case "1":
                    StudentService.addStudent();
                    break;
                case "2":
                    StudentService.deleteStudent();
                    break;
                case "3":
                    StudentService.updateName();
                    break;
                case "4":
                    StudentService.updateAll();
                    break;
                case "5":
                    StudentService.selectById();
                    break;
                case "6":
                    StudentService.selectAll();
                    break;
                case "0":
                    choice = false;
                    System.out.println("已退出登录界面！");
                    System.out.println("----------Welcome----------\n" + "输入你的操作选项：注册（r） | 登录（l） | 退出（q）");
                    break;
                default:
                    System.out.println("无效的选项，请重新输入！");
            }
        }
    }

    public static void dateFunctionService() {
        System.out.println("----------Welcome----------\n" + "输入你的操作选项: 数据备份（1） | 数据恢复（2） | 退出（0）");
        Boolean choice = true;
        while (choice) {
            switch (scanner.next()) {
                case "1":
                    StudentService.backupStudent();
                    break;
                case "2":
                    StudentService.restoreStudent();
                    break;
                case "q":
                    choice = false;
                    System.out.println("已退出！");
                    System.out.println("----------Welcome----------\n" + "输入你的操作选项：注册（r） | 登录（l） | 数据备份或恢复（t) | 退出（0）");
                    break;
                default:
                    System.out.println("无效的选项，请重新输入！");
            }
        }
    }


}
