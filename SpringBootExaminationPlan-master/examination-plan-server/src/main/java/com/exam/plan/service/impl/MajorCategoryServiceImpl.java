package com.exam.plan.service.impl;

import com.exam.plan.entity.Major;
import com.exam.plan.entity.MajorCategory;
import com.exam.plan.entity.MajorWithCategory;
import com.exam.plan.mapper.MajorCategoryMapper;
import com.exam.plan.mapper.MajorMapper;
import com.exam.plan.service.IMajorCategoryService;
import com.exam.plan.service.IMajorService;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MajorCategoryServiceImpl extends AbstractService<MajorCategory> implements IMajorCategoryService {

}
