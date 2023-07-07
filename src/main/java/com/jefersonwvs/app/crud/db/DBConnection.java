package com.jefersonwvs.app.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

  private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_db";
  private static final String DB_USER = "root";
  private static final String DB_PASSWORD = "root";

  private static Connection connection = null;

  public static Connection getConnection() {
    System.out.println("Start connection");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      if (connection != null) {
        System.out.println("Connected!");
        return connection;
      } else {
        System.out.println("Connection issue!");
        return null;
      }
    } catch (Exception e) {
      System.out.println("Exception in DB Connection: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  // "Test"
  public static void main(String[] args) {
    System.out.println(DBConnection.getConnection());
  }
}
