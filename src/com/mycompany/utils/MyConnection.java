///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.utils;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
///**
// *
// * @author HP
// */
//public class MyConnection {
//    
//    public static String url = "jdbc:mysql://localhost:3306/world-aid";
//    public static String login = "root";
//    public static String pwd = "";
//    public static MyConnection instance;
//    public Connection cnx ; 
//
//    private MyConnection(){
//                try {
//             cnx = DriverManager.getConnection(url, login, pwd);
//            System.out.println("Connection ettablie!");
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//    public static MyConnection getInstance(){
//        if (instance ==null){
//            instance =new MyConnection ();
//        }
//        return instance;
//        
//    }
//
//    public Connection getCnx() {
//        return cnx;
//    }
//
//    Connection getConnection() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    
//}
