<template>
  <el-dialog title="专业计划详情" :visible.sync="value.show">
    <el-form
      v-loading.body="loading"
      :model="value.data"
      :rules="majorplanDetailRules"
      ref="majorplanForm"
      label-width="115px"
    >
      <el-form-item label="计划版本ID" prop="planVersionId">
        <el-col :span="6">
          <el-input v-model="value.data.planVersionId" :disabled="value.type === 'update'"/>
        </el-col>
      </el-form-item>
      <el-form-item label="计划名称" prop="planName">
        <el-col :span="6">
          <el-input v-model="value.data.planName" />
        </el-col>
      </el-form-item>
      <el-form-item label="状态">
        <el-col :span="6">
        <el-select v-model="value.data.state" placeholder="选择状态" :value="value.data.state">
            <el-option label="新建" value="新建"></el-option>
            <el-option label="已编制" value="已编制"></el-option>
            <el-option label="已审批" value="已审批"></el-option>
            <el-option label="已启用" value="已启用"></el-option>
        </el-select>
        </el-col>
      </el-form-item>
      <el-form-item label="创建日期">
        <el-col :span="6">
          <el-date-picker
            v-model="value.data.createDate"
            :readonly ="true"
            type="date">
          </el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="审批状态">
        <el-col :span="6">
        <el-select v-model="value.data.approveStatus" placeholder="选择审批状态" :value="value.data.approveStatus">
            <el-option label="计划科提交" value="计划科提交"></el-option>
            <el-option label="学历处审核" value="学历处审核"></el-option>
            <el-option label="领导审核" value="领导审核"></el-option>
            <el-option label="计划发布启用" value="计划发布启用"></el-option>
        </el-select>
        </el-col>
      </el-form-item>
      <el-form-item label="学历处意见">
        <el-col :span="6">
          <el-input v-model="value.data.xuelichuSuggestion" />
        </el-col>
      </el-form-item>
      <el-form-item label="领导意见">
        <el-col :span="6">
          <el-input v-model="value.data.leaderSign" />
        </el-col>
      </el-form-item>
      <el-form-item label="专业编码" prop="majorId">
        <el-col :span="6">
          <el-input v-model="value.data.majorId" />
        </el-col>
      </el-form-item>  
      <el-form-item label="课程编码" prop="courseId">
        <el-col :span="6">
          <el-input v-model="value.data.courseId" />
        </el-col>
      </el-form-item>  
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click.native.prevent="value.show = false">取消</el-button>
      <el-button
        v-if="value.type === 'add'"
        type="success"
        :loading="btnLoading"
        @click.native.prevent="addMajorplan"
      >创建</el-button>
      <el-button
        v-if="value.type === 'update'"
        type="primary"
        :loading="btnLoading"
        @click.native.prevent="updateMajorplan"
      >调整</el-button>
    </div>
  </el-dialog>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
import {
  addMajorplan,
  updateMajorplan
} from '@/api/majorplan'

export default {
  props: {
    value: {
      type: Object
    }
  },
  created() {
    this.getToday()
    console.log('new date')
    // console.log('query=' + JSON.stringify(this.$route.query))
    // console.log('params=' + JSON.stringify(this.$route.params))
    // console.log('planName=' + this.$route.query.planName)
    // this.courseData = this.$route.query.data
    // console.log('created value=' + JSON.stringify(this.value))
  },
  activated() {
    console.log('activated value=' + JSON.stringify(this.value))
  },
  data() {
    const validatMajorID = (rule, value, callback) => {
      if (value.match(/^[ABCDYZG][0-9]{6}$/)) {
        callback()
      } else {
        callback(new Error('专业编码编码必须由1位字符(ABCDYZG)和6位数字组成'))
      }
    }
    const validateCourseId = (rule, value, callback) => {
      if (value.match(/^[0-9]{5}$/)) {
        callback()
      } else {
        callback(new Error('课程编码编码必须由5位数字组成'))
      }
    }
    const validateEmpty = (rule, value, callback) => {
      if (value === null || value.length === 0) {
        callback(new Error('请输入内容'))
      } else {
        callback()
      }
    }
    return {
      loading: false,
      btnLoading: false,
      toUpdate: false,
      majorplanDetailRules: {
        planVersionId: [{ required: true, trigger: 'blur', validator: validateEmpty }],
        planName: [{ required: true, trigger: 'blur', validator: validateEmpty }],
        majorId: [{ required: true, trigger: 'blur', validator: validatMajorID }],
        courseId: [{ required: true, trigger: 'blur', validator: validateCourseId }]
      }
    }
  },
  methods: {
    unix2CurrentTime,
    addMajorplan() {
      this.$refs.majorplanForm.validate(valid => {
        if (valid) {
          this.btnLoading = true
          addMajorplan(this.value.data).then(() => {
            this.$message.success('添加成功')
            this.value.callback.getList()
            this.value.show = false
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('添加专业计划失败')
            this.btnLoading = false
          })
        } else {
          this.$message.error('输入内容有误')
          console.log('表单无效')
        }
      })
    },
    /**
     * 修改
     */
    updateMajorplan() {
      this.$refs.majorplanForm.validate(valid => {
        if (valid) {
          this.btnLoading = true
          updateMajorplan(this.value.data).then(() => {
            this.$message.success('更新成功')
            this.value.callback.getList()
            this.value.show = false
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('更新专业计划失败')
            this.btnLoading = false
          })
        } else {
          console.log('表单无效')
        }
      })
    },
    getToday() {
      this.value.data.createDate = new Date()
      console.log('new createDate')
    }
  }
}
</script>
