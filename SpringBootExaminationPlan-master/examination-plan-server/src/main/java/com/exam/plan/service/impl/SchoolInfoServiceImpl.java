package com.exam.plan.service.impl;

import com.exam.plan.entity.ExamInfo;
import com.exam.plan.entity.SchoolInfo;
import com.exam.plan.service.IExamInfoService;
import com.exam.plan.service.ISchoolInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SchoolInfoServiceImpl extends AbstractService<SchoolInfo> implements ISchoolInfoService {

}
