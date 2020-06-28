package com.exam.plan.service.impl;

import com.exam.plan.entity.Approve;
import com.exam.plan.entity.ApproveView;
import com.exam.plan.mapper.ApproveMapper;
import com.exam.plan.service.IApproveService;
import com.exam.plan.service.IApproveViewService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ApproveViewServiceImpl extends AbstractService<ApproveView> implements IApproveViewService {
}
