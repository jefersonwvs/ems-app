package com.jefersonwvs.app.crud.beans;

public class Employee {

  private Integer id;
  private String name;
  private String email;
  private String phone;
  private String address;

  public Employee() {}

  public Employee(String name, String email, String phone, String address) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
  }

  public Employee(int id, String name, String email, String phone, String address) {
    this(name, email, phone, address);
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Employee{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", email='"
        + email
        + '\''
        + ", phone='"
        + phone
        + '\''
        + ", address='"
        + address
        + '\''
        + '}';
  }
}
