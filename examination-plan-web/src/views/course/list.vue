<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true">
        <el-form-item>
          <el-button
            type="success"
            size="mini"
            icon="el-icon-refresh"
            v-if="hasPermission('role:list')"
            @click.native.prevent="getCourseList"
          >刷新</el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
            @click.native.prevent="showAddDialog"
          >添加课程</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table
      :data="roleList"
      v-loading.body="listLoading"
      element-loading-text="loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="#" align="center" width="40">
        <template slot-scope="scope">
          <span v-text="getTableIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column label="课程编码" align="center" prop="courseId" />
      <el-table-column label="国家课程代码" align="center" prop="nationalCourseId" />
      <el-table-column label="课程名" align="center" prop="courseName" />
      <el-table-column label="课程说明" align="center" prop="courseSpecification" />
      <el-table-column label="课程性质" align="center" prop="courseProperty" />
      <el-table-column label="课程状态" align="center" prop="courseStatus">
        <template slot-scope="scope">{{ scope.row.courseStatus == '0' ? "正常" : "注销" }}</template>
      </el-table-column>
      <el-table-column
        label="管理"
        align="center"
      >
        <template slot-scope="scope">
        <el-button
            type="info"
            size="mini"
            @click.native.prevent="showDetail(scope.$index)"
          >查看</el-button>
          <el-button
            type="danger"
            size="mini"
            v-if="hasPermission('role:delete') && scope.row.name !== '超级管理员'"
            @click.native.prevent="removeCourse(scope.$index)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.page"
      :page-size="listQuery.size"
      :total="total"
      :page-sizes="[9, 18, 36, 72]"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>
    <course-detail-dialog v-model="courseDialog"></course-detail-dialog>
  </div>
</template>
<script>
import {
  listCourse,
  removeCourse
} from '@/api/course'
import { unix2CurrentTime } from '@/utils'
import { mapGetters } from 'vuex'
import CourseDetailDialog from './CourseDetailDialog'

