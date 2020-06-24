package com.exam.plan.mapper;

import com.exam.plan.entity.Major;
import com.exam.plan.entity.MajorSchool;
import com.exam.plan.entity.MajorWithCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorSchoolMapper extends MyMapper<MajorSchool>{
//    @Select("SELECT * FROM major_categories,major_info where major_info.major_category_code=major_categories.major_category_code")
//    List<MajorSchool> listAllWithCategory();
}
