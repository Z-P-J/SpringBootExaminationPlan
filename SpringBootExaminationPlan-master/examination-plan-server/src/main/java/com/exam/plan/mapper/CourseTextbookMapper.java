package com.exam.plan.mapper;

import com.exam.plan.entity.CourseInfo;
import com.exam.plan.entity.CourseTextBookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseTextbookMapper extends MyMapper<CourseTextBookInfo> {

    List<CourseTextBookInfo> listTextbookByCourseId(@Param("id") String courseId);

}
