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
            @click.native.prevent="getList"
          >刷新</el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
            @click.native.prevent="showAddDialog"
          >添加课程</el-button>
          <el-select v-model="courseFilter" placeholder="过滤课程" :value="courseFilter" @change="onChange">
            <el-option label="全部" value="all"></el-option>
            <el-option label="已启用" value="enable"></el-option>
            <el-option label="已停用" value="disable"></el-option>
          </el-select>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            :disabled="isDisabled('正常')"
            @click.native.prevent="disableCourse"
          >停用</el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            :disabled="isDisabled('注销')"
            @click.native.prevent="enableCourse"
          >启用</el-button>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-plus"
            :disabled="multipleSelection.length === 0"
            @click.native.prevent="deleteCourse"
          >删除</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table
      ref="multipleTable"
      :data="roleList"
      v-loading.body="listLoading"
      element-loading-text="loading"
      @selection-change="handleSelectionChange"
      border
      fit
      highlight-current-row
    >
    <el-table-column
      type="selection"
      width="40">
    </el-table-column>
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
      <el-table-column label="课程状态" align="center" prop="courseStatus"
        :filters="[{ text: '正常', value: '0' }, { text: '注销', value: '1' }]"
        :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">{{ scope.row.courseStatus }}</template>
      </el-table-column>
      <el-table-column
        label="管理"
        align="center"
      >
        <template slot-scope="scope">
          <!-- <el-button
            type="danger"
            size="mini"
            @click.native.prevent="setCourseTextbook(scope.$index)"
          >教材</el-button> -->
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
  removeCourse,
  disableCourse,
  enableCourse,
  deleteCourse
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
      this.getList()
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
      courseFilter: 'all',
      multipleSelection: [],
      roleList: [],
      permissionList: [],
      listLoading: false,
      total: 0,
      listQuery: {
        page: 1,
        size: 9,
        filter: 'all'
      },
      dialogFormVisible: false,
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
        courseStatus: '正常',
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
        data: {},
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
    filterTag(value, row) {
      // console.log('filterTag value=' + value)
      return row.courseStatus === value
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    onChange(value) {
      console.log('value=' + value)
      this.getList()
    },
    isDisabled(flag) {
      return this.multipleSelection.length === 0 || this.multipleSelection.filter(v => {
        return v.courseStatus === flag
      }).length === 0
    },
    disableCourse() {
      console.log('multipleSelection=' + JSON.stringify(this.multipleSelection))
      this.listLoading = true
      const ids = this.multipleSelection.map(v => {
        return v.courseId
      })
      disableCourse(ids).then(response => {
        console.log('data=' + JSON.stringify(response.data))
        this.$refs.multipleTable.clearSelection()
        this.roleList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).error(res => {
        this.listLoading = false
        this.$message.error('课程停用失败')
      })
    },
    enableCourse() {
      console.log('multipleSelection=' + JSON.stringify(this.multipleSelection))
      this.listLoading = true
      const ids = this.multipleSelection.map(v => {
        return v.courseId
      })
      enableCourse(ids).then(response => {
        console.log('data=' + JSON.stringify(response.data))
        this.$refs.multipleTable.clearSelection()
        this.roleList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).error(res => {
        this.listLoading = false
        this.$message.error('课程停用失败')
      })
    },
    deleteCourse() {
      console.log('multipleSelection=' + JSON.stringify(this.multipleSelection))
      this.listLoading = true
      const ids = this.multipleSelection.map(v => {
        return v.courseId
      })
      deleteCourse(ids).then(response => {
        console.log('data=' + JSON.stringify(response.data))
        this.$refs.multipleTable.clearSelection()
        this.roleList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).error(res => {
        this.listLoading = false
        this.$message.error('课程停用失败')
      })
    },
    /**
     * 获取课程列表
     */
    getList() {
      this.listLoading = true
      this.listQuery.filter = this.courseFilter
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
      this.getList()
    },
    /**
     * 改变页码
     * @param page 页号
     */
    handleCurrentChange(page) {
      this.listQuery.page = page
      this.getList()
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
          this.getList()
        }).catch(() => {
          this.$message.error('删除失败')
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    }
  }
}
</script>
