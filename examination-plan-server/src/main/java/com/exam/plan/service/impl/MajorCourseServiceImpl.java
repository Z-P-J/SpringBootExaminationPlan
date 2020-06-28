package com.exam.plan.service.impl;

import com.exam.plan.entity.*;
import com.exam.plan.exception.ResourcesNotFoundException;
import com.exam.plan.mapper.*;
import com.exam.plan.service.ICourseService;
import com.exam.plan.service.IMajorCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MajorCourseServiceImpl extends AbstractService<MajorCourseInfo> implements IMajorCourseService {



}
