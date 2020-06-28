package com.exam.plan.service.impl;

import com.exam.plan.entity.CourseInfo;
import com.exam.plan.entity.Major;
import com.exam.plan.entity.RolePermission;
import com.exam.plan.entity.RoleWithPermission;
import com.exam.plan.mapper.CourseMapper;
import com.exam.plan.mapper.MajorMapper;
import com.exam.plan.service.ICourseService;
import com.exam.plan.service.IMajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class MajorServiceImpl extends AbstractService<Major> implements IMajorService {
  @Resource
  private MajorMapper majorMapper;

  @Override
  public void save(Major entity) {
    this.majorMapper.insert(entity);
  }
}
