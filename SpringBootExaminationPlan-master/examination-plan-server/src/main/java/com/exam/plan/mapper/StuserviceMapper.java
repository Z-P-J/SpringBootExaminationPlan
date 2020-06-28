package com.exam.plan.mapper;

import com.exam.plan.entity.SchoolInfo;
import com.exam.plan.entity.Stuservice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * XX
 * 2020/6/28
 */
@Mapper
public interface StuserviceMapper {
    List<Stuservice> getstuservice(@Param("content") Map map);
}
