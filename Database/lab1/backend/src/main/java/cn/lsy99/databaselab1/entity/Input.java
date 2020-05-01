package cn.lsy99.databaselab1.entity;

public class Input {
  private String id;
  private String name;
  private int[] age;
  private String gender;
  private String className;
  private String dept;
  private String addr;

  public Input(String id, String name, int[] age, String gender, String className, String dept, String addr) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.className = className;
    this.dept = dept;
    this.addr = addr;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int[] getAge() {
    return age;
  }

  public String getGender() {
    return gender;
  }

  public String getClassName() {
    return className;
  }

  public String getDept() {
    return dept;
  }

  public String getAddr() {
    return addr;
  }
}
