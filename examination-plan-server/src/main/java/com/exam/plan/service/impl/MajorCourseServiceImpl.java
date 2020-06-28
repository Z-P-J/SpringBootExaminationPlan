package com.exam.plan.service.impl;

import com.exam.plan.entity.*;
import com.exam.plan.exception.ResourcesNotFoundException;
import com.exam.plan.mapper.CourseChargeMapper;
import com.exam.plan.mapper.CourseMapper;
import com.exam.plan.mapper.CourseNationalMapper;
import com.exam.plan.mapper.CourseTextbookMapper;
import com.exam.plan.service.ICourseService;
import com.exam.plan.service.IMajorCourseService;
import com.exam.plan.service.IMajorplanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MajorCourseServiceImpl extends AbstractService<ProCourseInfo> implements IMajorCourseService {
    @Resource
    private CourseMapper courseMapper;
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
//    final List<RoleWithResource> roles = this.courseMapper.listRoles();
//
//    return roles;
//  }

//  @Override
//  public void save(CourseInfo entity) {
//
//    this.courseMapper.insert(entity);
//  }

//  @Override
//  public void save(final RoleWithPermission role) {
//    this.courseMapper.insert(role);
////    this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
//  }

    @Override
    public void update(final RoleWithPermission role) {
        // 删掉所有权限，再添加回去
        final Condition condition = new Condition(RolePermission.class);
        condition.createCriteria().andCondition("role_id = ", role.getId());
//    this.rolePermissionMapper.deleteByCondition(condition);
//    this.rolePermissionMapper.saveRolePermission(role.getId(), role.getPermissionIdList());
        this.courseMapper.updateTimeById(role.getId());
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
    public void deleteCourseCharge(Object id) {
        Optional.ofNullable(this.courseChargeMapper.selectByPrimaryKey(id))
                .orElseThrow(ResourcesNotFoundException::new);
        assertDelete(this.courseChargeMapper.deleteByPrimaryKey(id) == 1);
    }

    @Override
    public void updateCourseCharge(CourseCharge info) {
        assertUpdate(this.courseChargeMapper.updateByPrimaryKeySelective(info) == 1);
    }

    @Override
    public void saveCourseCharge(CourseCharge info) {
        assertSave(this.courseChargeMapper.insertSelective(info) == 1);
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
    public void disableCourse(List<String> courseIdList) {
        courseMapper.disableCourse(courseIdList);
    }

    @Override
    public void enableCourse(List<String> courseIdList) {
        courseMapper.enableCourse(courseIdList);
    }

    @Override
    public void deleteCourse(List<String> courseIdList) {
        courseMapper.deleteCourse(courseIdList);
    }

    @Override
    public List<CourseTextBookInfo> listTextbookByCourseId(String courseId) {
        return courseTextbookMapper.listTextbookByCourseId(courseId);
    }
}
