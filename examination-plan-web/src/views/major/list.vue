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
            v-if="operationStatus === 'single' "
            @click.native.prevent="toggleOperation"
          >批量操作</el-button>
           <el-button
            type="primary"
            icon="el-icon-plus"
            v-if="operationStatus === 'batch'"
            @click.native.prevent="toggleOperation"
          >基本操作</el-button>
          <router-link
            class="inlineBlock"
            :to="{ path:'/major/detail/', query: { Id:0, data:tmpData, 
            actionStatus:'add'  } }">
            <el-button
              type="primary"
              icon="el-icon-refresh"
              v-if="hasPermission('role:add') && operationStatus === 'single'"
            >添加专业</el-button>
          </router-link>
          <el-button
            type="primary"
            icon="el-icon-plus"
            v-if="operationStatus === 'batch' "
            @click.native.prevent="removeDataByBatch"
          >批量删除</el-button>
          <el-button
            type="primary"
            icon="el-icon-plus"
            v-if="hasPermission('role:search') && operationStatus === 'batch'"
            @click.native.prevent="TogglebatchDialogStatus"
          >批量修改</el-button>
        </el-form-item>
        <span v-if="hasPermission('role:search') && operationStatus === 'single'">
          <el-form-item>
            <el-input v-model="search.fieldVal" placeholder="字段值"></el-input>
          </el-form-item>
          <el-form-item>
            <el-select v-model="search.fieldSelect" placeholder="字段名">
              <el-option label="专业编码" value="major_id"></el-option>
              <el-option label="名称" value="major_name"></el-option>
              <el-option label="状态" value="major_status"></el-option>
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
      @selection-change="handleSelectionChange"
    >
    <el-table-column
      v-if="operationStatus === 'batch'"
      type="selection"
      width="40">
    </el-table-column>
      <el-table-column label="#" align="center" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column label="专业编码" align="center" prop="major_id" width="180" />
      <el-table-column label="国家专业编码" align="center" prop="national_major_code" width="200" />
      <el-table-column label="专业名称" align="center" prop="major_name" width="200" />
      <el-table-column
        sortable
        prop="major_status"
        label="专业状态"
        width="100" align="center"
      >
      
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.major_status === '正常' ? 'success' 
            : scope.row.major_status === '即将停考' ? 'warning' : 'danger'"
            disable-transitions>{{scope.row.major_status}}</el-tag>
        </template>
      </el-table-column>
      <!-- <el-table-column sortable label="专业状态" align="center" prop="major_status" width="120" /> -->
      <el-table-column label="管理" align="center"
        v-if="hasPermission('role:update') || hasPermission('role:update') || hasPermission('role:delete')">
        <template slot-scope="scope">
          <router-link
            class="inlineBlock"
            :to="{ path:'/major/detail/', query: { Id:scope.row.major_id, data:scope.row, 
            actionStatus:'update'  } }">
            <el-button
              type="info"
              size="mini"
              v-if="hasPermission('role:detail')">详情
              </el-button>
          </router-link>
          <router-link
            class="inlineBlock"
            :to="{ path:'/major/school', query: { Id:scope.row.major_id, data:scope.row, 
            actionStatus:'add'  } }">
            <el-button
              type="info"
              size="mini"
              v-if="hasPermission('role:list')">主考院校
              </el-button>
          </router-link>
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
    <el-dialog :visible.sync="batchDialogStatus">
      <el-form
        status-icon
        class="small-space"
        label-position="left"
        label-width="75px"
        style="width: 300px; margin-left:50px;"
        :model="tmpData"
        ref="tmpData"
      >

  <el-form-item label="专业类型">
    <el-select v-model="tmpData.major_type" placeholder="请选择专业类型" :value="tmpData.major_type">
      <el-option label="基础科段" value="基础科段"></el-option>
      <el-option label="专科" value="专科"></el-option>
      <el-option label="本科段" value="本科段"></el-option>
      <el-option label="独立本科段" value="独立本科段"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="学历层次">
    <el-select v-model="tmpData.education_level" placeholder="请选择学历层次" :value="tmpData.education_level">
      <el-option label="专科" value="专科"></el-option>
      <el-option label="本科" value="本科"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="开考形式">
    <el-select v-model="tmpData.exam_type" placeholder="请选择开考形式" :value="tmpData.exam_type">
      <el-option label="面向高校" value="面向高校"></el-option>
      <el-option label="面向社会" value="面向社会"></el-option>
    </el-select>
  </el-form-item>
<el-form-item label="首次开考考次"><el-input v-model="tmpData.first_exam_id" /></el-form-item>
<el-form-item label="批准文号"><el-input v-model="tmpData.approve_num" /></el-form-item>
<el-form-item label="停止新生注册考次"><el-input v-model="tmpData.stop_freshman_registration_exam_id"/></el-form-item>
<el-form-item label="停止注册文号"><el-input v-model="tmpData.stop_registration_num" /></el-form-item>
<el-form-item label="停止报考考次"><el-input v-model="tmpData.stop_apply_exam_id" /></el-form-item>
<el-form-item label="停止报考文号"><el-input v-model="tmpData.stop_apply_num" /></el-form-item>
<el-form-item label="停止颁发毕业证日期">
  <el-date-picker
      v-model="tmpData.stop_diploma_date"
      type="date"
      placeholder="停止颁发毕业证日期">
    </el-date-picker>
  </el-form-item>
