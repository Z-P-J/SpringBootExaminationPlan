package com.exam.plan.service.impl;

import com.exam.plan.entity.ExamMajorInfo;
import com.exam.plan.entity.ExamPlanInfo;
import com.exam.plan.service.IExamMajorService;
import com.exam.plan.service.IExamPlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class ExamMajorServiceImpl extends AbstractService<ExamMajorInfo> implements IExamMajorService {



}
