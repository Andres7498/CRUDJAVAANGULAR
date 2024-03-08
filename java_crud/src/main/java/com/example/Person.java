package com.example;

import java.sql.Date;

public class Person {

    private String name;
    private String idNum;
    private Date birthDate;
  
    public Person(String name, String idNum, Date birthDate) {
      this.name = name;
      this.idNum = idNum;
      this.birthDate = birthDate;
    }
  
    public String getName() {
      return name;
    }
  
    public String getIdNum() {
      return idNum;
    }
  
    public Date getBirthDate() {
      return birthDate;
    }
  
  
    public String toString() {
      return "Person{" +
              "name='" + name + '\'' +
              ", idNum='" + idNum + '\'' +
              ", birthDate=" + birthDate +
              '}';
    }
  }
  