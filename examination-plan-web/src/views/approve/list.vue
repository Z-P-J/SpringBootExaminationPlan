<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true">
        <el-form-item>
          <el-button
            type="success"
            size="mini"
            icon="el-icon-refresh"
            @click.native.prevent="getDataList"
          >刷新</el-button>
          <el-button
              type="primary"
              size="mini"
              icon="el-icon-plus"
              v-if="hasPermission('role:add')"
              @click.native.prevent="showAddDialog"
          >添加专业申请</el-button>
        </el-form-item>
        <el-form-item>
          <span v-if="hasPermission('role:search')">
            <el-form-item>
              <el-input v-model="search.fieldVal" placeholder="字段值"></el-input>
            </el-form-item>
            <el-form-item>
              <el-select v-model="search.fieldSelect" placeholder="字段名">
                <el-option label="申请表编码" value="approve_id"></el-option>
                <el-option label="申请表名称" value="approve_name"></el-option>
                <el-option label="状态" value="approve_status"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="searchData" :loading="btnLoading">查询</el-button>
            </el-form-item>
          </span>
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
    >
      <el-table-column label="#" align="center" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column label="申请表编码" align="center" prop="approve_id"  />
      <el-table-column label="申请表名称" align="center" prop="approve_name"  />
      <el-table-column label="申请院校" align="center" prop="school_name" />
      <el-table-column
        sortable
        prop="approve_status"
        label="申请表状态"
        width="100" align="center"
      >
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.approve_status === '已审批' ? 'success' 
            : scope.row.approve_status === '已申请' ? 'warning' : 'danger'"
            disable-transitions>{{scope.row.approve_status}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="apply_time" />
      <el-table-column label="管理" align="center"
        v-if="hasPermission('role:detail') || hasPermission('role:update') || hasPermission('role:delete')">
        <template slot-scope="scope">  
          <router-link
            class="inlineBlock"
            :to="{ path:'/approve/detail/', query: {data:scope.row  } }">
            <el-button
              type="warning"
              size="mini"
              v-if="hasPermission('role:detail') "
            >查看</el-button>
          </router-link>
          <router-link
            class="inlineBlock"
            :to="{ path:'/approve/check/', query: { data:scope.row  } }">
            <el-button
              type="success"
              size="mini"
              v-if="hasPermission('role:update') &&  scope.row.approve_status == '已申请'"
            >审核</el-button>
          </router-link>
          <el-button
            type="danger"
            size="mini"
            v-if="hasPermission('role:delete') "                      
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
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" v-if="operationStatus === 'single'">
      <el-form
        status-icon
        class="small-space"
        label-position="left"
        label-width="120px"
        style="width: 400px; margin-left:50px;"
        :model="tmpData"
        ref="tmpData"
      >
        <el-form-item label="院校编码" prop="school_id" required>
          <el-input
            type="text"
            auto-complete="off"
            v-model="tmpData.school_id"
            @focus="getSchoolList('school')"
          />
        </el-form-item>
        <el-form-item label="院校名称" prop="school_name" required>
          <el-input
            type="text"
            auto-complete="off"
            v-model="tmpData.school_name"
            :disabled="true"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="dialogFormVisible = false">取消</el-button>
        <router-link
            class="inlineBlock"
            :to="{ path:'/approve/selectmajor_list/', query:{school_id:this.tmpData.school_id, ShowStatus:'adjust'} }">
          <el-button
            type="primary"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
          >专业调整</el-button>
          </router-link>
          <router-link
            class="inlineBlock"
            :to="{ path:'/approve/selectmajor_list/', query:{school_id:this.tmpData.school_id, ShowStatus:'continue'} }">
          <el-button
              type="primary"
              icon="el-icon-plus"
              v-if="hasPermission('role:add')"
          >续办专业</el-button>
          </router-link>
          <router-link
            class="inlineBlock"
            :to="{ path:'/approve/selectmajor_list/', query:{school_id:this.tmpData.school_id, ShowStatus:'extend'} }">
          <el-button
              type="primary"
              icon="el-icon-plus"
              v-if="hasPermission('role:add')"
          >扩办专业</el-button>
          </router-link>
          <router-link
            class="inlineBlock"
            :to="{ path:'/approve/add_new/', query:{school_id:this.tmpData.school_id, ShowStatus:'new'} }"> 
         <el-button
            type="primary"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
          >新专业</el-button> 
          </router-link> 
      </div>  
    </el-dialog>
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

  </div>
</template>
<script>
import { list, remove, search } from '@/api/approve'
import { unix2CurrentTime } from '@/utils'
import { mapGetters } from 'vuex'
import { schoolList } from '@/api/school'
export default {
  created() {
    this.getDataList()
    console.log('前端收到dataList=' + JSON.stringify(this.dataList))
    this.ShowStatus()
  },
  data() {
    return {
      dataList: [], // 申请表列表
      dialogStatus: 'add',
      dialogFormVisible: false,
      operationStatus: '',
      ShowStatus:'',
      textMap: {
        add: '请先输入院校信息'
      },
      listLoading: false, // 数据加载等待动画
      total: 0, // 数据总数
      listQuery: {
        page: 1, // 页码
        size: 9 // 每页数量
      },
      btnLoading: false, // 按钮等待动画
      search:{
        page:null,
        size:null,
        fieldVal:'',
        fieldSelect:null
      },
      tmpData: {
        approve_id: '',
        approve_name: '',
        apply_time:'',
        school_id:'',
        school_name:'',
        approve_time:'',
        approve_comment:'',
        approve_status: '',
        approver: '',
        approve_num:'',
      },
      schoolDataList: []
    }
  },
  computed: {
    ...mapGetters(['approve_id'])
  },
  methods: {
    unix2CurrentTime,

    /**
     * 获取申请表列表
     */
    getDataList() {
      this.listLoading = true
      list(this.listQuery).then(response => {
        this.dataList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).catch(res => {
        this.listLoading = false
        this.$message.error('加载申请表列表失败!')
      })
    },

    /*列表查询功能块*/ 
    searchData() {
      this.btnLoading = true
      this.listLoading = true
          console.log('前端收到search=' + JSON.stringify(this.searchData))
      this.search.page = this.listQuery.page
      this.search.size = this.listQuery.size
      search(this.search).then(response => {
        this.dataList = response.data.list
        this.total = response.data.total
        this.listLoading = false
        this.btnLoading = false
      }).catch(res => {
        this.$message.error('搜索失败')
        this.listLoading = false
        this.btnLoading = false
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
      this.tmpData.school_id = '00001'
      this.tmpData.school_name = '四川大学'
      this.operationStatus = 'single'
    },
    getSchoolList() {
      this.operationStatus = 'school'
      var me = this
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
      this.tmpData.school_id = item.school_id
      this.tmpData.school_name = item.school_name
      this.$message.success('选择成功')
      this.operationStatus = 'single'
    },
    changeShow(){
      if(this.tmpData.approve_name === '专业调整'){
        this.ShowStatus = "adjust";
      }
      if(this.tmpData.approve_name === '续办专业'){
       this.ShowStatus = "continue";
      }
      if(this.tmpData.approve_name === '扩办专业'){
        this.ShowStatus = "extend";
      }
      if(this.tmpData.approve_name === '新专业'){
        this.ShowStatus = "new";
      }
      if(this.tmpData.approve_status === '已审批'){
        this.ShowStatus = "Ischeck";
      }
    },

    /**
     * 删除申请表
     * @param index 申请表下标
     * @returns {boolean}
     */
    removeData(index) {
      this.$confirm('删除该申请表？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const approve_id = this.dataList[index].approve_id
        remove(approve_id).then(() => {
          this.$message.success('删除成功!')
          this.getDataList()
        }).catch(() => {
          this.$message.error('删除失败!')
        })
      }).catch(() => {
        this.$message.info('已取消删除!')
      })
    },
  }
}
</script>
