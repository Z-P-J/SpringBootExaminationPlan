package com.exam.plan.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleWithResource extends Role {
  /** 角色对应的权限 */
  @Transient private List<Resource> resourceList;
}
