package com.exam.plan.service.impl;

import com.exam.plan.entity.MajorCategory;
import com.exam.plan.entity.MajorNational;
import com.exam.plan.service.IMajorCategoryService;
import com.exam.plan.service.IMajorNationalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MajorNationalServiceImpl extends AbstractService<MajorNational> implements IMajorNationalService {

}
