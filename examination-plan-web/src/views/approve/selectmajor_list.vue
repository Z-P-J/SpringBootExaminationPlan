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
            icon="el-icon-setting"
            v-if="operationStatus === 'single' && ShowStatus !== 'adjust'"
            @click.native.prevent="toggleOperation"
          >批量操作</el-button>
           <el-button
            icon="el-icon-setting"
            v-if="operationStatus === 'batch'"
            @click.native.prevent="toggleOperation "
          >基本操作</el-button>
          <el-button
            icon="el-icon-plus"
            type="primary"
            v-if="hasPermission('role:list') && operationStatus === 'batch'"
            @click.native.prevent="addDataByBatch"
          >批量添加</el-button>
          <el-button
            v-if="hasPermission('role:list')"
            @click="$router.back(-1)"
          >返回</el-button>
        </el-form-item>
        
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
      <el-table-column label="专业编码" align="center" prop="major_id" width="150" />
      <el-table-column label="大类专业编码" align="center" prop="major_category_code" width="150" />
      <el-table-column label="国家专业编码" align="center" prop="national_major_code" width="150" />
      <el-table-column label="专业名称" align="center" prop="major_name" />
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
      <el-table-column label="管理" align="center"
        v-if="hasPermission('role:detail') && ShowStatus === 'adjust' ">
        <template slot-scope="scope">
          <router-link
            class="inlineBlock"
            :to="{ path:'/approve/selectcourse_list', query: { MajorId:scope.row.major_id, 
            schoolId: Datafrom,
            data:scope.row, actionStatus:'update',ShowStatus:'adjust' } }">
            <el-button
              type="warning"
              size="mini"
              v-if="hasPermission('role:detail') && ShowStatus === 'adjust'">专业课程调整
              </el-button>
            </router-link>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.page"
      :page-size="listQuery.size"
      :total="total"
      :page-sizes="[10, 20, 50, 100]"
      layout="total, sizes, prev, pager, next, jumper"
    ></el-pagination>
  </div>
</template>
<script>

import { addByBatch,continuelist, extendlist, search, remove } from '@/api/approve'
import { addApproveMajorByBatch } from '@/api/approve_major'
import { unix2CurrentTime } from '@/utils'
import { mapGetters } from 'vuex'

export default {
  created() {
    console.log('this.$route.query.school_id=',this.$route.query.school_id)
    this.Datafrom = this.$route.query.school_id
    this.tmpData.school_id = this.$route.query.school_id
    this.ShowStatus = this.$route.query.ShowStatus;
    this.getDataList()
    console.log('kaishiShowStatus=',this.ShowStatus)
  },
  data() {
    return {
      Datafrom: null,
      dataList: [], // 用户列表
      total: 0, // 数据总数
      listQuery: {
        page: 1, // 页码
        size: 9 // 每页数量
      },
      listLoading: false, // 数据加载等待动画
      btnLoading: false, // 按钮等待动画
      ShowStatus:'',
      tmpData: {
        major_id: '',
        national_major_code: '',
        major_name: '',
        major_brief_introduction: '',
        major_type: '',
        education_level: '',
        exam_type: '',
        main_target_school: '',
        first_exam_id: '',
        approve_num: '',
        stop_freshman_registration_exam_id: '',
        stop_registration_num: '',
        stop_apply_exam_id: '',
        stop_apply_num: '',
        stop_diploma_date: '',
        stop_diploma_num: '',
        major_status: '',
        total_credit: null,
        graduation_credit: null,
        total_course_number: null,
        whether_divide_direction: null,
        major_category_code: null,
        apply_condition: '',
        graduation_condition: '',
        notes: ''
      },
      rules: {
        national_major_code: [
          { min: 7, max: 7, message: '7 位编码（1 位字符+6 位数字）', trigger: 'blur' }
        ],
        major_category_code: [
          { min: 7, max: 7, message: '7 位编码（1 位字符+6 位数字）', trigger: 'blur' }
        ],
        first_exam_id: [
          { min: 3, max: 3, message: '3 位数字编码（例：171-17 年 4 月考试）', trigger: 'blur' }
        ],
        stop_freshman_registration_exam_id: [
          { min: 3, max: 3, message: '3 位数字编码（例：171-17 年 4 月考试）', trigger: 'blur' }
        ],
        stop_apply_exam_id: [
          { min: 3, max: 3, message: '3 位数字编码（例：171-17 年 4 月考试）', trigger: 'blur' }
        ]
      },
      operationStatus: 'single', // 批量修改选项状态
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
    getDataList(ShowStatus) {
      if(this.ShowStatus === 'continue' || this.ShowStatus === 'adjust'){
        console.log('this.tmpData.school_id',this.tmpData.school_id)
        continuelist(this.listQuery, this.tmpData.school_id ).then(response => {
          this.dataList = response.data.list
          this.total = response.data.total
          this.listLoading = false
        }).catch(res => {
          this.$message.error('加载列表失败')
        })
      }else if(this.ShowStatus === 'extend'){
        console.log('this.tmpData.school_id',this.tmpData.school_id)
        extendlist(this.listQuery, this.tmpData.school_id ).then(response => {
          this.dataList = response.data.list
          this.total = response.data.total
          this.listLoading = false
      }).catch(res => {
          this.$message.error('加载列表失败')
        })
      }else {
        this.$message.error('加载列表失败')
      }
      this.listLoading = true
      
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
      for (var item in this.tmpData) {
        this.tmpData[item] = null
      }
    },
    /**
     * 批量添加
     */
    addDataByBatch() {
      var param = {}
      var operation_status = null
      param["school_id"]=this.tmpData.school_id
      if(this.ShowStatus === 'adjust'){
        operation_status = '专业调整'
      }else if(this.ShowStatus === 'continue'){
        operation_status = '续办专业'
      }else if(this.ShowStatus === 'extend'){
        operation_status = '扩办专业'
      }else if(this.ShowStatus === 'new'){
        operation_status = '新专业'
      }
      param["operation_status"] = operation_status
      console.log("param",param)
      var ids = this.multipleSelection.map((item) => {
        return item.major_id
      })
      console.log("ids",ids)
      this.btnLoading = true
      addApproveMajorByBatch(param, ids).then(() => {
        this.$message.success('添加成功')
        this.btnLoading = false
        this.$router.push({path:'/approve/list'})
      }).catch(res => {
        this.btnLoading = false
        this.$message.error('批量添加失败')
      })
    }
  }
}
</script>
