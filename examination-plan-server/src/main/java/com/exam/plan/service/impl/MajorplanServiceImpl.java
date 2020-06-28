package com.exam.plan.service.impl;

import com.exam.plan.entity.*;
import com.exam.plan.exception.ResourcesNotFoundException;
import com.exam.plan.mapper.CourseChargeMapper;
import com.exam.plan.mapper.MajorplanMapper;
import com.exam.plan.mapper.CourseNationalMapper;
import com.exam.plan.mapper.CourseTextbookMapper;
import com.exam.plan.service.IMajorplanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MajorplanServiceImpl extends AbstractService<MajorPlanVersion> implements IMajorplanService {
    @Resource
    private MajorplanMapper majorplanMapper;
    @Resource
    private CourseTextbookMapper courseTextbookMapper;
    @Resource
    private CourseChargeMapper courseChargeMapper;
    @Resource
    private CourseNationalMapper courseNationalMapper;

//  @Override
//  public List<RoleWithResource> listRoleWithPermission() {
//    // 由于mybatis在嵌套查询时和pagehelper有冲突
//    // 暂时用for循环代替
//    final List<RoleWithResource> roles = this.majorplanMapper.listRoles();
//
//    return roles;
//  }

//  @Override
//  public void save(MajorPlanVersion entity) {
//
//    this.majorplanMapper.insert(entity);
//  }

//  @Override
//  public void save(final RoleWithPermission role) {
//    this.majorplanMapper.insert(role);
////    this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
//  }

    @Override
    public void update(final RoleWithPermission role) {
//        // 删掉所有权限，再添加回去
//        final Condition condition = new Condition(RolePermission.class);
//        condition.createCriteria().andCondition("role_id = ", role.getId());
////    this.rolePermissionMapper.deleteByCondition(condition);
////    this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
//        this.majorplanMapper.updateTimeById(role.getId());
    }

    @Override
    public List<CourseTextBookInfo> listTextbook() {
        return courseTextbookMapper.selectAll();
    }

    @Override
    public CourseTextBookInfo getTextbookById(Object id) {
        return courseTextbookMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteTextbookById(Object id) {
        Optional.ofNullable(this.courseTextbookMapper.selectByPrimaryKey(id))
                .orElseThrow(ResourcesNotFoundException::new);
        assertDelete(this.courseTextbookMapper.deleteByPrimaryKey(id) == 1);
    }

    @Override
    public void updateTextbook(CourseTextBookInfo info) {
        assertUpdate(this.courseTextbookMapper.updateByPrimaryKeySelective(info) == 1);
    }

    @Override
    public void saveTextbook(CourseTextBookInfo info) {
        assertSave(this.courseTextbookMapper.insertSelective(info) == 1);
    }

    @Override
    public List<CourseCharge> listCharge() {
        return courseChargeMapper.selectAll();
    }

    @Override
    public CourseCharge getChargeById(Object id) {
        return courseChargeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CourseNational> listNationalCourse() {
        return courseNationalMapper.selectAll();
    }

    @Override
    public CourseNational getNationalCourseById(Object id) {
        return courseNationalMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteNationalCourse(Object id) {
        Optional.ofNullable(this.courseNationalMapper.selectByPrimaryKey(id))
                .orElseThrow(ResourcesNotFoundException::new);
        assertDelete(this.courseNationalMapper.deleteByPrimaryKey(id) == 1);
    }

    @Override
    public void updateNationalCourse(CourseNational info) {
        assertUpdate(this.courseNationalMapper.updateByPrimaryKeySelective(info) == 1);
    }

    @Override
    public void saveNationalCourse(CourseNational info) {
        assertSave(this.courseNationalMapper.insertSelective(info) == 1);
    }

    @Override
    public void disableMajorplan(List<String> planVersionIdList) {
        majorplanMapper.disableMajorplan(planVersionIdList);
    }

    @Override
    public void enableMajorplan(List<String> planVersionIdList) {
        majorplanMapper.enableMajorplan(planVersionIdList);
    }

    @Override
    public void deleteMajorplan(List<String> planVersionIdList) {
        majorplanMapper.deleteMajorplan(planVersionIdList);
    }
}
