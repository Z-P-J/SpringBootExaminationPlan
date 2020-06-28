package com.exam.plan.service;


import com.exam.plan.entity.SchoolWithMajor;

import java.util.List;

public interface ISchoolWithMajorService extends Service<SchoolWithMajor>{

    List<SchoolWithMajor> listAllWithMajor(String id);
    List<SchoolWithMajor> listAllWithExtendMajor(String id);
}
