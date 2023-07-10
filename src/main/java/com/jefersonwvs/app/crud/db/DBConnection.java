package com.jefersonwvs.app.crud.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

  private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_db";
  private static final String DB_USER = "root";
  private static final String DB_PASSWORD = "root";

  private static Connection connection = null;

  public static Connection getConnection() {
    System.out.println("[APP LOG] Starting DB connection...");
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
      if (connection != null) {
        System.out.println("[APP LOG] Connected to the DB!");
        return connection;
      } else {
        System.out.println("[APP LOG] Problem connecting to the database!");
        return null;
      }
    } catch (Exception e) {
      System.out.println("[APP LOG] Exception when connecting to DB: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }

  // "Test"
  public static void main(String[] args) {
    System.out.println(DBConnection.getConnection());
  }
}
