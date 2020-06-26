<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true">
        <el-form-item>
          <el-button
            type="success"
            icon="el-icon-refresh"
            v-if="hasPermission('role:list')"
            @click.native.prevent="getDataList"
          >刷新</el-button>
          <el-button
            type="primary"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
            @click.native.prevent="showAddDialog"
          >添加统考考次</el-button>
          
          <!-- <el-button
            type="primary"
            icon="el-icon-plus"
            v-if="hasPermission('role:update')"
            
          >批量导入</el-button> -->
        </el-form-item>

        <span v-if="hasPermission('role:search')">
          <el-form-item>
            <el-input v-model="search.fieldVal" placeholder="字段值"></el-input>
          </el-form-item>
          <el-form-item>
            <el-select v-model="search.fieldSelect" placeholder="字段名">
              <el-option label="考次编码" value="exam_id"></el-option>
              <el-option label="课程编码" value="course_id"></el-option>
              <el-option label="时间编码" value="time_id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="searchData" :loading="btnLoading">查询</el-button>
          </el-form-item>
        </span>
      </el-form>
    </div>
    <el-table
      :data="dataList"
      v-loading.body="listLoading"
      element-loading-text="loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="#" align="center" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column label="考次编码" align="center" prop="exam_id" width="180" />
      <el-table-column label="课程编码" align="center" prop="course_id" width="200" />
      <el-table-column sortable label="时间编码" align="center" prop="time_id" width="200" />
      <el-table-column label="管理" align="center"
        v-if="hasPermission('role:update') || hasPermission('role:add') || hasPermission('role:delete')">
        <template slot-scope="scope">
          <el-button
            type="warning"
            size="mini"
            v-if="hasPermission('role:update')"
            @click.native.prevent="showUpdateDialog(scope.$index)"
          >详情</el-button>
          <el-button
            type="danger"
            size="mini"
            v-if="hasPermission('role:delete')"
            @click.native.prevent="removeData(scope.$index)"
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
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form
        status-icon
        class="small-space"
        label-position="left"
        label-width="150px"
        style="width: 400px; margin-left:50px;"
        :model="tmpData"
        :rules="rules"
        ref="tmpData"
      >
        <el-form-item label="考次编码" prop="exam_id" required>
          <el-input
            type="text"
            auto-complete="off"
            v-model="tmpData.exam_id"
          />
        </el-form-item>
        <el-form-item label="课程编码" prop="course_id" required>
          <el-input
            type="text"
            auto-complete="off"
            v-model="tmpData.course_id"
          />
        </el-form-item>
        <el-form-item label="时间编码" prop="time_id" required>
            <el-input
              type="text"
              auto-complete="off"
              v-model="tmpData.time_id"
            />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="dialogFormVisible = false">取消</el-button>
        <el-button
          type="danger"
          v-if="dialogStatus === 'add'"
          @click.native.prevent="$refs['tmpData'].resetFields()"
        >重置</el-button>
        <el-button
          type="success"
          v-if="dialogStatus === 'add'"
          :loading="btnLoading"
          @click.native.prevent="addData"
        >添加</el-button>
        <el-button
          type="primary"
          v-if="dialogStatus === 'update'"
          :loading="btnLoading"
          @click.native.prevent="updateData"
        >更新资料</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { courseList as list, courseSearch as search, courseRemove as remove, courseAdd as add, courseUpdate as update } from '@/api/exam-set'
import { unix2CurrentTime } from '@/utils'
import { mapGetters } from 'vuex'

