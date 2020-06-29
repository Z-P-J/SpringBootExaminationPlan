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
          >添加教材</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table
      :data="roleList"
      v-loading.body="listLoading"
      element-loading-text="loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="#" align="center" width="40">
        <template slot-scope="scope">
          <span v-text="getTableIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column label="教材编码" align="center" prop="textbookCode" />
      <el-table-column label="教材名称" align="center" prop="textbookName" />
      <el-table-column label="作者" align="center" prop="author"/>
      <el-table-column label="单价" align="center" prop="price" />
      <el-table-column label="选用类型" align="center" prop="textbookSelectType" />
      <el-table-column label="教材状态" align="center" prop="textbookUseStatus">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.textbookUseStatus === '在用' ? 'success' : (scope.row.textbookUseStatus === '未启用' ? 'warning' : 'danger')"
            disable-transitions>{{scope.row.textbookUseStatus}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="管理"
        align="center"
      >
        <template slot-scope="scope">
        <el-button
            type="info"
            size="mini"
            @click.native.prevent="showDetail(scope.$index)"
          >查看</el-button>
          <el-button
            type="danger"
            size="mini"
            v-if="hasPermission('role:delete') && scope.row.name !== '超级管理员'"
            @click.native.prevent="remove(scope.$index)"
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
    <textbook-detail-dialog v-model="textbookDialog"></textbook-detail-dialog>
  </div>
</template>
<script>
import {
  listTextbook,
  removeTextbook
} from '@/api/course'
import { unix2CurrentTime } from '@/utils'
import { mapGetters } from 'vuex'
import TextbookDetailDialog from './textbook-detail-dialog'

export default {
  components: {
    TextbookDetailDialog
  },
  created() {
    if (this.hasPermission('role:list')) {
      this.getList()
    }
  },
  data() {
    return {
      roleList: [],
      permissionList: [],
      listLoading: false,
      total: 0,
      listQuery: {
        page: 1,
        size: 9
      },
      dialogFormVisible: false,
      btnLoading: false,
      tempTextbook: {
        courseId: '',
        courseName: '',
        textbookCode: '',
        textbookName: '',
        textbookISBN: '',
        textbookType: '教材',
        chiefEditor: '未知',
        author: '未知',
        publishingHouse: '未知',
        version: '1.0',
        publicationDate: '2020-01-01',
        price: 50,
        textbookSelectType: '国家统编',
        syllabus: '',
        textbookUseStatus: '在用',
        textbookStartTime: '2020-01-01',
        textbookEndTime: '2020-12-01'
      },
      textbookDialog: {
        data: {},
        show: false,
        type: 'add',
        callback: this
      }
    }
  },
  computed: {
    ...mapGetters(['roleName'])
  },
  methods: {
    unix2CurrentTime,
    /**
     * 获取课程列表
     */
    getList() {
      this.listLoading = true
      listTextbook(this.listQuery).then(response => {
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
      this.textbookDialog.data = this.tempTextbook
      this.textbookDialog.type = 'add'
      this.textbookDialog.show = true
    },
    showDetail(index) {
      const course = this.roleList[index]
      this.textbookDialog.data = course
      this.textbookDialog.type = 'update'
      this.textbookDialog.show = true
    },
    /**
     * 移除角色
     * @param index 角色下标
     * @returns {boolean}
     */
    remove(index) {
      this.$confirm('删除该教材？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const courseId = this.roleList[index].courseId
        removeTextbook(courseId).then(() => {
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
