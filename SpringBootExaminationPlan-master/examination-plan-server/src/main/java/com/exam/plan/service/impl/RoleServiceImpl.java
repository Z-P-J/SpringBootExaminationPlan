package com.exam.plan.service.impl;

import com.exam.plan.entity.Role;
import com.exam.plan.entity.RolePermission;
import com.exam.plan.entity.RoleWithPermission;
import com.exam.plan.entity.RoleWithResource;
import com.exam.plan.mapper.PermissionMapper;
import com.exam.plan.mapper.RoleMapper;
import com.exam.plan.mapper.RolePermissionMapper;
import com.exam.plan.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends AbstractService<Role> implements IRoleService {
  @Resource
  private RoleMapper roleMapper;
  @Resource
  private PermissionMapper permissionMapper;
  @Resource
  private RolePermissionMapper rolePermissionMapper;

  @Override
  public List<RoleWithResource> listRoleWithPermission() {
    // 由于mybatis在嵌套查询时和pagehelper有冲突
    // 暂时用for循环代替
    final List<RoleWithResource> roles = this.roleMapper.listRoles();
    roles.forEach(
        role -> {
          final List<com.exam.plan.entity.Resource> resources =
              this.permissionMapper.listRoleWithResourceByRoleId(role.getId());
          role.setResourceList(resources);
        });
    return roles;
  }

  @Override
  public void save(final RoleWithPermission role) {
    this.roleMapper.insert(role);
    this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
  }

  @Override
  public void update(final RoleWithPermission role) {
    // 删掉所有权限，再添加回去
    final Condition condition = new Condition(RolePermission.class);
    condition.createCriteria().andCondition("role_id = ", role.getId());
    this.rolePermissionMapper.deleteByCondition(condition);
    this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
    this.roleMapper.updateTimeById(role.getId());
  }
}
