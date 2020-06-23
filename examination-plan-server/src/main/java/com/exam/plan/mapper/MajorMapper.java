package com.exam.plan.mapper;

import com.exam.plan.entity.CourseInfo;
import com.exam.plan.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MajorMapper extends MyMapper<Major> {

}
