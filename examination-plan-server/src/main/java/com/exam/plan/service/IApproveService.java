package com.exam.plan.service;


import com.exam.plan.entity.Approve;


import java.util.List;
import java.util.Map;

public interface IApproveService extends Service<Approve> {
    /**
     * 保存用户
     *
     * @param approveDto 用户
     */
    void save(Approve approveDto);
    /**
     * 获取所有用户以及对应角色
     *
     * @return 用户列表
     */
    List<Approve> listAllWithApprove();

}
