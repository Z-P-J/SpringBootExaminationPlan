package com.exam.plan.service.impl;

import com.exam.plan.entity.ExamCourse;
import com.exam.plan.entity.ExamInfo;
import com.exam.plan.service.IExamCourseService;
import com.exam.plan.service.IExamInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class ExamCourseServiceImpl extends AbstractService<ExamCourse> implements IExamCourseService {

}
