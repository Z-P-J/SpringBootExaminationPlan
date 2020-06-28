<template>
    <el-dialog title="领导审核" :visible.sync="value.show">
      <el-form
      v-loading.body="loading"
      :model="value.data"
      :rules="courseDetailRules"
      ref="majorplanForm"
      label-width="115px"
      >
      <el-form-item label="审核意见">
          <el-col :span="8">
            <el-input type="textarea" v-model="value.data.leaderSign" />
          </el-col>
      </el-form-item>
      <el-form-item label="上传签名图片">
          <el-col :span="8">
            <el-button
            type="primary"
            icon="el-icon-plus"
          >上传</el-button>
          </el-col>
      </el-form-item>
      <el-form-item label="上传文件pdf">
          <el-col :span="8">
            <el-button
            type="primary"
            icon="el-icon-plus"
          >上传</el-button>
          </el-col>
      </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          v-if="value.type === 'approve'"
          type="success"
          :loading="btnLoading"
          @click.native.prevent="done"
        >完成</el-button>
        <el-button @click.native.prevent="value.show = false">取消</el-button>
      </div>
    </el-dialog>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
import {
  updateMajorplan
} from '@/api/majorplan'

export default {
  props: {
    value: {
      type: Object
    }
  },
  created() {
    // console.log('created value=' + JSON.stringify(this.value))
  },
  activated() {
    // console.log('activated value=' + JSON.stringify(this.value))
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
        textbookName: [{ required: true, trigger: 'blur', validator: validateEmpty }],
        courseId: [{ required: true, trigger: 'blur', validator: validateCourseId }]
      }
    }
  },
  methods: {
    unix2CurrentTime,
    done() {
      this.value.data.state = '已审批'
      this.value.data.approveStatus = '领导审核'
      this.$refs.majorplanForm.validate(valid => {
        if (valid) {
          this.btnLoading = true
          updateMajorplan(this.value.data).then(() => {
            this.$message.success('审核成功')
            this.value.callback.getList()
            this.value.show = false
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('审核专业计划失败')
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
