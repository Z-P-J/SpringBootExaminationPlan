<template>
  <div class="app-container">
    <el-form
      v-loading.body="loading"
      :model="approveData"
      :rules="approveDetailRules"
      ref="approveForm"
      label-width="115px"
    >


     <el-row :gutter="18">
        <el-col :span="9">
          <el-form-item label="申请表编码:" prop="approve_id">
            <el-input v-if="toUpdate" v-model="approveData.approve_name" />
            <span v-else>{{ approveData.approve_id }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="申请表名称:" prop="approve_name">
            <el-input v-if="toUpdate" v-model="approveData.approve_name" />
            <span v-else>{{ approveData.approve_name }}</span>
          </el-form-item>
        </el-col>
     </el-row>

      <el-row :gutter="18">
        <el-col :span="9">
          <el-form-item label="申请院校编码:" prop="school_id">
            <el-input v-if="toUpdate" v-model="approveData.shcool_id" />
            <span v-else>{{ approveData.school_id }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="申请院校名称:" prop="school_name">
            <el-input v-if="toUpdate" v-model="approveData.school_name" />
            <span v-else>{{ approveData.school_name }}</span>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="18">
        <el-col :span="9">
          <el-form-item label="申请表创建时间:" prop="apply_time">
            <el-input v-if="toUpdate" v-model="approveData.apply_time" />
            <span v-else>{{ approveData.apply_time }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="申请表状态:" prop="approve_status">
            <el-input v-if="toUpdate" v-model="approveData.approve_status" />
            <span v-else>{{ approveData.approve_status }}</span>
          </el-form-item>
        </el-col>
      </el-row>

  
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="申请内容如下："></el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="6">
          <el-form-item label="学分："><span>{{ approveData.approve_comment }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="合格线："><span>{{ approveData.approve_comment }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="记分制："><span>{{ approveData.approve_comment }}</span></el-form-item>
        </el-col>
      </el-row>
      
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="审核情况如下："></el-form-item>
        </el-col>
      </el-row>

      <el-form-item label="审核意见：">
      <el-col :span="18">
        <el-input
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 4}"
        placeholder="请输入审核意见"
        v-model="approveData.approve_comment">
      </el-input>
      </el-col>
      </el-form-item>
      

      <el-form-item label="新专业批准文号：">
      <el-col :span="18">
        <el-input v-model="approveData.approve_num" />
      </el-col>
      </el-form-item>
      <el-form-item label="审核人签名：">
      <el-col :span="8">
        <el-input v-model="approveData.approver" />
      </el-col>
      </el-form-item>
    

      <!-- <el-row :gutter="18">
        <el-col :span="6">
          <el-form-item label="试题来源："><span>{{ approveData.paperSource }}</span></el-form-item>
          <el-form-item label="试题来源">
            <el-select v-model="approveData.testSource" placeholder="请选择试题来源" :value="approveData.testSource">
              <el-option label="全国命题" value="全国命题"></el-option>
              <el-option label="省内命题" value="省内命题"></el-option>
              <el-option label="省外协作命题" value="省外协作命题"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="考试时间："><span>{{ approveData.examDuration + "分钟" }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="课程性质">
            <span>{{ approveData.courseProperty }}</span>
            <el-select v-model="approveData.courseProperty" placeholder="请选择课程性质" :value="approveData.courseProperty">
              <el-option label="理论" value="理论"></el-option>
              <el-option label="实践" value="实践"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="课程状态：">
            <el-select v-model="approveData.courseStatus" placeholder="请选择课程状态" :value="approveData.courseStatus">
              <el-option label="正常" value="0"></el-option>
              <el-option label="注销" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="备注："><span>{{ approveData.notes == null ? '无' : approveData.notes }}</span></el-form-item>
        </el-col>
      </el-row> -->

    </el-form>
                 <!-- //顶部按钮操作 -->
          <el-button @click="$router.back(-1)">返回</el-button>
          <el-button type="danger">重置</el-button>
          <el-button type="success">提交</el-button>
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
      loading: false,
      btnLoading: false,
      toUpdate: false,
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
    /**
     * 更新用户资料
     */
    updateDetail() {
     
    }
  }
}
</script>
