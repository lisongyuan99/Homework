package cn.lsy99.databaselab1.entity;

import java.util.List;

public class Output {
  private String sql;
  private List<Student> students;

  public Output(String sql, List<Student> students) {
    this.sql = sql;
    this.students = students;
  }

  public String getSql() {
    return sql;
  }

  public List<Student> getStudents() {
    return students;
  }
}