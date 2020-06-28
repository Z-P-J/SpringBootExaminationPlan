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
          >添加考试计划</el-button>
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
      <el-table-column label="#" align="center" width="40">
        <template slot-scope="scope">
          <span v-text="getTableIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column label="考次编码" align="center" prop="examId" />
      <el-table-column label="开考专业编码" align="center" prop="majorId" />
      <!-- <el-table-column label="开考课程编码" align="center" prop="courseId" />
      <el-table-column label="课程名称" align="center" prop="courseName" /> -->
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
            @click.native.prevent="removeExam(scope.$index)"
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
    <exam-major-detail-dialog v-model="courseDialog"></exam-major-detail-dialog>
  </div>
</template>
<script>
import {
  listExam,
  removeExam
} from '@/api/exam-plan'
import { unix2CurrentTime } from '@/utils'
import { mapGetters } from 'vuex'
import ExamMajorDetailDialog from './exam-major-detail-dialog'

export default {
  components: {
    ExamMajorDetailDialog
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
        examId: '',
        // courseId: '',
        majorId: ''
        // timeId: '无',
        // examDate: '全国命题',
        // startTime: '2020-01-01',
        // endTime: '2020-12-01',
        // examApproveStatus: 60,
        // xuelichuSuggestion: '100分制',
        // leaderSign: ''
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
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    /**
     * 获取课程列表
     */
    getList() {
      this.listLoading = true
      this.listQuery.filter = this.courseFilter
      listExam(this.listQuery).then(response => {
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
    removeExam(index) {
      this.$confirm('删除该考次？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const examId = this.roleList[index].examId
        removeExam(examId).then(() => {
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
