package com.exam.plan.service.impl;

import com.exam.plan.entity.ExamCourseInfo;
import com.exam.plan.entity.ExamPlanApproveInfo;
import com.exam.plan.service.IExamCourseInfoService;
import com.exam.plan.service.IExamPlanApproveInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExamPlanApproveInfoServiceImpl extends AbstractService<ExamPlanApproveInfo> implements IExamPlanApproveInfoService {

}
