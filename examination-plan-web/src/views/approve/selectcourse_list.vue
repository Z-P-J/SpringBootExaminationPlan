<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true">
        <el-form-item>
          <el-button
            type="success"
            icon="el-icon-refresh"
            v-if="hasPermission('role:list')"
            @click.native.prevent="getApproveCourseDataList"
          >刷新</el-button>
          <el-button
            type="primary"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
            @click.native.prevent="AddApproveCourseData"
          >确认申请调整该专业课程</el-button>
          <el-button
            size="mini"
            v-if="hasPermission('role:delete')"
            @click="$router.back(-1)"  
          >返回</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table
      :data="approveCourseDataList"
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
      <el-table-column label="课程编码" align="center" prop="course_id" width="180" />
      <el-table-column label="课程名称" align="center" prop="course_name" width="200" />
      <el-table-column sortable label="课程类型" align="center" prop="course_property" width="200" />
      <el-table-column label="管理" align="center"
        v-if="hasPermission('role:update')">
        <template slot-scope="scope">
          <el-button
            type="warning"
            size="mini"
            v-if="hasPermission('role:update')"
            @click.native.prevent="showUpdateDialog(scope.$index)"
          >替换课程</el-button>
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
        :model="approveCourseData"
        ref="approveCourseData"
      >
        <el-form-item label="课程编码" prop="new_course_id">
          <el-input
            type="text"
            auto-complete="off"
            v-model="approveCourseData.new_course_id"
          />
        </el-form-item>
        <el-form-item label="课程名称" prop="new_course_name">
          <el-input
            type="text"
            auto-complete="off"
            v-model="approveCourseData.new_course_name"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="dialogFormVisible = false">取消</el-button>
        <el-button
          type="primary"
          v-if="dialogStatus === 'update'"
          :loading="btnLoading"
          @click.native.prevent="courseUpdate"
        >确认替换</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { unix2CurrentTime } from '@/utils'
import { getApproveCourseDataList, AddApproveCourseData, removeData} from '@/api/approve_course'
import { mapGetters } from 'vuex'

export default {
  created() {
    this.approveCourseData = this.$route.query.data
    this.Datafrom = this.$route.query
    console.log('schoolId=' + JSON.stringify(this.Datafrom.schoolId))
    console.log('major_id=' + JSON.stringify(this.approveCourseData.major_id))
    console.log('approveCourseData=' + JSON.stringify(this.approveCourseData))
    this.getApproveCourseDataList(this.approveCourseData.major_id)
  },
  data() {
    return {
      Datafrom: {},
      approveCourseData:{},
      approveCourseDataList: [], // 用户列表
      listLoading: false, // 数据加载等待动画
      total: 0, // 数据总数
      listQuery: {
        page: 1, // 页码
        size: 9 // 每页数量
      },
      dialogStatus: 'update',
      dialogFormVisible: false,
      btnLoading: false, // 按钮等待动画
      textMap: {
        update: '专业课程替换',
      },
      approveCourseData: {
        major_id:'',
        school_id:'',
        course_id: '',
        course_name: '',
        new_course_id: '',
        new_course_name: '',
        course_property: '',
      },
      approveCourseDataSet: []
    }
  },
  computed: {
    ...mapGetters(['Id'])
  },
  methods: {
    unix2CurrentTime,
    /**
     * 获取课程列表
     */
    getApproveCourseDataList(id) {
     console.log('id',id)
      getApproveCourseDataList(id).then(response => {
        this.approveCourseDataList = response.data.list
        console.log('approveCourseDataList'+JSON.stringify(approveCourseDataList))
      }).catch(res => {
      })
    },
    
    /**
     * 改变每页数量
     * @param size 页大小
     */
    handleSizeChange(size) {
      this.listQuery.size = size
      this.listQuery.page = 1
      this.getApproveCourseDataList()
    },
    /**
     * 改变页码
     * @param page 页号
     */
    handleCurrentChange(page) {
      this.listQuery.page = page
      this.getApproveCourseDataList()
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
     * 显示修改用户对话框
     * @param index 用户下标
     */
    showUpdateDialog(index) {
      this.dialogFormVisible = true
      this.dialogStatus = 'update'
      console.log(this.dataList[index])
      // this.approveCourseData = this.dataList[index]
    },
    /**
     * 更新用户
     */
    courseUpdate() {
        console.log('this.approveCourseData',(this.approveCourseData))
        this.approveCourseDataSet.push(this.approveCourseData)
        this.$message.success('替换课程暂存成功！，需要确认申请才能生效')
        this.dialogFormVisible=false
    },
    AddApproveCourseData() {
        console.log(' this.Datafrom.schoolId',( this.Datafrom.schoolId))
        AddApproveCourseData(this.approveCourseDataSet, this.Datafrom.schoolId).then(() => {
          this.$message.success('替换课程保存成功！')
          this.btnLoading = false
          this.$router.push({path:'/approve/list'})
        }).catch(res => {
          this.$message.error('替换课程保存失败！')
          this.btnLoading = false
        })
    },

    /**
     * 删除用户
     * @param index 用户下标
    */
    removeData(index) {
      this.$confirm('确认取消专业课程调整？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const major_id = this.dataList[index].major_id
        removeData(id).then(() => {
          this.$message.success('删除成功')
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    }
  }
}
</script>
