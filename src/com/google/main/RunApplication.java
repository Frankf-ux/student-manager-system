package com.google.main;

import com.google.service.FunctionService;
import com.google.service.StudentService;

import java.util.Scanner;

public class RunApplication {

    public static void main(String[] args) throws Throwable {
        loadInterface();

    }

    public static void loadInterface() throws Throwable {
        System.out.println("----------Welcome----------\n" + "输入你的操作选项：注册（r） | 登录（l） | 数据备份或恢复（t) | 退出（q）:");
        Scanner scanner = new Scanner(System.in);
        Boolean choice = true;

        while (choice) {
            switch (scanner.next()) {
                case "r":
                    StudentService.registerUser();
                    break;
                case "l":
                    StudentService.loginUser();
                    StudentService.init();
                    FunctionService.functionService();
                    break;
                case "q":
                    choice = false;
                    System.out.println("已退出程序！");
                    break;
                case "t":
                    System.out.println("请先登录账户！");
                    StudentService.loginUser();
                    FunctionService.dateFunctionService();
                default:
                    System.out.println("无效的选项，请重新输入！");
                    break;
            }
        }
        scanner.close();
    }

}
