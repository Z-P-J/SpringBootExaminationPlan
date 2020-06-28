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
          >创建专业计划</el-button>
          <el-select v-model="courseFilter" placeholder="筛选计划" :value="courseFilter" @change="onChange">
            <el-option label="全部" value="all"></el-option>
            <el-option label="已启用" value="enable"></el-option>
            <el-option label="新建" value="newable"></el-option>
            <el-option label="已编制" value="compable"></el-option>
            <el-option label="已审批" value="approvable"></el-option>
          </el-select>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            :disabled="this.multipleSelection.length === 0 || isDisabled('已启用')"
            @click.native.prevent="disableMajorplan"
          >停用</el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            :disabled="this.multipleSelection.length === 0 || isDisabled('已审批')"
            @click.native.prevent="enableMajorplan"
          >发布启用</el-button>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-plus"
            :disabled="multipleSelection.length === 0"
            @click.native.prevent="deleteMajorplan"
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
      <el-table-column label="计划版本ID" align="center" prop="planVersionId" />
      <el-table-column label="计划名称" align="center" prop="planName" />
      <el-table-column label="状态" align="center" prop="state" />
      <el-table-column label="创建日期" align="center" prop="createDate" />
      <!-- <el-table-column label="审批状态" align="center" prop="approveStatus"
        :filters="[{ text: '正常', value: '0' }, { text: '注销', value: '1' }]"
        :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">{{ scope.row.state }}</template>
      </el-table-column> -->
      <el-table-column label="审批状态" align="center" prop="approveStatus" />
      <el-table-column label="学历处意见" align="center" prop="xuelichuSuggestion" />
      <el-table-column label="领导意见" align="center" prop="leaderSign" />
      <el-table-column
        label="管理"
        align="center"
      >
        <template slot-scope="scope">
        <el-button
            type="warning"
            size="mini"
            @click.native.prevent="showDetail(scope.$index)"
          >查看</el-button>
          <el-button
            type="danger"
            size="mini"
            v-if="hasPermission('role:delete') && scope.row.name !== '超级管理员'"
            @click.native.prevent="removeMajorplan(scope.$index)"
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
    <majorplan-detail-dialog v-model="majorplanDialog"></majorplan-detail-dialog>
    <div style="float: right;">注：状态转换遵循拓扑排序：新建——>已编制——>已审批——>已启用</div>
  </div>
</template>
<script>
import {
  listMajorplan,
  deleteMajorplan,
  removeMajorplan,
  disableMajorplan,
  enableMajorplan
} from '@/api/majorplan'
import { unix2CurrentTime } from '@/utils'
import { mapGetters } from 'vuex'
import majorplanDetailDialog from './majorplanDetailDialog'

export default {
  components: {
    majorplanDetailDialog
  },
  created() {
    // if (this.hasPermission('role:update')) {
    //   this.getPermissionList()
    // }
    if (this.hasPermission('role:list')) {
      this.getList()
    }
    this.getToday()
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
      tempMajorplan: {
        planVersionId: 'P0000',
        planName: '计算机科学专业计划',
        state: '新建',
        createDate: '2020-6-29',
        approveStatus: '计划科提交',
        xuelichuSuggestion: '无',
        leaderSign: '无',
        majorId: 'A000000',
        courseId: '00000'
      },
      majorplanDialog: {
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
      return row.state === value
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    onChange(value) {
      console.log('value=' + value)
      this.getList()
    },
    isDisabled(flag) {
      return this.multipleSelection.filter(v => {
        return v.state === flag
      }).length === 0
    },
    disableMajorplan() {
      console.log('multipleSelection=' + JSON.stringify(this.multipleSelection))
      this.listLoading = true
      const ids = this.multipleSelection.map(v => {
        return v.planVersionId
      })
      console.log('ids=' + ids + '  ' + typeof (ids))
      disableMajorplan(ids).then(response => {
        console.log('data=' + JSON.stringify(response.data))
        this.$refs.multipleTable.clearSelection()
        this.roleList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).error(res => {
        this.listLoading = false
        this.$message.error('停用失败')
      })
    },
    enableMajorplan() {
      console.log('multipleSelection=' + JSON.stringify(this.multipleSelection))
      this.listLoading = true
      const ids = this.multipleSelection.map(v => {
        return v.planVersionId
      })
      enableMajorplan(ids).then(response => {
        console.log('data=' + JSON.stringify(response.data))
        this.$refs.multipleTable.clearSelection()
        this.roleList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).error(res => {
        this.listLoading = false
        this.$message.error('发布失败')
      })
    },
    deleteMajorplan() {
      console.log('multipleSelection=' + JSON.stringify(this.multipleSelection))
      this.listLoading = true
      const ids = this.multipleSelection.map(v => {
        return v.planVersionId
      })
      deleteMajorplan(ids).then(response => {
        console.log('data=' + JSON.stringify(response.data))
        this.$refs.multipleTable.clearSelection()
        this.roleList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).error(res => {
        this.listLoading = false
        this.$message.error('删除失败')
      })
    },
    /**
     * 获取课程列表
     */
    getList() {
      this.listLoading = true
      this.listQuery.filter = this.courseFilter
      listMajorplan(this.listQuery).then(response => {
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
      this.majorplanDialog.data = this.tempMajorplan
      this.majorplanDialog.type = 'add'
      this.majorplanDialog.show = true
    },
    showDetail(index) {
      const course = this.roleList[index]
      this.majorplanDialog.data = course
      this.majorplanDialog.type = 'update'
      this.majorplanDialog.show = true
    },
    /**
     * 移除角色
     * @param index 角色下标
     * @returns {boolean}
     */
    removeMajorplan(index) {
      this.$confirm('删除该计划？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const planVersionId = this.roleList[index].planVersionId
        removeMajorplan(planVersionId).then(() => {
          this.$message.success('删除成功')
          this.getList()
        }).catch(() => {
          this.$message.error('删除失败')
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    },
    getToday() {
      this.tempMajorplan.createDate = new Date()
      console.log('new createDate')
    }
  }
}
</script>
