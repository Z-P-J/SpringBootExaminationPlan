package com.exam.plan.mapper;

import com.exam.plan.entity.MajorPlanVersion;
import com.exam.plan.entity.CourseTextBookInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MajorplanMapper extends MyMapper<MajorPlanVersion> {
//  /**
//   * 获取所有角色以及对应的权限
//   *
//   * @return 角色可控资源列表
//   */
//  List<RoleWithResource> listRoles();

//  List<CourseTextBookInfo> listTextbook();

    /**
     * 按角色Id更新修改时间
     *
     * @param id 角色Id
     */
    void updateTimeById(@Param("id") Long id);

    void disableMajorplan(@Param("planVersionIdList") List<String> planVersionIdList);

    void enableMajorplan(@Param("planVersionIdList") List<String> planVersionIdList);

    void deleteMajorplan(@Param("planVersionIdList") List<String> planVersionIdList);

}
