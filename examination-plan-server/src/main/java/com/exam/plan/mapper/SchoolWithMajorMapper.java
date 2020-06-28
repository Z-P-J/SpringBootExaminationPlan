package com.exam.plan.mapper;

import com.exam.plan.entity.MajorWithSchool;
import com.exam.plan.entity.SchoolWithMajor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SchoolWithMajorMapper extends MyMapper<SchoolWithMajor>{
    List<SchoolWithMajor> listAllWithMajor(String id);
    List<SchoolWithMajor> listAllWithExtendMajor(String id);
}
