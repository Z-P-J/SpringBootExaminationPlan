package com.exam.plan.service.impl;

import com.exam.plan.entity.MajorCourseInfo;
import com.exam.plan.entity.MajorDirection;
import com.exam.plan.service.IMajorCourseService;
import com.exam.plan.service.IMajorDirectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MajorDirectionServiceImpl extends AbstractService<MajorDirection> implements IMajorDirectionService {



}
