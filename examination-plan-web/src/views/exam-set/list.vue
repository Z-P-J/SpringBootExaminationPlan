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
            @click.native.prevent="getDataList"
          >刷新</el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
            @click.native.prevent="showAddDialog"
          >添加考试</el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            v-if="hasPermission('role:update')"
          >批量操作</el-button>
        </el-form-item>

        <span v-if="hasPermission('role:search')">
          <el-form-item>
            <el-input v-model="search.fieldVal" placeholder="字段值"></el-input>
          </el-form-item>
          <el-form-item>
            <el-select v-model="search.fieldSelect" placeholder="字段名">
              <el-option label="考次" value="exams_id"></el-option>
              <el-option label="考试类型" value="exams_type"></el-option>
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
      <!-- exams_id: '',
      time_id: '',
      exams_type: '',
      jihua_exams_status: '',
      kaowu_exams_status: '',
      kaoji_exams_status: '',
      exam_date: '',
      start_time: '',
      end_time: '',
      default_charge: '',
      photo_charge: '',
      notes: '' -->
      <el-table-column label="考次" align="center" prop="exams_id" width="180" />
      <el-table-column label="考试日期" align="center" prop="exam_date" width="200" />
      <el-table-column label="开始时间" align="center" prop="start_time" width="200" />
      <el-table-column label="结束时间" align="center" prop="end_time" width="120" />
      <el-table-column label="管理" align="center"
        v-if="hasPermission('role:update') || hasPermission('role:update') || hasPermission('role:delete')">
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
        label-width="120px"
        style="width: 300px; margin-left:50px;"
        :model="tmpData"
        ref="tmpData"
      >
      <!-- exams_id: '',
      time_id: '',
      exams_type: '',
      jihua_exams_status: '',
      kaowu_exams_status: '',
      kaoji_exams_status: '',
      exam_date: '',
      start_time: '',
      end_time: '',
      default_charge: '',
      photo_charge: '',
      notes: '' -->
        <el-form-item label="考次编码" prop="exams_id" required>
          <el-input
            type="text"
            auto-complete="off"
            v-model="tmpData.exams_id"
          />
        </el-form-item>
        <el-form-item label="考试类型" prop="exams_type" required>
            <el-select v-model="tmpData.exams_type" placeholder="字段名">
              <el-option label="省考" value="省考"></el-option>
              <el-option label="统考" value="统考"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="计划考次状态" prop="jihua_exams_status">
            <el-select v-model="tmpData.jihua_exams_status" placeholder="字段名">
              <el-option label="已用" value="已用"></el-option>
              <el-option label="未用" value="未用"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="考务考次状态" prop="kaowu_exams_status">
            <el-select v-model="tmpData.kaowu_exams_status" placeholder="字段名">
              <el-option label="已用" value="已用"></el-option>
              <el-option label="未用" value="未用"></el-option>
              <el-option label="冻结" value="冻结"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item label="考籍考次状态" prop="kaoji_exams_status">
            <el-select v-model="tmpData.kaoji_exams_status" placeholder="字段名">
              <el-option label="已用" value="已用"></el-option>
              <el-option label="未用" value="未用"></el-option>
              <el-option label="冻结" value="冻结"></el-option>
            </el-select>  
        </el-form-item>
        <el-form-item label="考试日期" prop="exam_date" required>
          <el-date-picker
            v-model="tmpData.exam_date"
            type="date"
            placeholder="考试日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="时间编码" prop="time_id" required>
            <el-input
              type="text"
              auto-complete="off"
              v-model="tmpData.time_id"
            />
        </el-form-item>
        <el-form-item label="开始时间" prop="start_time" required>
          <el-time-picker
            value-format='HH:mm:ss'
            v-model="tmpData.start_time"
            :picker-options="{
              selectableRange: '6:00:00 - 22:00:00'
            }"
            placeholder="开始时间">
          </el-time-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="end_time" required>
          <el-time-picker
            value-format='HH:mm:ss'
            v-model="tmpData.end_time"
            :picker-options="{
              selectableRange: '6:00:00 - 22:00:00'
            }"
            placeholder="开始时间">
          </el-time-picker>
        </el-form-item>
        
        <el-form-item label="统考课程默认收费" prop="default_charge">
          <el-input
            type="num"
            auto-complete="off"
            v-model="tmpData.default_charge"
          />
        </el-form-item>
        <el-form-item label="照相收费" prop="photo_charge">
          <el-input
            type="num"
            auto-complete="off"
            v-model="tmpData.photo_charge"
          />
        </el-form-item>
        <el-form-item label="备注" prop="notes">
          <el-input
            type="textarea"
            autosize
            placeholder="请输入内容"
            v-model="tmpData.notes">
          </el-input>
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
          @click.native.prevent="addAccount"
        >添加</el-button>
        <el-button
          type="primary"
          v-if="dialogStatus === 'update'"
          :loading="btnLoading"
          @click.native.prevent="updateAccount"
        >更新</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { list, search, remove, add, update } from '@/api/exam-set'
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
      textMap: {
        update: '修改考试信息',
        add: '添加考试'
      },
      dialogStatus: 'add',
      dialogFormVisible: false,
      btnLoading: false, // 按钮等待动画
      tmpData: {
        exams_id: '',
        time_id: '',
        exams_type: '',
        jihua_exams_status: '',
        kaowu_exams_status: '',
        kaoji_exams_status: '',
        exam_date: '',
        start_time: '',
        end_time: '',
        default_charge: '',
        photo_charge: '',
        notes: ''
      },
      search: {
        page: null,
        size: null,
        fieldVal: '',
        fieldSelect: null
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
      this.tmpData.exams_id = '203'
      this.tmpData.time_id = '7A'
      this.tmpData.exams_type = '统考'
      this.tmpData.jihua_exams_status = '未用'
      this.tmpData.kaowu_exams_status = '冻结'
      this.tmpData.kaoji_exams_status = '冻结'
      this.tmpData.exam_date = '2020-07-01'
      this.tmpData.start_time = '10:00:00'
      this.tmpData.end_time = '12:00:00'
      this.tmpData.default_charge = 26
      this.tmpData.photo_charge = 10
      this.tmpData.notes = '后续添加备注'
    },
    /**
     * 添加用户
     */
    addAccount() {
      this.btnLoading = true
      add(this.tmpData).then(() => {
        this.$message.success('添加成功')
        this.getDataList()
        this.dialogFormVisible = false
        this.btnLoading = false
      }).catch(res => {
        this.$message.error('添加失败')
        this.btnLoading = false
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
    updateAccount() {
      update(this.tmpData).then(() => {
        this.$message.success('更新成功')
        this.getDataList()
        this.dialogFormVisible = false
      }).catch(res => {
        this.$message.error('更新失败')
      })
    },
    /**
     * 删除用户
     * @param index 用户下标
    */
    removeData(index) {
      this.$confirm('删除考次？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const id = this.dataList[index].exams_id
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
