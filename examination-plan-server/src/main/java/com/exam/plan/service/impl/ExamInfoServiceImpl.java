package com.exam.plan.service.impl;

import com.exam.plan.entity.ExamInfo;
import com.exam.plan.entity.MajorCategory;
import com.exam.plan.service.IExamInfoService;
import com.exam.plan.service.IMajorCategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExamInfoServiceImpl extends AbstractService<ExamInfo> implements IExamInfoService {

}
