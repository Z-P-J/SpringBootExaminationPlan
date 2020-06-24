package com.exam.plan.mapper;

import com.exam.plan.entity.Major;
import com.exam.plan.entity.MajorCategory;
import com.exam.plan.entity.MajorWithCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MajorCategoryMapper extends MyMapper<MajorCategory>{


}
