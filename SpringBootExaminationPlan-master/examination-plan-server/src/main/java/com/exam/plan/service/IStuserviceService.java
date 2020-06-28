package com.exam.plan.service;

import com.exam.plan.entity.Stuservice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * XX
 * 2020/6/28
 */
public interface IStuserviceService {
    List<Stuservice> getstuservice(Map map);
}
