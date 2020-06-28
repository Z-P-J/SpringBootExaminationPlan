package com.exam.plan.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author Zoctan
 * @date 2018/06/09
 */
@Data
public class AccountDto {
  /** 用户Id */
  private Long id;

  /** 邮箱 */
  private String email;

  /** 用户名 */
  private String name;

  /** 密码 */
  @JSONField(serialize = false)
  private String password;

  /** 注册时间 */
  private Timestamp registerTime;

  /** 上一次登录时间 */
  private Timestamp loginTime;

  private Long roleId;
}