export default {
  created() {
    if (this.hasPermission('role:list')) {
      this.getDataList()
    }
  },
  data() {
    return {
      dataList: [], // 用户列表
      listLoading: false, // 数据加载等待动画
      total: 0, // 数据总数
      listQuery: {
        page: 1, // 页码
        size: 9 // 每页数量
      },
      dialogStatus: 'add',
      dialogFormVisible: false,
      btnLoading: false, // 按钮等待动画
      textMap: {
        update: '修改统考考次',
        add: '添加统考考次'
      },
      tmpData: {
        exam_id: '',
        course_id: '',
        time_id: ''
      },
      search: {
        page: null,
        size: null,
        fieldVal: '',
        fieldSelect: null
      },
      rules: {
        exam_id: [
          { min: 3, max: 3, message: '3 位数字编码（例：171-17 年 4 月考试）', trigger: 'blur' }
        ],
        course_id: [
          { min: 5, max: 5, message: '5 位数字课程码', trigger: 'blur' }
        ],
        time_id: [
          { min: 2, max: 2, message: '2 位编码（例：7A-周日上午）', trigger: 'blur' }
        ]
      }
    }
  },
  computed: {
    ...mapGetters(['accountId'])
  },
  methods: {
    unix2CurrentTime,
    /**
     * 获取用户列表
     */
    getDataList() {
      this.listLoading = true
      list(this.listQuery).then(response => {
        this.dataList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).catch(res => {
        this.$message.error('加载列表失败')
      })
    },
    searchData() {
      this.btnLoading = true
      this.listLoading = true
      this.search.page = this.listQuery.page
      this.search.size = this.listQuery.size
      search(this.search).then(response => {
        this.dataList = response.data.list
        this.total = response.data.total
        this.listLoading = false
        this.btnLoading = false
      }).catch(res => {
        this.$message.error('搜索失败')
      })
    },
    /**
     * 改变每页数量
     * @param size 页大小
     */
    handleSizeChange(size) {
      this.listQuery.size = size
      this.listQuery.page = 1
      this.getDataList()
    },
    /**
     * 改变页码
     * @param page 页号
     */
    handleCurrentChange(page) {
      this.listQuery.page = page
      this.getDataList()
    },
    /**
     * 表格序号
     * 可参考自定义表格序号
     * http://element-cn.eleme.io/#/zh-CN/component/table#zi-ding-yi-suo-yin
     * @param index 数据下标
     * @returns 表格序号
     */
    getIndex(index) {
      return (this.listQuery.page - 1) * this.listQuery.size + index + 1
    },
    /**
     * 显示添加对话框
     */
    showAddDialog() {
      // 显示新增对话框
      this.dialogFormVisible = true
      this.dialogStatus = 'add'
      this.tmpData.exam_id = '201'
      this.tmpData.course_id = '00001'
      this.tmpData.time_id = '7A'
    },
    /**
     * 添加用户
     */
    addData() {
      this.btnLoading = true
      this.$refs.tmpData.validate((valid) => {
        if (valid) {
          add(this.tmpData).then(() => {
            this.$message.success('添加成功')
            this.getDataList()
            this.dialogFormVisible = false
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('添加失败')
            this.btnLoading = false
          })
        } else {
          this.$message.error('请检查输入格式')
          this.btnLoading = false
          return false
        }
      })
    },
    /**
     * 显示修改用户对话框
     * @param index 用户下标
     */
    showUpdateDialog(index) {
      this.dialogFormVisible = true
      this.dialogStatus = 'update'
      console.log(this.dataList[index])
      this.tmpData = this.dataList[index]
    },
    /**
     * 更新用户
     */
    updateData() {
      this.$refs.tmpData.validate((valid) => {
        if (valid) {
          update(this.tmpData).then(() => {
            this.$message.success('更新成功')
            this.getDataList()
            this.dialogFormVisible = false
          }).catch(res => {
            this.btnLoading = false
            this.$message.error('更新失败')
          })
        } else {
          this.$message.error('请检查输入格式')
          this.btnLoading = false
          return false
        }
      })
    },
    /**
     * 删除用户
     * @param index 用户下标
    */
    removeData(index) {
      this.$confirm('删除专业？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const id = this.dataList[index].id
        remove(id).then(() => {
          this.$message.success('删除成功')
          this.getDataList()
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    }
  }
}
</script>
