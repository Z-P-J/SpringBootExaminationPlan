package com.exam.plan.service.impl;

import com.exam.plan.entity.Major;
import com.exam.plan.entity.MajorSchool;
import com.exam.plan.mapper.MajorMapper;
import com.exam.plan.mapper.MajorSchoolMapper;
import com.exam.plan.service.IMajorSchoolService;
import com.exam.plan.service.IMajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class MajorSchoolServiceImpl extends AbstractService<MajorSchool> implements IMajorSchoolService {
  @Resource
  private MajorSchoolMapper majorSchoolMapper;
  @Override
  public void save(MajorSchool entity) {
    this.majorSchoolMapper.insert(entity);
  }
}
