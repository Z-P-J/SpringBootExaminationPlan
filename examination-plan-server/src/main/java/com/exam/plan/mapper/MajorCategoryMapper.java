package com.exam.plan.mapper;

import com.exam.plan.entity.Major;
import com.exam.plan.entity.MajorCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MajorCategoryMapper{
//    @Select("SELECT * FROM major_categories,major_info where major_info.major_category_code=major_categories.major_category_code")
    List<MajorCategory> listAllWithCategory();
}
