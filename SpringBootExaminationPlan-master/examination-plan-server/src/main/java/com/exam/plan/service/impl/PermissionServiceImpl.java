package com.exam.plan.service.impl;

import com.exam.plan.mapper.PermissionMapper;
import com.exam.plan.entity.Permission;
import com.exam.plan.service.IPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/06/09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl extends AbstractService<Permission>
    implements IPermissionService {
  @Resource
  private PermissionMapper permissionMapper;

  @Override
  public List<com.exam.plan.entity.Resource> listResourceWithHandle() {
//    return new ArrayList<>();
    return this.permissionMapper.listResourceWithHandle();
  }

  @Override
  public List<com.exam.plan.entity.Resource> listRoleWithResourceByRoleId(Long roleId) {
//    return new ArrayList<>();
    return this.permissionMapper.listRoleWithResourceByRoleId(roleId);
  }
}
