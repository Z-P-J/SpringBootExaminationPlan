<template>
<el-dialog title="专业课程详情" :visible.sync="value.show">
      <el-form
      v-loading.body="loading"
      :model="value.data"
      :rules="courseDetailRules"
      ref="courseForm"
      label-width="115px"
    >
    <!-- <el-form-item label="课程名称" prop="courseName">
    <el-col :span="6">
      <el-input v-model="value.data.courseName" />
    </el-col>
  </el-form-item> -->
  <el-form-item label="课程编码" prop="courseId">
    <!-- <span>{{ value.data.courseId }}</span> -->
    <el-col :span="6">
      <el-input v-model="value.data.courseId" />
    </el-col>
  </el-form-item>
  <el-form-item label="专业编码">
    <el-col :span="6">
      <el-input v-model="value.data.majorId" />
    </el-col>
  </el-form-item>
  <el-form-item label="方向编码">
    <el-col :span="6">
      <el-input v-model="value.data.directionId" />
    </el-col>
  </el-form-item>


      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="课程性质">
            <el-select v-model="value.data.courseAttr" placeholder="请选择课程性质" :value="value.data.courseAttr">
              <el-option label="专业核心" value="专业核心"></el-option>
              <el-option label="公共基础" value="公共基础"></el-option>
              <el-option label="专业选考" value="专业选考"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="课程类别">
            <el-select v-model="value.data.courseType" placeholder="请选择课程类别" :value="value.data.courseType">
              <el-option label="必考" value="必考"></el-option>
              <el-option label="选考" value="选考"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="考核方式">
            <el-select v-model="value.data.examMethod" placeholder="请选择考核方式" :value="value.data.examMethod">
              <el-option label="笔试" value="笔试"></el-option>
              <el-option label="非笔试" value="非笔试"></el-option>
              <el-option label="机考" value="机考"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="考试类型">
            <el-select v-model="value.data.examType" placeholder="请选择考试类型" :value="value.data.examType">
              <el-option label="统考" value="统考"></el-option>
              <el-option label="省考" value="省考"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="备注：">
            <el-input type="textarea" v-model="value.data.notes"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native.prevent="value.show = false">取消</el-button>
        <el-button
          v-if="value.type === 'add'"
          type="success"
          :loading="btnLoading"
          @click.native.prevent="addCourse"
        >添加</el-button>
        <el-button
          v-if="value.type === 'update'"
          type="primary"
          :loading="btnLoading"
          @click.native.prevent="updateCourse"
        >更新</el-button>
      </div>
    </el-dialog>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
// import { isValidateEmail } from '@/utils/validate'
import {
  addCourse,
  updateCourse
} from '@/api/major-course'

export default {
  props: {
    value: {
      type: Object
    }
  },
  created() {
    // console.log('query=' + JSON.stringify(this.$route.query))
    // console.log('params=' + JSON.stringify(this.$route.params))
    // console.log('courseId=' + this.$route.query.courseId)
    // this.courseData = this.$route.query.data
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
        courseId: [{ required: true, trigger: 'blur', validator: validateCourseId }]
      },
      tempCourse: {
        courseId: '',
        nationalCourseId: '',
        courseName: '',
        courseSpecification: '无',
        testSource: '全国命题',
        courseStatus: '0',
        credit: 3,
        qualifiedScore: 60,
        scoreScale: '100分制',
        subjectiveScore: 40,
        objectiveScore: 60,
        totalScore: 100,
        examDuration: 120,
        isProcedural: 0,
        courseProperty: '理论',
        exam_type: '统考',
        notes: '无'
      }
    }
  },
  methods: {
    unix2CurrentTime,
    addCourse() {
      this.$refs.courseForm.validate(valid => {
        if (valid) {
          this.btnLoading = true
          addCourse(this.value.data).then(() => {
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
    updateCourse() {
      this.$refs.courseForm.validate(valid => {
        if (valid) {
          this.btnLoading = true
          updateCourse(this.value.data).then(() => {
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
