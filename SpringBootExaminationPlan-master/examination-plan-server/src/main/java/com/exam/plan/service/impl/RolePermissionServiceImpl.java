package com.exam.plan.service.impl;

import com.exam.plan.entity.RolePermission;
import com.exam.plan.mapper.RolePermissionMapper;
import com.exam.plan.service.IRolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/06/09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RolePermissionServiceImpl extends AbstractService<RolePermission>
    implements IRolePermissionService {
  @Resource
  private RolePermissionMapper rolePermissionMapper;
}
