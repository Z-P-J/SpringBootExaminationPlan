<template>
<el-dialog title="考试计划审批" :visible.sync="value.show">
      <el-form
      v-loading.body="loading"
      :model="value.data"
      :rules="courseDetailRules"
      ref="courseForm"
      label-width="115px"
    >
    <!-- <el-form-item label="教材名称" prop="textbookName">
    <el-col :span="6">
      <el-input v-model="value.data.textbookName" />
    </el-col>
  </el-form-item> -->
  <el-form-item label="考试编码" prop="examId">
    <el-col :span="6">
      <el-input v-model="value.data.examId" />
    </el-col>
  </el-form-item>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="考试计划审批状态" prop="examApproveStatus">
            <el-select v-model="value.data.examApproveStatus" placeholder="请选择考试计划审批状态" :value="value.data.examApproveStatus">
              <el-option label="计划科提交" value="计划科提交"></el-option>
              <el-option label="学历处确认" value="学历处确认"></el-option>
              <el-option label="领导审核" value="领导审核"></el-option>
              <el-option label="计划发布启用" value="计划发布启用"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="学历处确认意见" prop="xuelichuSuggestion">
        <el-col :span="6">
          <el-input type="textarea" v-model="value.data.xuelichuSuggestion" />
        </el-col>
      </el-form-item>
      <el-form-item label="领导审核签字" prop="leaderSign">
        <el-col :span="6">
          <el-input v-model="value.data.leaderSign" />
        </el-col>
      </el-form-item>

    </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="value.show = false">取消</el-button>
        <el-button
          v-if="value.type === 'add'"
          type="success"
          :loading="btnLoading"
          @click.native.prevent="add"
        >添加</el-button>
        <el-button
          v-if="value.type === 'update'"
          type="primary"
          :loading="btnLoading"
          @click.native.prevent="update"
        >更新</el-button>
      </div>
    </el-dialog>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
import {
  addApprove,
  updateApprove
} from '@/api/exam-plan'

export default {
  props: {
    value: {
      type: Object
    }
  },
  created() {
    console.log('created value=' + JSON.stringify(this.value))
  },
  activated() {
    console.log('activated value=' + JSON.stringify(this.value))
  },
  data() {
    const validateCourseId = (rule, value, callback) => {
      if (value.match(/^[0-9]{5}$/)) {
        callback()
      } else {
        callback(new Error('课程编码必须由5位数字组成'))
      }
    }
    const validateEmpty = (rule, value, callback) => {
      if (value === null || value.length === 0) {
        callback(new Error('请输入内容'))
      } else {
        callback()
      }
    }
    // const validateName = (rule, value, callback) => {
    //   console.log('validateName value=' + value)
    //   if (value.length < 3) {
    //     callback(new Error('课程名称长度必须在3或以上'))
    //   } else {
    //     callback()
    //   }
    // }
    return {
      loading: false,
      btnLoading: false,
      toUpdate: false,
      courseDetailRules: {
        courseName: [{ required: true, trigger: 'blur', validator: validateEmpty }],
        // textbookName: [{ required: true, trigger: 'blur', validator: validateEmpty }],
        courseId: [{ required: true, trigger: 'blur', validator: validateCourseId }]
      }
    }
  },
  methods: {
    unix2CurrentTime,
    add() {
      this.$refs.courseForm.validate(valid => {
        if (valid) {
          this.btnLoading = true
          this.value.data.textbookCode = '0000' + this.value.data.courseId
          addApprove(this.value.data).then(() => {
            this.$message.success('添加成功')
            this.value.callback.getList()
            this.value.show = false
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('添加课程失败')
            this.btnLoading = false
          })
        } else {
          this.$message.error('输入内容有误')
          console.log('表单无效')
        }
      })
    },
    /**
     * 修改角色
     */
    update() {
      this.$refs.courseForm.validate(valid => {
        if (valid) {
          this.btnLoading = true
          updateApprove(this.value.data).then(() => {
            this.$message.success('更新成功')
            this.value.callback.getList()
            this.value.show = false
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('更新课程失败')
            this.btnLoading = false
          })
        } else {
          console.log('表单无效')
        }
      })
    }
  }
}
</script>
