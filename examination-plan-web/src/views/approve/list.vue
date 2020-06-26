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
            @click.native.prevent="getApproveList"
          >刷新</el-button>
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
            @click.native.prevent="showaddApproveDialog"
          >添加调整专业申请</el-button>
          <el-button
              type="primary"
              size="mini"
              icon="el-icon-plus"
              v-if="hasPermission('role:add')"
              @click.native.prevent="showaddApproveDialog"
          >添加续办专业申请</el-button>
          <el-button
              type="primary"
              size="mini"
              icon="el-icon-plus"
              v-if="hasPermission('role:add')"
              @click.native.prevent="showaddApproveDialog"
          >添加扩办专业申请</el-button>
         <el-button
            type="primary"
            size="mini"
            icon="el-icon-plus"
            v-if="hasPermission('role:add')"
            @click.native.prevent="showaddApproveDialog"
          >添加新专业申请</el-button>
        </el-form-item>
      </el-form>
      
      <el-form :inline="true" align="center">
        <el-form-item>
            <span v-if="hasPermission('role:search')">
                <el-form-item>
                  <el-input v-model="search.school_name" placeholder="申请院校"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-select v-model="search.approve_name" placeholder="申请表名称">
                    <el-option label="请选择" value />
                    <div v-for="(approve_name, index) in approveList" :key="index">
                      <el-option :label="approve_name" :value="approve_name"/>
                    </div>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="searchBy" :loading="btnLoading">查询</el-button>
                </el-form-item>
              </span>
        </el-form-item>
      </el-form>
    </div>

    <el-table
      :data="approveList"
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
      <el-table-column label="状态" align="center" prop="approve_status" />
      <el-table-column label="创建时间" align="center" prop="apply_time" />
      <el-table-column label="管理" align="center"
        v-if="hasPermission('role:detail') || hasPermission('role:update') || hasPermission('role:delete')">
        <template slot-scope="scope">
           
        <router-link
          class="inlineBlock"
          :to="{ path:'/approve/detail/', query: { approve_id:scope.row.approve_id, data:scope.row  } }">
          <el-button
            type="warning"
            size="mini"
            v-if="hasPermission('role:detail') "
          >查看</el-button>
        </router-link>

        <router-link
          class="inlineBlock"
          :to="{ path:'/approve/check/', query: { approve_id:scope.row.approve_id, data:scope.row  } }">
          <el-button
            type="warning"
            size="mini"
            v-if="hasPermission('role:detail') "
          >审核</el-button>
        </router-link>

          <el-button
            type="danger"
            size="mini"
            v-if="hasPermission('role:delete') "
            @click.native.prevent="removeApprove(scope.$index)"
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
        label-width="75px"
        style="width: 300px; margin-left:50px;"
        :model="tmpAccount"
        :rules="createRules"
        ref="tmpAccount"
      >
        <el-form-item label="账户名" prop="name" required>
          <el-input
            type="text"
            prefix-icon="el-icon-edit"
            auto-complete="off"
            :disabled="dialogStatus === 'updateRole'"
            v-model="tmpAccount.name"
          />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input
            type="text"
            prefix-icon="el-icon-message"
            auto-complete="off"
            :disabled="dialogStatus === 'updateRole'"
            v-model="tmpAccount.email"
          />
        </el-form-item>
        <el-form-item label="密码" prop="password" required
        v-if="dialogStatus !== 'updateRole'">
          <el-input
            type="password"
            prefix-icon="el-icon-edit"
            auto-complete="off"
            v-model="tmpAccount.password"
            v-if="dialogStatus !== 'updateRole'"
          />
        </el-form-item>
        <el-form-item label="角色"
          v-if="dialogStatus === 'updateRole'">
          <el-select placeholder="请选择" v-model="tmpAccount.roleId">
            <el-option v-for="item in roleList" :key="item.id" :label="item.name" :value="item.id" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="dialogFormVisible = false">取消</el-button>
        <el-button
          type="danger"
          v-if="dialogStatus === 'add'"
          @click.native.prevent="$refs['tmpAccount'].resetFields()"
        >重置</el-button>
        <el-button
          type="success"
          v-if="dialogStatus === 'add'"
          :loading="btnLoading"
          @click.native.prevent="addApprove"
        >添加</el-button>
        <el-button
          type="primary"
          v-if="dialogStatus === 'update'"
          :loading="btnLoading"
          @click.native.prevent="updateAccount"
        >更新资料</el-button>
        <el-button
          type="primary"
          v-if="dialogStatus === 'updateRole'"
          :loading="btnLoading"
          @click.native.prevent="updateAccountRole"
        >更新角色</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { list , remove, update, add as updateApprove } from '@/api/approve'
import { list as getapprovenameList, updateAccountRole } from '@/api/approve'
import { isValidateEmail } from '@/utils/validate'
import { unix2CurrentTime } from '@/utils'
import { mapGetters } from 'vuex'