<el-form-item label="停止颁发文号"><el-input v-model="tmpData.stop_diploma_num" /></el-form-item>
  <el-form-item label="专业状态">
    <el-select v-model="tmpData.major_status" placeholder="请选择专业状态" :value="tmpData.major_status">
      <el-option label="正常" value="正常"></el-option>
      <el-option label="停考" value="停考"></el-option>
      <el-option label="即将停考" value="即将停考"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="总学分"><el-input v-model="tmpData.total_credit" /></el-form-item>
  <el-form-item label="毕业学分"><el-input v-model="tmpData.graduation_credit" /></el-form-item>
  <el-form-item label="总课程数"><el-input v-model="tmpData.total_course_number" /></el-form-item>
  <el-form-item label="是否分方向"><el-input v-model="tmpData.whether_divide_direction" /></el-form-item>
  <el-form-item label="专业大类代码"><el-input v-model="tmpData.major_category_code" /></el-form-item>
  <el-form-item label="报考条件说明">
  <el-input
  type="textarea"
  autosize
  placeholder="请输入内容"
  v-model="tmpData.apply_condition">
  </el-input></el-form-item>
<el-form-item label="毕业条件说明">
  <el-input
  type="textarea"
  autosize
  placeholder="请输入内容"
  v-model="tmpData.graduation_condition">
</el-input></el-form-item>
<el-form-item label="备注">
<el-input
  type="textarea"
  autosize
  placeholder="请输入内容"
  v-model="tmpData.notes">
</el-input></el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="batchDialogStatus = false">取消</el-button>
        <el-button
          type="primary"
          :loading="btnLoading"
          @click.native.prevent="updateDataByBatch"
        >确认更新</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { list, search, remove, removeByBatch, updateByBatch } from '@/api/major'
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
      total: 0, // 数据总数
      listQuery: {
        page: 1, // 页码
        size: 9 // 每页数量
      },
      listLoading: false, // 数据加载等待动画
      btnLoading: false, // 按钮等待动画
      operationStatus: 'single',
      batchDialogStatus: false, // 批量修改选项状态
      tmpData: {
        major_id: 'A100000',
        national_major_code: 'D000',
        major_name: '测试专业',
        major_brief_introduction: '后续补充介绍',
        major_type: '本科段',
        education_level: '本科',
        exam_type: '面向高校',
        main_target_school: '四川大学',
        first_exam_id: '001',
        approve_num: 'BS001',
        stop_freshman_registration_exam_id: '000',
        stop_registration_num: '000',
        stop_apply_exam_id: '000',
        stop_apply_num: '000',
        stop_diploma_date: '2022-12-31',
        stop_diploma_num: '000',
        major_status: '正常',
        total_credit: 200,
        graduation_credit: 110,
        total_course_number: 55,
        whether_divide_direction: 1,
        major_category_code: '1',
        apply_condition: '暂无',
        graduation_condition: '暂无',
        notes: '暂无'
      },
      search: {
        page: null,
        size: null,
        fieldVal: '',
        fieldSelect: null
      },
      multipleSelection: []
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
     * 删除用户
     * @param index 用户下标
    */
    removeData(index) {
      this.$confirm('删除专业？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const id = this.dataList[index].major_id
        remove(id).then(() => {
          this.$message.success('删除成功')
          this.getDataList()
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    },
    /**
     * 进行批量操作
     */
    handleSelectionChange(val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    toggleOperation() {
      if (this.operationStatus === 'batch') {
        this.operationStatus = 'single'
      } else {
        this.operationStatus = 'batch'
      }
    },
    TogglebatchDialogStatus() {
      this.tmpData.major_id = null   
      this.tmpData.national_major_code = null   
      this.tmpData.major_name = null       
      this.tmpData.major_brief_introduction = null         
      this.tmpData.major_type = null      
      this.tmpData.education_level = null     
      this.tmpData.exam_type = null       
      this.tmpData.main_target_school = null       
      this.tmpData.first_exam_id = null      
      this.tmpData.approve_num = null        
      this.tmpData.stop_freshman_registration_exam_id = null      
      this.tmpData.stop_registration_num = null      
      this.tmpData.stop_apply_exam_id = null      
      this.tmpData.stop_apply_num = null      
      this.tmpData.stop_diploma_date = null             
      this.tmpData.stop_diploma_num = null      
      this.tmpData.major_status = null     
      this.tmpData.total_credit = null    
      this.tmpData.graduation_credit = null    
      this.tmpData.total_course_number = null   
      this.tmpData.whether_divide_direction = null  
      this.tmpData.major_category_code = null    
      this.tmpData.apply_condition = null     
      this.tmpData.graduation_condition = null     
      this.tmpData.notes = null 
      this.batchDialogStatus = true
    },
    /**
     * 删除用户
     * @param index 用户下标
    */
    removeDataByBatch(index) {
      var me = this
      this.$confirm('批量删除专业？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        var ids = me.multipleSelection.map((item)=>{
          return ("'" + item.major_id + "'")
        })
        console.log('ids:', ids)
        removeByBatch(ids).then(() => {
          this.$message.success('删除成功')
          this.getDataList()
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    },
    /**
     * 批量更新
     */
    updateDataByBatch() {
      var ids = this.multipleSelection.map((item)=>{
        return ("'" + item.major_id + "'")
      })
      updateByBatch(this.tmpData, ids).then(() => {
        this.$message.success('批量更新成功')
        this.getDataList()
        this.dialogFormVisible = false
      }).catch(res => {
        this.$message.error('批量更新失败')
      })
    }
  }
}
</script>
