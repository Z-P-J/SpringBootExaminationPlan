<template>
  <div class="app-container">
    <el-form
      v-loading.body="loading"
      :model="courseData"
      :rules="courseDetailRules"
      ref="courseForm"
      label-width="115px"
    >
    <!-- <el-form-item>
        <el-row :gutter="18">
          <el-col :span="6">
            <el-button type="success" :loading="btnLoading" @click.native.prevent="regainAccountDetail">重新获取信息</el-button>
          </el-col>

          <el-col :span="6" v-if="!toUpdate">
            <el-button type="primary" :loading="btnLoading" @click.native.prevent="toUpdate = !toUpdate">修改信息</el-button>
          </el-col>
          <el-col :span="6" v-else>
            <el-button type="primary" :loading="btnLoading" @click.native.prevent="updateDetail">确认修改</el-button>
            <el-button type="warning" @click.native.prevent="toUpdate = !toUpdate">取消修改</el-button>
          </el-col>

          <el-col :span="6">
            <el-button type="danger" @click.native.prevent="showUpdatePasswordDialog">修改密码</el-button>
          </el-col>
        </el-row>
      </el-form-item> -->
    <el-form-item label="活动名称">
    <el-col :span="6">
      <el-input v-model="courseData.courseName" />
    </el-col>
  </el-form-item>
  <el-form-item label="课程编码"><span>{{ courseData.courseId }}</span></el-form-item>
  <el-form-item label="国家课程代码"><span>{{ courseData.nationalCourseId }}</span></el-form-item>
      <!-- <el-row :gutter="18">
        <el-col :span="6">
          <el-form-item label="课程名:" prop="courseName">
            <el-input v-if="toUpdate" v-model="courseData.courseName" />
            <span v-else>{{ courseData.courseName }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="课程编码:" prop="courseId">
            <el-input v-if="toUpdate" v-model="courseData.courseId" />
            <span v-else>{{ courseData.courseId }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="国家课程代码:" prop="nationalCourseId">
            <el-input v-if="toUpdate" v-model="courseData.nationalCourseId" />
            <span v-else>{{ courseData.nationalCourseId }}</span>
          </el-form-item>
        </el-col>
      </el-row> -->
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="课程说明："><span>{{ courseData.courseSpecification }}</span></el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="6">
          <el-form-item label="学分："><span>{{ courseData.credit }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="合格线："><span>{{ courseData.qualifiedScore }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="记分制："><span>{{ courseData.scoreScale }}</span></el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="6">
          <el-form-item label="总分："><span>{{ courseData.totalScore }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="主观题分数："><span>{{ courseData.subjectiveScore }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="客观题分数："><span>{{ courseData.objectiveScore }}</span></el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="6">
          <!-- <el-form-item label="试题来源："><span>{{ courseData.paperSource }}</span></el-form-item> -->
          <el-form-item label="试题来源">
            <el-select v-model="courseData.testSource" placeholder="请选择试题来源" :value="courseData.testSource">
              <el-option label="全国命题" value="全国命题"></el-option>
              <el-option label="省内命题" value="省内命题"></el-option>
              <el-option label="省外协作命题" value="省外协作命题"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="考试时间："><span>{{ courseData.examDuration + "分钟" }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="课程性质">
            <!-- <span>{{ courseData.courseProperty }}</span> -->
            <el-select v-model="courseData.courseProperty" placeholder="请选择课程性质" :value="courseData.courseProperty">
              <el-option label="理论" value="理论"></el-option>
              <el-option label="实践" value="实践"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <!-- <el-form-item label="课程状态：">
            <span>{{ courseData.courseStatus == '0' ? "正常" : "注销" }}</span>
          </el-form-item> -->
          <el-form-item label="课程状态：">
            <el-select v-model="courseData.courseStatus" placeholder="请选择课程状态" :value="courseData.courseStatus">
              <el-option label="正常" value="0"></el-option>
              <el-option label="注销" value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="备注："><span>{{ courseData.notes == null ? '无' : courseData.notes }}</span></el-form-item>
        </el-col>
      </el-row>

    </el-form>
  </div>
</template>

<script>
// import store from '@/store'
import { unix2CurrentTime } from '@/utils'
import { isValidateEmail } from '@/utils/validate'
// import { setToken } from '@/utils/token'
// import { mapState } from 'vuex'

export default {
  created() {
    console.log('query=' + JSON.stringify(this.$route.query))
    console.log('params=' + JSON.stringify(this.$route.params))
    console.log('courseId=' + this.$route.query.courseId)
    this.courseData = this.$route.query.data
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
      courseDetailRules: {
        name: [{ required: true, trigger: 'blur', validator: validateName }],
        email: [{ required: true, trigger: 'blur', validator: validateEmail }]
      },
      courseData: {}
    }
  },
  // computed: {
  //   ...mapState({
  //     account: state => state.account
  //   })
  // },
  methods: {
    unix2CurrentTime,
    /**
     * 更新用户资料
     */
    updateDetail() {
      // this.$refs.courseForm.validate(valid => {
      //   if (valid) {

      //   }
      // })
    }
  }
}
</script>
