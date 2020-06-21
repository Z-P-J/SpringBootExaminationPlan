package com.exam.plan.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

public class Account {
  /** 用户Id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** 邮箱 */
  private String email;

  /** 用户名 */
  private String name;

  /** 密码 */
  private String password;

  /** 注册时间 */
  private Timestamp registerTime;

  /** 上一次登录时间 */
  private Timestamp loginTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Timestamp getRegisterTime() {
    return registerTime;
  }

  public void setRegisterTime(Timestamp registerTime) {
    this.registerTime = registerTime;
  }

  public Timestamp getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Timestamp loginTime) {
    this.loginTime = loginTime;
  }

  @Override
  public String toString() {
    return "Account{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", registerTime=" + registerTime +
            ", loginTime=" + loginTime +
            '}';
  }
}