export default {
  components: {
    CourseDetailDialog
  },
  created() {
    // if (this.hasPermission('role:update')) {
    //   this.getPermissionList()
    // }
    if (this.hasPermission('role:list')) {
      this.getCourseList()
    }
  },
  data() {
    /**
     * 验证角色名
     * @param rule 规则
     * @param value 角色名
     * @param callback 回调
     */
    const validateRoleName = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('角色名不能为空'))
      } else {
        callback()
      }
    }
    return {
      roleList: [],
      permissionList: [],
      listLoading: false,
      total: 0,
      listQuery: {
        page: 1,
        size: 9
      },
      dialogFormVisible: false,
      textMap: {
        update: '修改角色',
        add: '添加角色'
      },
      btnLoading: false,
      tempRole: {
        id: '',
        name: '',
        permissionIdList: []
      },
      tempCourse: {
        courseId: '',
        nationalCourseId: '',
        courseName: '',
        courseSpecification: '无',
        testSource: '全国命题',
        courseStatus: '0',
        credit: 3,
        qualifiedScore: 60,
        scoreScale: '100分制',
        subjectiveScore: 40,
        objectiveScore: 60,
        totalScore: 100,
        examDuration: 120,
        isProcedural: 0,
        courseProperty: '理论',
        notes: '无'
      },
      courseDialog: {
        data: {
          courseId: '00005',
          nationalCourseId: '00005',
          courseName: '数学',
          courseSpecification: '数学课程',
          testSource: '全国命题',
          courseStatus: '0',
          credit: 3,
          qualifiedScore: 60,
          scoreScale: '100分制',
          subjectiveScore: 40,
          objectiveScore: 60,
          totalScore: 100,
          examDuration: 120,
          isProcedural: 0,
          courseProperty: '理论',
          notes: '无'
        },
        show: false,
        type: 'add',
        callback: this
      },
      createRules: {
        name: [{ required: true, trigger: 'blur', validator: validateRoleName }]
      }
    }
  },
  computed: {
    ...mapGetters(['roleName'])
  },
  methods: {
    unix2CurrentTime,
    /**
     * 获取所有角色权限
     */
    // getPermissionList() {
    //   listResourcePermission().then(response => {
    //     this.permissionList = response.data.list
    //   }).catch(res => {
    //     this.$message.error('加载权限列表失败')
    //   })
    // },
    /**
     * 获取课程列表
     */
    getCourseList() {
      this.listLoading = true
      listCourse(this.listQuery).then(response => {
        console.log('data=' + JSON.stringify(response.data))
        this.roleList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).catch(res => {
        this.$message.error('加载课程列表失败')
      })
    },
    /**
     * 改变每页数量
     * @param size 页大小
     */
    handleSizeChange(size) {
      this.listQuery.page = 1
      this.listQuery.size = size
      this.getCourseList()
    },
    /**
     * 改变页码
     * @param page 页号
     */
    handleCurrentChange(page) {
      this.listQuery.page = page
      this.getCourseList()
    },
    /**
     * 表格序号
     * @param index 数据下标
     * @returns 表格序号
     */
    getTableIndex(index) {
      return (this.listQuery.page - 1) * this.listQuery.size + index + 1
    },
    /**
     * 显示新增角色对话框
     */
    showAddDialog() {
      this.courseDialog.data = this.tempCourse
      this.courseDialog.type = 'add'
      this.courseDialog.show = true
    },
    showDetail(index) {
      const course = this.roleList[index]
      this.courseDialog.data = course
      this.courseDialog.type = 'update'
      this.courseDialog.show = true
    },
    /**
     * 校验角色名是否已经存在
     * @param id 角色id
     * @param name 角色名
     * @returns {boolean}
     */
    isRoleNameUnique(id, name) {
      for (let i = 0; i < this.roleList.length; i++) {
        if (this.roleList[i].id !== id && this.roleList[i].name === name) {
          this.$message.error('角色名已存在')
          return false
        }
      }
      return true
    },
    /**
     * 移除角色
     * @param index 角色下标
     * @returns {boolean}
     */
    removeCourse(index) {
      this.$confirm('删除该课程？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const courseId = this.roleList[index].courseId
        removeCourse(courseId).then(() => {
          this.$message.success('删除成功')
          this.getCourseList()
        }).catch(() => {
          this.$message.error('删除失败')
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    },
    /**
     * 判断角色菜单内的权限是否一个都没选
     * @param index 下标
     * @returns {boolean}
     */
    isMenuNone(index) {
      const handleList = this.permissionList[index].handleList
      for (let i = 0; i < handleList.length; i++) {
        if (this.tempRole.permissionIdList.indexOf(handleList[i].id) > -1) {
          return false
        }
      }
      return true
    },
    /**
     * 判断角色菜单内的权限是否全选了
     * @param index 下标
     * @returns {boolean}
     */
    isMenuAll(index) {
      const handleList = this.permissionList[index].handleList
      for (let i = 0; i < handleList.length; i++) {
        if (this.tempRole.permissionIdList.indexOf(handleList[i].id) < 0) {
          return false
        }
      }
      return true
    },
    /**
     * 根据菜单状态check所有checkbox
     * @param index 下标
     */
    checkAll(index) {
      if (this.isMenuAll(index)) {
        // 如果已经全选了,则全部取消
        this.cancelAll(index)
      } else {
        // 如果尚未全选,则全选
        this.selectAll(index)
      }
    },
    /**
     * checkbox全部选中
     * @param index 下标
     */
    selectAll(index) {
      const handleList = this.permissionList[index].handleList
      for (let i = 0; i < handleList.length; i++) {
        this.addUnique(handleList[i].id, this.tempRole.permissionIdList)
      }
    },
    /**
     * checkbox全部取消选中
     * @param index 下标
     */
    cancelAll(index) {
      const handleList = this.permissionList[index].handleList
      for (let i = 0; i < handleList.length; i++) {
        const idIndex = this.tempRole.permissionIdList.indexOf(handleList[i].id)
        if (idIndex > -1) {
          this.tempRole.permissionIdList.splice(idIndex, 1)
        }
      }
    },
    /**
     * 本方法会在勾选状态改变之后触发
     * @param item 选项
     * @param index 对应下标
     */
    handleChecked(item, index) {
      if (this.tempRole.permissionIdList.indexOf(item.id) > -1) {
        // 选中事件
        // 如果之前未勾选本权限,现在勾选完之后,tempRole里就会包含本id
        // 那么就要将必选的权限勾上
        this.makePermissionChecked(index)
      } else {
        // 取消选中事件
        this.cancelAll(index)
      }
    },
    /**
     * 将角色菜单必选的权限勾上（这里并没有做必选的数据库字段）
     * @param index 权限对应下标
     */
    makePermissionChecked(index) {
      const handleList = this.permissionList[index].handleList
      for (let i = 0; i < handleList.length; i++) {
        this.addUnique(handleList[i].id, this.tempRole.permissionIdList)
      }
    },
    /**
     * 数组内防重复地添加元素
     * @param val 值
     * @param arr 数组
     */
    addUnique(val, arr) {
      const _index = arr.indexOf(val)
      if (_index < 0) {
        arr.push(val)
      }
    }
  }
}
</script>
