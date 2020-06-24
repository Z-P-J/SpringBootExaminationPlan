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
          <router-link
            class="inlineBlock"
            :to="{ path:'/major/detail/', query: { Id:0, data:tmpData, 
            actionStatus:'add'  } }">
            <el-button
              type="primary"
              size="mini"
              icon="el-icon-refresh"
              v-if="hasPermission('role:add')"
            >添加专业</el-button>
          </router-link>
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
    >
      <el-table-column label="#" align="center" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"></span>
        </template>
      </el-table-column>
      <el-table-column label="专业编码" align="center" prop="major_id" width="180" />
      <el-table-column label="国家专业编码" align="center" prop="national_major_code" width="200" />
      <el-table-column label="专业名称" align="center" prop="major_name" width="200" />
      <el-table-column label="专业状态" align="center" prop="major_status" width="120" />
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
    
  </div>
</template>
<script>
import { list, search, remove } from '@/api/major'
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

      btnLoading: false, // 按钮等待动画
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
    }
  }
}
</script>
