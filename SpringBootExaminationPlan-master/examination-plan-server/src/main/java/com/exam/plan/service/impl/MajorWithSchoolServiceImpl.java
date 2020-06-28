package com.exam.plan.service.impl;

import com.exam.plan.entity.MajorWithSchool;
import com.exam.plan.service.IMajorWithSchoolService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MajorWithSchoolServiceImpl extends AbstractService<MajorWithSchool> implements IMajorWithSchoolService {
//  @Resource
//  public MajorWithSchoolMapper majorWithSchoolMapper;

//  @Override
//  public List<MajorWithSchool> listAllWithSchool(){
//    return this.majorWithSchoolMapper.listAllWithSchool();
//  };
}
