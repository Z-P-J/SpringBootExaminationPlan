package com.exam.plan.mapper;


import com.exam.plan.entity.AccountWithRole;
import com.exam.plan.entity.Approve;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApproveMapper extends MyMapper<Approve> {
    /**
     * 获取所有用户以及对应角色
     *
     * @return 用户列表
     */
    List<Approve> listAllWithApprove();

//    /**
//     * 按条件获取用户
//     *
//     * @param params 参数
//     * @return 用户列表
//     */
//    List<Approve> findWithRoleBy(final Map<String, Object> params);
}
