package com.exam.plan.service.impl;

import com.exam.plan.entity.MajorWithCategory;
import com.exam.plan.mapper.MajorCategoryMapper;
import com.exam.plan.mapper.MajorWithCategoryMapper;
import com.exam.plan.service.IMajorCategoryService;
import com.exam.plan.service.IMajorWithCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MajorWithCategoryServiceImpl extends AbstractService<MajorWithCategory> implements IMajorWithCategoryService {
//  @Resource
//  public MajorWithCategoryMapper majorWithCategoryMapper;

//  @Override
//  public List<MajorWithCategory> listAllWithCategory(){
//    return this.majorWithCategoryMapper.listAllWithCategory();
//  };
}
