<template>
<el-dialog title="国家课程详情" :visible.sync="value.show">
      <el-form
      v-loading.body="loading"
      :model="value.data"
      :rules="courseDetailRules"
      ref="courseForm"
      label-width="115px"
    >
  <el-form-item label="国家课程代码" prop="nationMajorCode">
    <el-col :span="6">
      <el-input v-model="value.data.nationMajorCode" />
    </el-col>
  </el-form-item>
  <el-form-item label="课程名称" prop="courseName">
    <el-col :span="6">
      <el-input v-model="value.data.courseName" />
    </el-col>
  </el-form-item>
  <el-form-item label="学分">
    <el-col :span="6">
      <el-input v-model="value.data.credit" />
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
  addNationalCourse,
  updateNationalCourse
} from '@/api/course'

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
    return {
      loading: false,
      btnLoading: false,
      toUpdate: false,
      courseDetailRules: {
        courseName: [{ required: true, trigger: 'blur', validator: validateEmpty }],
        textbookName: [{ required: true, trigger: 'blur', validator: validateEmpty }],
        nationMajorCode: [{ required: true, trigger: 'blur', validator: validateCourseId }]
      }
    }
  },
  methods: {
    unix2CurrentTime,
    add() {
      this.$refs.courseForm.validate(valid => {
        if (valid) {
          this.btnLoading = true
          addNationalCourse(this.value.data).then(() => {
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
          updateNationalCourse(this.value.data).then(() => {
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
