<template>
<el-dialog title="教材详情" :visible.sync="value.show">
      <el-form
      v-loading.body="loading"
      :model="value.data"
      :rules="courseDetailRules"
      ref="courseForm"
      label-width="115px"
    >
    <el-form-item label="教材名称" prop="textbookName">
    <el-col :span="6">
      <el-input v-model="value.data.textbookName" />
    </el-col>
  </el-form-item>
  <el-form-item label="课程名称" prop="courseName">
    <el-col :span="6">
      <el-input v-model="value.data.courseName" />
    </el-col>
  </el-form-item>
  <el-form-item label="课程编码" prop="courseId">
    <el-col :span="6">
      <el-input v-model="value.data.courseId" />
    </el-col>
  </el-form-item>
  <el-form-item label="教材ISBN">
    <!-- <span>{{ value.data.nationalCourseId }}</span> -->
    <el-col :span="6">
      <el-input v-model="value.data.textbookISBN" />
    </el-col>
  </el-form-item>
  <el-form-item label="主编">
    <el-col :span="6">
      <el-input v-model="value.data.chiefEditor" />
    </el-col>
  </el-form-item>

  <el-form-item label="作者">
    <el-col :span="6">
      <el-input v-model="value.data.author" />
    </el-col>
  </el-form-item>

  <el-form-item label="出版社">
    <el-col :span="6">
      <el-input v-model="value.data.publishingHouse" />
    </el-col>
  </el-form-item>

  <el-form-item label="版本">
    <el-col :span="6">
      <el-input v-model="value.data.version" />
    </el-col>
  </el-form-item>

  <el-form-item label="出版日期">
    <el-col :span="6">
      <el-date-picker
      v-model="value.data.publicationDate"
      type="date"
      clearable="false"
      placeholder="选择日期">
    </el-date-picker>
    </el-col>
  </el-form-item>

  <el-form-item label="单价">
    <el-col :span="6">
      <el-input v-model="value.data.price" />
    </el-col>
  </el-form-item>

  <el-form-item label="教材大纲">
    <el-col :span="12">
      <el-input v-model="value.data.syllabus" />
    </el-col>
  </el-form-item>

      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="教材使用状态">
            <el-select v-model="value.data.textbookUseStatus" placeholder="请选择教材使用状态" :value="value.data.textbookUseStatus">
              <el-option label="在用" value="在用"></el-option>
              <el-option label="作废" value="作废"></el-option>
              <el-option label="未启用" value="未启用"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="教材选用类别">
            <el-select v-model="value.data.textbookSelectType" placeholder="请选择教材选用类别" :value="value.data.textbookSelectType">
              <el-option label="国家统编" value="国家统编"></el-option>
              <el-option label="自选教材" value="自选教材"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="教材类型">
            <el-select v-model="value.data.textbookType" placeholder="请选择教材类型" :value="value.data.textbookType">
              <el-option label="教材" value="教材"></el-option>
              <el-option label="参考书" value="参考书"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="开始使用日期">
        <el-col :span="12">
          <!-- <el-input v-model="value.data.textbookStartTime" /> -->
          <el-date-picker
            v-model="value.data.textbookStartTime"
            type="date"
            clearable="false"
            placeholder="选择日期">
          </el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="结束使用日期">
        <el-col :span="12">
          <!-- <el-input v-model="value.data.textbookEndTime" /> -->
          <el-date-picker
            v-model="value.data.textbookEndTime"
            type="date"
            clearable="false"
            placeholder="选择日期">
          </el-date-picker>
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
  addTextbook,
  updateTextbook
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
    add() {
      this.$refs.courseForm.validate(valid => {
        if (valid) {
          this.btnLoading = true
          this.value.data.textbookCode = '0000' + this.value.data.courseId
          addTextbook(this.value.data).then(() => {
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
          updateTextbook(this.value.data).then(() => {
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
