package com.exam.plan.service.impl;

import com.exam.plan.entity.MajorWithSchool;
import com.exam.plan.entity.SchoolWithMajor;
import com.exam.plan.mapper.SchoolWithMajorMapper;
import com.exam.plan.service.IMajorWithSchoolService;
import com.exam.plan.service.ISchoolWithMajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class SchoolWithMajorServiceImpl extends AbstractService<SchoolWithMajor> implements ISchoolWithMajorService {
  @Resource
  public SchoolWithMajorMapper schoolWithMajorMapper;

  @Override
  public List<SchoolWithMajor> listAllWithMajor(String id){
    return this.schoolWithMajorMapper.listAllWithMajor(id);
  };

  @Override
  public List<SchoolWithMajor> listAllWithExtendMajor(String id){
    return this.schoolWithMajorMapper.listAllWithExtendMajor(id);
  };
}
