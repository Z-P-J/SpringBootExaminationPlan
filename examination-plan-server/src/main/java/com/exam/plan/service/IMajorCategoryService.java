package com.exam.plan.service;

import com.exam.plan.entity.Major;
import com.exam.plan.entity.MajorCategory;

import java.util.List;

public interface IMajorCategoryService{

    List<MajorCategory> listAllWithCategory();
}
