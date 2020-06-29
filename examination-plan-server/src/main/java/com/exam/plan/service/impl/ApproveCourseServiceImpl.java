package com.exam.plan.service.impl;

import com.exam.plan.entity.ApproveCourse;
import com.exam.plan.mapper.ApproveCourseMapper;
import com.exam.plan.service.IApproveCourseService;
import com.exam.plan.service.IApproveMajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class ApproveCourseServiceImpl extends AbstractService<ApproveCourse> implements IApproveCourseService {
  @Resource
  public ApproveCourseMapper approveCourseMapper;

  @Override
  public void save(ApproveCourse entity) {
    this.approveCourseMapper.insert(entity);
  }

}
