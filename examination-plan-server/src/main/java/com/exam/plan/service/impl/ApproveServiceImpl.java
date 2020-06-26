package com.exam.plan.service.impl;

import com.exam.plan.entity.*;
import com.exam.plan.exception.ServiceException;
import com.exam.plan.mapper.ApproveMapper;
import com.exam.plan.service.IApproveService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ApproveServiceImpl extends AbstractService<Approve> implements IApproveService {
  @Resource
  private ApproveMapper ApproveMapper;

  @Override
  public List<Approve> listAllWithApprove() {
    return this.ApproveMapper.listAllWithApprove();
  }

  /** 增加申请表 */
  @Override
  public void save(final Approve tmpApprove) {
        final Approve approve = new Approve();
//        approve.setApprove_id(tmpApprove.approve_id);
        this.ApproveMapper.insertSelective(approve);
        System.out.println("Approve=" + approve);

  }
    public void saveCheck(final Approve tmpApprove){
        final Approve approve = new Approve();
//        approve.setApprove_id(tmpApprove.approve_id);
        this.ApproveMapper.insertSelective(approve);
        System.out.println("Approve=" + approve);
    }
}
