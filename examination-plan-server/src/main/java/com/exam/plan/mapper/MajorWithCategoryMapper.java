package com.exam.plan.mapper;

import com.exam.plan.entity.MajorWithCategory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MajorWithCategoryMapper {
//    @Select("SELECT * FROM major_categories,major_info where major_info.major_category_code=major_categories.major_category_code")
    List<MajorWithCategory> listAllWithCategory();
}
