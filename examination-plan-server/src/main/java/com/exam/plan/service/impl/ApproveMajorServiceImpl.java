package com.exam.plan.service.impl;

import com.exam.plan.entity.Approve;
import com.exam.plan.entity.ApproveMajor;
import com.exam.plan.entity.Major;
import com.exam.plan.entity.SchoolWithMajor;
import com.exam.plan.mapper.ApproveMajorMapper;
import com.exam.plan.mapper.SchoolWithMajorMapper;
import com.exam.plan.service.IApproveMajorService;
import com.exam.plan.service.ISchoolWithMajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ApproveMajorServiceImpl extends AbstractService<ApproveMajor> implements IApproveMajorService {
  @Resource
  public ApproveMajorMapper approveMajorMapper;

  @Override
  public void save(ApproveMajor entity) {
    this.approveMajorMapper.insert(entity);
  }

}
