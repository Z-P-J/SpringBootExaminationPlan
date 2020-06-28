package com.exam.plan.service.impl;

import com.exam.plan.entity.Stuservice;
import com.exam.plan.mapper.StuserviceMapper;
import com.exam.plan.service.IStuserviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * XX
 * 2020/6/28
 */
@Service
public class StuserviceImpl implements IStuserviceService {
    @Autowired
    StuserviceMapper stuserviceMapper;
    @Override
    public List<Stuservice> getstuservice(Map map) {
        /*
        加入操作
         */
        return stuserviceMapper.getstuservice(map);
    }
}