export default {
  created() {
      this.getApproveList()
  },
  data() {
    const validateEmail = (rule, value, callback) => {
      if (!isValidateEmail(value)) {
        callback(new Error('邮箱格式错误'))
      } else {
        callback()
      }
    }
    const validateName = (rule, value, callback) => {
      if (value.length < 3) {
        callback(new Error('账户名长度必须 ≥ 3'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码长度必须 ≥ 6'))
      } else {
        callback()
      }
    }
    return {
      approveList: [], // 申请表列表
      approvenameList: [], // 全部角色
      filterRoleNameList: [], // 用于过滤表格角色的列表 http://element-cn.eleme.io/#/zh-CN/component/table#shai-xuan
      listLoading: false, // 数据加载等待动画
      total: 0, // 数据总数
      listQuery: {
        page: 1, // 页码
        size: 9 // 每页数量
      },
      dialogStatus: 'add',
      dialogFormVisible: false,
      textMap: {
        updateRole: '修改账号角色',
        update: '修改账号',
        add: '添加账号'
      },
      btnLoading: false, // 按钮等待动画
      tmpAccount: {
        accountId: '',
        email: '',
        name: '',
        password: '',
        roleId: 2 // 对应后端数据库普通用户角色Id
      },
      search: {
        page: null,
        size: null,
        school_name: null,
        approve_name: null
      },
      createRules: {
        email: [{ required: true, trigger: 'blur', validator: validateEmail }],
        name: [{ required: true, trigger: 'blur', validator: validateName }],
        password: [
          { required: true, trigger: 'blur', validator: validatePassword }
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
     * 获取所有角色
     */
    getapprovenameList() {
      getapprovenameList().then(response => {
        this.approvenameList = response.data.list
      }).catch(res => {
        this.$message.error('加载角色失败')
      })
    },


    /**
     * 获取申请表列表
     */
    getApproveList() {
      this.listLoading = true
      list(this.listQuery).then(response => {
        console.log('data=' + JSON.stringify(response.data))
        this.approveList = response.data.list
        this.total = response.data.total
        this.listLoading = false
      }).catch(res => {
        this.$message.error('加载申请表列表失败!')
      })
    },

    // //搜索申请表
    // searchBy() {
    //   this.btnLoading = true
    //   this.listLoading = true
    //   this.search.page = this.listQuery.page
    //   this.search.size = this.listQuery.size
    //   search(this.search).then(response => {
    //     this.approveList = response.data.list
    //     this.total = response.data.total
    //     this.listLoading = false
    //     this.btnLoading = false
    //   }).catch(res => {
    //     this.$message.error('搜索失败')
    //   })
    // },


    /**
     * 改变每页数量
     * @param size 页大小
     */
    handleSizeChange(size) {
      this.listQuery.size = size
      this.listQuery.page = 1
      this.getApproveList()
    },
    /**
     * 改变页码
     * @param page 页号
     */
    handleCurrentChange(page) {
      this.listQuery.page = page
      this.getApproveList()
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




    // /**
    //  * 添加申请表
    //  */
    // addApprove() {
    //   this.$refs.tmpAccount.validate(valid => {
    //     if (valid && this.isUniqueDetail(this.tmpAccount)) {
    //       this.btnLoading = true
    //       register(this.tmpAccount).then(() => {
    //         this.$message.success('添加成功')
    //         this.getApproveList()
    //         this.dialogFormVisible = false
    //         this.btnLoading = false
    //       }).catch(res => {
    //         this.$message.error('添加申请表失败')
    //         this.btnLoading = false
    //       })
    //     }
    //   })
    // },


  
    // /**
    //  * 更新用户角色
    //  */
    // updateAccountRole() {
    //   updateAccountRole(this.tmpAccount).then(() => {
    //     this.$message.success('更新成功')
    //     this.getApproveList()
    //     this.dialogFormVisible = false
    //   }).catch(res => {
    //     this.$message.error('更新失败')
    //   })
    // },

    // /**
    //  * 用户资料是否唯一
    //  * @param account 用户
    //  */
    // isUniqueDetail(account) {
    //   for (let i = 0; i < this.accountList.length; i++) {
    //     if (this.accountList[i].name === account.name) {
    //       this.$message.error('账户名已存在')
    //       return false
    //     }
    //     if (this.accountList[i].email === account.email) {
    //       this.$message.error('邮箱已存在')
    //       return false
    //     }
    //   }
    //   return true
    // },


    /**
     * 删除申请表
     * @param index 申请表下标
     * @returns {boolean}
     */
    removeApprove(index) {
      this.$confirm('删除该申请表？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const approveId = this.approveList[index].approve_id
        remove(approveId).then(() => {
          this.$message.success('删除成功!')
          this.getApproveList()
        }).catch(() => {
          this.$message.error('删除失败!')
        })
      }).catch(() => {
        this.$message.info('已取消删除!')
      })
    },
    changeShow(){
      if(this.approveData.approve_name === '专业调整'){
        this.ShowStute = "adjust";
      }
      if(this.approveData.approve_name === '续办专业'){
       this.ShowStute = "continue";
      }
      if(this.approveData.approve_name === '扩办专业'){
        this.ShowStute = "extend";
      }
      if(this.approveData.approve_name === '新专业'){
        this.ShowStute = "new";
      }
      if(this.approveData.approve_statu === '已审批'){
        this.ShowStute = "Ischeck";
      }  
    }
  }
}
</script>
