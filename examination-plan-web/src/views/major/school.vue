<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true">
        <el-form-item>
          <el-button
            type="success"
            icon="el-icon-back"
            @click.native.prevent="$router.back(-1)"
          >返回</el-button>
          <el-button
            type="primary"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
            @click.native.prevent="showAddDialog"
          >添加院校</el-button>
          <el-button
            type="primary"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
            @click.native.prevent="showUpdateDialogBatch"
          >批量修改</el-button>
        </el-form-item>

        <span v-if="hasPermission('role:search')">
          <el-form-item>
            <el-input v-model="search.fieldVal" placeholder="字段值"></el-input>
          </el-form-item>
          <el-form-item>
            <el-select v-model="search.fieldSelect" placeholder="字段名">
              <el-option label="主考院校" value="main_target_school"></el-option>
              <el-option label="主考院校序号" value="main_target_school_code"></el-option>
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
      <el-table-column label="专业编码" align="center" prop="major_id" width="180" />
      <el-table-column label="主考院校" align="center" prop="school_name" width="200" />
      <el-table-column sortable label="第N主考院校" align="center" prop="main_target_school_code" width="200" />
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
    <el-dialog :visible.sync="dialogFormVisible" v-if="operationStatus === 'school'">
        <el-table
          :data="schoolDataList"
          highlight-current-row
          append-to-body
        >
          <el-table-column label="院校编码" align="center" prop="school_id" width="150" />
          <el-table-column label="院校名称" align="center" prop="school_name" width="150" />
          <el-table-column label="选择" align="center">
            <template slot-scope="scope">
              <el-button
                type="success"
                size="mini"
                @click.native.prevent="updateSchoolData(scope.row)"
              >选择此项</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-dialog>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" v-if="operationStatus === 'single'" >
      
      <el-form
        status-icon
        class="small-space"
        label-position="left"
        label-width="75px"
        style="width: 300px; margin-left:50px;"
        :model="tmpData"
        ref="tmpData"
      >
        <el-form-item label="专业大类编码" prop="major_id" required>
          <el-input
            type="text"
            auto-complete="off"
            v-model="tmpData.major_id"
            :disabled="true"
          />
        </el-form-item>
        <el-form-item label="院校名称" prop="main_target_school" required>
          <el-input
            type="text"
            auto-complete="off"
            v-model="tmpData.main_target_school"
            @focus="getSchoolList('school')"
          />
        </el-form-item>
         <el-form-item label="第N主考院校" prop="main_target_school_code">
            <el-input
              type="number"
              auto-complete="off"
              v-model="tmpData.main_target_school_code"
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
        >更新</el-button>
      </div>
    </el-dialog>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" v-if="operationStatus === 'batch'">
      <el-table
        :data="schoolDataList"
        v-loading.body="listLoading"
        element-loading-text="loading"
        border
        fit
        highlight-current-row
        @selection-change="handleSchoolSelectionChange"
      >
        <el-table-column
          type="selection"
          width="40">
        </el-table-column>
        <el-table-column label="院校编码" align="center" prop="school_id" width="150" />
        <el-table-column label="院校名称" align="center" prop="school_name" width="150" />
        
        <el-table-column label="自定义第N院校" align="center">
          <template slot-scope="scope">
            <el-input v-model="scope.row.notes" type="number"/>
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="dialogFormVisible = false">取消</el-button>
        <el-button
          type="primary"
          v-if="dialogStatus === 'update'"
          :loading="btnLoading"
          @click.native.prevent="updateDataByBatch"
        >更新</el-button>
      </div>
    </el-dialog>
    
  </div>
</template>
<script>
import { schoolList as list, schoolSearch as search, schoolRemove as remove, schoolAdd as add, schoolUpdate as update,
  schoolUpdateByBatch as updateByBatch } from '@/api/major'
import { schoolList } from '@/api/school'
import { unix2CurrentTime } from '@/utils'
import { mapGetters } from 'vuex'

export default {
  created() {
    this.majorData = this.$route.query.data
    console.log(this.majorData)
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
        update: '修改主考院校',
        add: '添加主考院校'
      },
      tmpData: {
        major_id: '',
        main_target_school: '00001',
        main_target_school_code: 3
      },
      search: {
        page: null,
        size: null,
        fieldVal: '',
        fieldSelect: null
      },
      majorData: {},
      schoolDataList: [],
      operationStatus: 'single',
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
      list(this.listQuery, this.majorData.major_id).then(response => {
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
      search(this.search, this.majorData.major_id).then(response => {
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
      this.operationStatus = 'single'
      this.dialogFormVisible = true
      this.dialogStatus = 'add'
      this.tmpData.major_id = this.majorData.major_id
      this.tmpData.main_target_school = '00001'
      this.tmpData.main_target_school_code = 3
    },
    /**
     * 添加
     */
    addData() {
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
     * 显示修改对话框
     * @param index 下标
     */
    showUpdateDialog(index) {
      this.operationStatus = 'single'
      this.dialogFormVisible = true
      this.dialogStatus = 'update'
      console.log(this.dataList[index])
      this.tmpData = this.dataList[index]
    },
    /**
     * 更新
     */
    updateData() {
      console.log('tmpdata param:', this.tmpData)
      update(this.tmpData).then(() => {
        this.$message.success('更新成功')
        this.getDataList()
        this.dialogFormVisible = false
      }).catch(res => {
        this.$message.error('更新失败')
      })
    },
    /**
     * 删除
     * @param index 下标
    */
    removeData(index) {
      this.$confirm('删除院校？', '警告', {
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
    },
    showUpdateDialogBatch() {
      // 显示新增对话框
      this.dialogFormVisible = true
      this.dialogStatus = 'update'
      this.getSchoolList('batch')
    },
    /**
     * 批量更新院校
     */
    handleSchoolSelectionChange(val) {
      this.multipleSelection = val
      console.log(this.multipleSelection)
    },
    updateDataByBatch() {
      var params = []
      var me = this
      for (var item of this.multipleSelection) { // item代表数组里面的元素
        var tmpData = {}
        tmpData['major_id'] = me.majorData.major_id
        tmpData['main_target_school'] = item.school_id
        tmpData['main_target_school_code'] = item.notes
        console.log('tmpData', tmpData)
        params.push(tmpData)
      }
      console.log('params:', params)
      this.btnLoading = true
      updateByBatch(params).then(() => {
        this.$message.success('批量更新成功')
        this.getDataList()
        this.dialogFormVisible = false
        this.btnLoading = false
        this.operationStatus = 'single'
      }).catch(res => {
        this.btnLoading = false
        this.$message.error('批量更新失败')
      })
    },
    getSchoolList(status) {
      this.operationStatus = status
      console.log(this.operationStatus)
      var me = this
      // console.log('this.listQuery' + JSON.stringify(this.listQuery))
      schoolList(this.listQuery).then(response => {
        var data = response.data.list
        data.forEach((item, index) => {
          item.notes = index + 1 // 伪赋值用于存放院校编码
        })
        me.schoolDataList = data
        console.log('me.schoolDataList' + me.schoolDataList)
      }).catch(res => {
        this.$message.error('加载院校列表失败')
      })
    },
    updateSchoolData(item) {
      // console.log(item)
      this.tmpData.main_target_school = item.school_id
      // console.log(this.tmpData)
      // this.dialogFormVisible = false
      this.operationStatus = 'single'
      this.$message.success('选择成功')
    }
  }
}
</script>
