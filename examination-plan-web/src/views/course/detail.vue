<template>
  <div class="app-container">
    <el-form
      v-loading.body="loading"
      :model="courseData"
      :rules="updateDetailRules"
      ref="tmpAccount"
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
  <el-form-item label="国家课程代码"><span>{{ courseData.courseCode }}</span></el-form-item>
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
          <el-form-item label="国家课程代码:" prop="courseCode">
            <el-input v-if="toUpdate" v-model="courseData.courseCode" />
            <span v-else>{{ courseData.courseCode }}</span>
          </el-form-item>
        </el-col>
      </el-row> -->
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="课程说明："><span>{{ courseData.courseDesc }}</span></el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="6">
          <el-form-item label="学分："><span>{{ courseData.studyScore }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="合格线："><span>{{ courseData.passLine }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="记分制："><span>{{ courseData.markingSystem }}</span></el-form-item>
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
            <el-select v-model="courseData.paperSource" placeholder="请选择试题来源" :value="courseData.paperSource">
              <el-option label="全国命题" value="全国命题"></el-option>
              <el-option label="省内命题" value="省内命题"></el-option>
              <el-option label="省外协作命题" value="省外协作命题"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="考试时间："><span>{{ courseData.testTime + "分钟" }}</span></el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="课程性质">
            <!-- <span>{{ courseData.curriculum }}</span> -->
            <el-select v-model="courseData.curriculum" placeholder="请选择课程性质" :value="courseData.curriculum">
              <el-option label="理论" value="理论"></el-option>
              <el-option label="实践" value="实践"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <!-- <el-form-item label="课程状态：">
            <span>{{ courseData.courseState == '0' ? "正常" : "注销" }}</span>
          </el-form-item> -->
          <el-form-item label="课程状态：">
            <!-- <span>{{ courseData.curriculum }}</span> -->
            <el-select v-model="courseData.courseState" placeholder="请选择课程状态" :value="courseData.courseState">
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

    <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
      <el-form
        status-icon
        class="small-space"
        label-position="left"
        label-width="115px"
        style="width: 400px; margin-left:50px;"
        :model="tmpPassword"
        :rules="updatePasswordRules"
        ref="tmpPassword"
      >
        <el-form-item label="旧密码" prop="oldPassword" required>
          <el-input
            type="password"
            prefix-icon="el-icon-edit"
            auto-complete="off"
            placeholder="请输入旧密码"
            v-model="tmpPassword.oldPassword"
          />
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword" required>
          <el-input
            type="password"
            prefix-icon="el-icon-edit"
            auto-complete="off"
            placeholder="请输入新密码"
            v-model="tmpPassword.newPassword"
          />
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword2" required>
          <el-input
            type="password"
            prefix-icon="el-icon-edit"
            auto-complete="off"
            placeholder="请再次输入新密码"
            v-model="tmpPassword.newPassword2"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="dialogFormVisible = false">取消</el-button>
        <el-button type="danger" @click.native.prevent="$refs['tmpPassword'].resetFields()">重置</el-button>
        <el-button type="primary" :loading="btnLoading" @click.native.prevent="updatePassword">更新</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import store from '@/store'
import { update as updateAccount, validatePassword } from '@/api/account'
import { unix2CurrentTime } from '@/utils'
import { isValidateEmail } from '@/utils/validate'
import { setToken } from '@/utils/token'
import { mapState } from 'vuex'

export default {
  created() {
    console.log('query=' + JSON.stringify(this.$route.query))
    console.log('params=' + JSON.stringify(this.$route.params))
    console.log('courseId=' + this.$route.query.courseId)
    this.courseData = this.$route.query.data
    this.setDetail()
  },
  data() {
    const validateOldPassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码长度必须在6或以上'))
      }
      // promise异步查询后端密码
      this.validateOldPassword(value).then(isValidate => {
        if (!isValidate) {
          callback(new Error('旧密码不正确'))
        } else {
          callback()
        }
      })
    }
    const validateNewPassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码长度必须在6或以上'))
      } else if (this.isOldNewPasswordSame()) {
        callback(new Error('新旧密码不能一样'))
      } else {
        callback()
      }
    }
    const validateNewPassword2 = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码长度必须在6或以上'))
      } else if (!this.isNewPasswordSame()) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    }
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
      dialogFormVisible: false,
      toUpdate: false,
      tmpPassword: {
        oldPassword: '',
        newPassword: '',
        newPassword2: ''
      },
      updatePasswordRules: {
        oldPassword: [
          { required: true, trigger: 'blur', validator: validateOldPassword }
        ],
        newPassword: [
          { required: true, trigger: 'blur', validator: validateNewPassword }
        ],
        newPassword2: [
          { required: true, trigger: 'blur', validator: validateNewPassword2 }
        ]
      },
      tmpAccount: {
        id: '',
        name: '',
        email: ''
      },
      updateDetailRules: {
        name: [{ required: true, trigger: 'blur', validator: validateName }],
        email: [{ required: true, trigger: 'blur', validator: validateEmail }]
      },
      courseData: {}
    }
  },
  computed: {
    ...mapState({
      account: state => state.account
    })
  },
  methods: {
    unix2CurrentTime,
    /**
     * 设置用户资料
     */
    setDetail() {
      this.tmpAccount.name = this.account.name
      this.tmpAccount.email = this.account.email
    },
    /**
     * 验证旧密码
     * @param oldPassword 旧密码
     */
    validateOldPassword(oldPassword) {
      const account = {
        id: this.account.accountId,
        password: oldPassword
      }
      return validatePassword(account).then(response => response.data)
    },
    /**
     * 新旧密码是否相同
     */
    isOldNewPasswordSame() {
      return this.tmpPassword.oldPassword === this.tmpPassword.newPassword
    },
    /**
     * 新密码1和2是否相同
     */
    isNewPasswordSame() {
      return this.tmpPassword.newPassword === this.tmpPassword.newPassword2
    },
    /**
     * 重置token
     */
    resetToken(token) {
      setToken(token)
      this.account.token = token
    },
    /**
     * 重新获取用户信息
     */
    regainAccountDetail() {
      this.loading = true
      this.btnLoading = true
      store.dispatch('Detail').then(() => {
        this.loading = false
        this.btnLoading = false
      })
    },
    /**
     * 更新用户
     * @param account 用户
     */
    updateAccount(account) {
      this.btnLoading = true
      updateAccount(account).then(response => {
        this.$message.success('更新成功')
        this.resetToken(response.data)
        this.regainAccountDetail()
        this.btnLoading = false
      }).catch(res => {
        this.$message.error('更新失败')
      })
    },
    /**
     * 更新用户资料
     */
    updateDetail() {
      this.$refs.tmpAccount.validate(valid => {
        if (valid) {
          this.tmpAccount.id = this.account.accountId
          this.updateAccount(this.tmpAccount)
        }
      })
    },
    /**
     * 显示更新密码对话框
     */
    showUpdatePasswordDialog() {
      this.dialogFormVisible = true
      this.tmpPassword.oldPassword = ''
      this.tmpPassword.newPassword = ''
      this.tmpPassword.newPassword2 = ''
    },
    /**
     * 更新密码
     */
    updatePassword() {
      this.$refs.tmpPassword.validate(valid => {
        if (valid) {
          const account = {}
          account.id = this.account.accountId
          account.password = this.tmpPassword.newPassword
          this.updateAccount(account)
          this.dialogFormVisible = false
        }
      })
    }
  }
}
</script>
