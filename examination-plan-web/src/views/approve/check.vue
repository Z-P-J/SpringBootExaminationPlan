<template>
  <div class="app-container">
    <el-form
      v-loading.body="loading"
      :model="approveData"
      :rules="approveDetailRules"
      ref="tmpApprove"
      label-width="115px"
    >


     <el-row >
      <el-col :span="12"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="12"><div class="grid-content bg-purple-light"></div></el-col>
    </el-row>
      <el-row :gutter="18">
        <el-col :span="9">
          <el-form-item label="申请表编码:" prop="approve_id">
          <span >{{ approveData.approve_id }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="申请表名称:" prop="approve_name">
            <span>{{ approveData.approve_name }}</span>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="18">
        <el-col :span="9">
          <el-form-item label="申请院校编码:" prop="school_id">
            <span>{{ approveData.school_id }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="申请院校名称:" prop="school_name">
            <span >{{ approveData.school_name }}</span>
          </el-form-item>
        </el-col>
      </el-row>

     <el-row :gutter="18">
        <el-col :span="9">
          <el-form-item label="申请表创建时间:" prop="apply_time">
            <span >{{ approveData.apply_time }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="申请表状态:" prop="approve_status">
            <span >{{ approveData.approve_status }}</span>
          </el-form-item>
        </el-col>
    </el-row>

      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="申请内容如下："></el-form-item>
        </el-col>
      </el-row>
     
    <!-- //审核部分 -->
      <el-form-item label="审核意见：" >
        <el-col :span="18">
          <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 4}"
          placeholder="请输入审核意见"
          v-model="tmpApprove.approve_comment"
          >
          </el-input>
        </el-col>
      </el-form-item>

      <el-form-item  v-if="ShowStute === 'new'" label="专业批准文号：">
        <el-col :span="18">
          <el-input  placeholder="请输入新专业的批准文号" 
          v-model="tmpApprove.approve_num"/>
        </el-col>
      </el-form-item>

      <el-form-item label="审核人签名：">
        <el-col :span="8">
          <el-input  v-model="tmpApprove.approver"/>
        </el-col>
      </el-form-item>

    </el-form>
                 <!-- //顶部按钮操作 -->
          <el-button 
          @click="$router.back(-1)"
          >返回</el-button>

          <el-button type="danger" 
          @click.native.prevent="$refs['tmpApprove'].resetFields()"
          >重置</el-button>

          <el-button  type="primary" 
          v-if="approveData.approve_status === '已申请'"
          :loading="btnLoading"
          @click.native.prevent="updateApprove"
          >提交</el-button>
  </div>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
import { isValidateEmail } from '@/utils/validate'


export default {
  created() {
    console.log('params=' + JSON.stringify(this.$route.params))
    console.log('approveId=' + this.$route.query.approve_id)
    this.approveData = this.$route.query.data
    console.log('approveData=' + JSON.stringify(this.approveData))
    this.changeShow()
  },
  data() {
    const validateName = (rule, value, callback) => {
      if (value.length < 3) {
        callback(new Error('账户名长度必须在3或以上'))
      } else {
        callback()
      }
    }
    const validateEmail = (rule, value, callback) => {
      if (!isValidateEmail(value)) {
        callback(new Error('邮箱格式错误'))
      } else {
        callback()
      }

    }
    
    return {
      ShowStute:'adjust',
      loading: false,
      btnLoading: false,
      toUpdate: false,
      tmpApprove: {
        approve_id: '',
        approve_name: '',
        apply_time:'',
        school_id:'',
        school_name:'',
        approve_time:'',
        approve_comment:'1111111111',
        approve_status: '',
        approver: '江大白',
        approve_num:'1111111111111111',
        roleId: 2 // 对应后端数据库普通用户角色Id
      },
      approveDetailRules: {
        name: [{ required: true, trigger: 'blur', validator: validateName }],
        email: [{ required: true, trigger: 'blur', validator: validateEmail }]
      },
      approveData: {},
      textarea:''
    }
  },

  methods: {
    unix2CurrentTime,
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
    },

  /**
     * 进行申请表添加
     */
    add() {
      this.$refs.temApprove.validate(valid => {
        if (valid ) {
          this.btnLoading = true
          add(this.temApprove).then(() => {
            this.$message.success('申请表提交成功！')
            this.getApproveList()
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('申请表提交失败！')
            this.btnLoading = false
          })
        }
      })
    },
    /**
     * 进行审批
     */
    addCheck() {
      this.$refs.temApprove.validate(valid => {
        if (valid ) {
          this.btnLoading = true
          add(this.temApprove).then(() => {
            this.$message.success('审批提交成功！')
            this.getApproveList()
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('审批提交失败！')
            this.btnLoading = false
          })
        }
      })
    },
    /**
     * 更新用户资料
     */
    updateDetail() {
     
    }
  }
}
</script>
