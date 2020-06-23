package com.exam.plan.service.impl;

import com.exam.plan.entity.CourseInfo;
import com.exam.plan.entity.RolePermission;
import com.exam.plan.entity.RoleWithPermission;
import com.exam.plan.mapper.CourseMapper;
import com.exam.plan.service.ICourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class CourseServiceImpl extends AbstractService<CourseInfo> implements ICourseService {
  @Resource
  private CourseMapper courseMapper;

//  @Override
//  public List<RoleWithResource> listRoleWithPermission() {
//    // 由于mybatis在嵌套查询时和pagehelper有冲突
//    // 暂时用for循环代替
//    final List<RoleWithResource> roles = this.courseMapper.listRoles();
//
//    return roles;
//  }

  @Override
  public void save(CourseInfo entity) {

    this.courseMapper.insert(entity);
  }

//  @Override
//  public void save(final RoleWithPermission role) {
//    this.courseMapper.insert(role);
////    this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
//  }

  @Override
  public void update(final RoleWithPermission role) {
    // 删掉所有权限，再添加回去
    final Condition condition = new Condition(RolePermission.class);
    condition.createCriteria().andCondition("role_id = ", role.getId());
//    this.rolePermissionMapper.deleteByCondition(condition);
//    this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
    this.courseMapper.updateTimeById(role.getId());
  }
}
