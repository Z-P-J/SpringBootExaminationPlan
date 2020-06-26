<template>
  <div class="app-container"
   >
    <el-form
      v-loading.body="loading"
      :model="approveData"
      :rules="approveDetailRules"
      ref="approveForm"
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
      
      
      <el-row :gutter="18">
        <el-col :span="18" v-if="approveData.approve_status === '已审批'">
          <el-form-item label="审核情况如下：" >
          </el-form-item>
        </el-col>
        <el-col :span="9" v-else>
          <el-form-item label="申请表审核中！" >
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="审核意见：" v-if="approveData.approve_status === '已审批'"><span>{{ approveData.approve_comment }}</span></el-form-item>
      <el-form-item label="批准文号："  v-if="ShowStatu === 'new'">
        <span>{{ approveData.approve_num }}</span></el-form-item>
      <el-row :gutter="18">
        <el-col :span="9">
          <el-form-item label="审核人:" prop="approver" v-if="approveData.approve_status === '已审批'">
            <span >{{ approveData.approver }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="审核时间:" prop="approve_time" v-if="approveData.approve_status === '已审批'">
            <span >{{ approveData.approve_time }}</span>
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>
                 <!-- //顶部按钮操作 -->
          <el-button @click="$router.back(-1)">返回</el-button>
        
  </div>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
import { isValidateEmail } from '@/utils/validate'



export default {
  created() {
    console.log('query=' + JSON.stringify(this.$route.query))
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
      ShowStatu:'adjust',
      loading: false,
      btnLoading: false,
      toUpdate: false,
      approveDetailRules: {
        name: [{ required: true, trigger: 'blur', validator: validateName }],
        email: [{ required: true, trigger: 'blur', validator: validateEmail }]
      },
      approveData: {}
    }
  },

  methods: {
    unix2CurrentTime,
    //根据专业申报表的不同显示不同部分
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
    },
    /**
     * 更新用户资料
     */
    updateDetail() {
     
    }
  }
}
</script>
