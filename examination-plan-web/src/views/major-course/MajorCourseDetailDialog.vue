<template>
<div>
  <el-dialog title="专业课程详情" :visible.sync="value.show">
      <el-form
      v-loading.body="loading"
      :model="value.data"
      :rules="courseDetailRules"
      ref="courseForm"
      label-width="115px"
    >
    <el-form-item label="专业编码" prop="majorId">
        <el-col :span="6">
          <el-input v-model="value.data.majorId" :disabled="value.type === 'update'" />
        </el-col>
      </el-form-item>
      <el-form-item label="课程编码" prop="courseId">
        <el-col :span="6">
          <el-input v-model="value.data.courseId" />
        </el-col>
      </el-form-item>
        <el-form-item label="课程性质">
        <el-col :span="6">
        <el-select v-model="value.data.courseAttr" placeholder="选择状态" :value="value.data.courseAttr">
            <el-option label="公共基础" value="公共基础"></el-option>
            <el-option label="专业核心" value="专业核心"></el-option>
            <el-option label="专业选考" value="专业选考"></el-option>
        </el-select>
        </el-col>
      </el-form-item>
      <el-form-item label="课程类别">
        <el-col :span="6">
        <el-select v-model="value.data.courseType" placeholder="选择状态" :value="value.data.courseType">
            <el-option label="必考" value="必考"></el-option>
            <el-option label="选考" value="选考"></el-option>
        </el-select>
        </el-col>
      </el-form-item>
      <el-form-item label="考核方式">
        <el-col :span="6">
        <el-select v-model="value.data.examMethod" placeholder="选择状态" :value="value.data.examMethod">
            <el-option label="笔试" value="笔试"></el-option>
            <el-option label="非笔试" value="非笔试"></el-option>
            <el-option label="机考" value="机考"></el-option>
        </el-select>
        </el-col>
      </el-form-item>
      <el-form-item label="考试类型">
        <el-col :span="6">
        <el-select v-model="value.data.examType" placeholder="选择状态" :value="value.data.examType">
            <el-option label="统考" value="统考"></el-option>
            <el-option label="省考" value="省考"></el-option>
        </el-select>
        </el-col>
      </el-form-item>
      <el-form-item label="所属方向" prop="directionId">
        <el-col :span="6">
          <el-input v-model="value.data.directionId" />
        </el-col>
      </el-form-item>
      <el-form-item label="备注" prop="notes">
        <el-col :span="6">
          <el-input v-model="value.data.notes" type="textarea"/>
        </el-col>
      </el-form-item>
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
    <!-- <textbook-detail-dialog v-model="textbookDialog"></textbook-detail-dialog> -->
</div>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
// import { isValidateEmail } from '@/utils/validate'
import {
  addCourse,
  updateMajorCourse,
  getTextbook,
  removeTextbook
} from '@/api/major-course'
// import TextbookDetailDialog from './TextbookDetailDialog'

export default {
//   components: {
//     TextbookDetailDialog
//   },
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
    // console.log('created value=' + JSON.stringify(this.value))
  },
  activated() {
    // console.log('activated value=' + JSON.stringify(this.value))
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
    const validatDirectionId = (rule, value, callback) => {
      if (value.match(/^[ABCDYZG][0-9]{8}$/)) {
        callback()
      } else {
        callback(new Error('所属方向编码必须由专业编码后加 2 位序号组成'))
      }
    }
    // const validateEmpty = (rule, value, callback) => {
    //   if (value === null || value.length === 0) {
    //     callback(new Error('请输入内容'))
    //   } else {
    //     callback()
    //   }
    // }
    return {
      textbookList: [],
      textbookDialog: {
        data: {
          courseId: this.value.data.courseId,
          courseName: '',
          textbookCode: '',
          textbookName: '',
          textbookISBN: '',
          textbookType: '教材',
          chiefEditor: '未知',
          author: '未知',
          publishingHouse: '未知',
          version: '1.0',
          publicationDate: '2020-01-01',
          price: 50,
          textbookSelectType: '国家统编',
          syllabus: '',
          textbookUseStatus: '在用',
          textbookStartTime: '2020-01-01',
          textbookEndTime: '2020-12-01'
        },
        show: false,
        type: 'add',
        callback: this
      },
      loading: false,
      btnLoading: false,
      toUpdate: false,
      courseDetailRules: {
        majorId: [{ required: true, trigger: 'blur', validator: validatMajorID }],
        courseId: [{ required: true, trigger: 'blur', validator: validateCourseId }],
        directionId: [{ required: true, trigger: 'blur', validator: validatDirectionId }]
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
        notes: '无'
      }
    }
  },
  methods: {
    unix2CurrentTime,
    onOpen() {
      console.log('onOpen')
      this.textbookList.splice(0, this.textbookList.length)
      if (this.value.type === 'add') {
        return
      }
      getTextbook(this.value.data.courseId).then(response => {
        console.log('data=' + JSON.stringify(response.data))
        if (response.data == null) {
          return
        }
        this.textbookList.push(response.data)
      }).catch(res => {
        this.$message.error('获取课程教材失败')
      })
    },
    getList() {
      this.onOpen()
    },
    addTextbook() {
      this.textbookDialog.data.courseId = this.value.data.courseId
      this.textbookDialog.type = 'add'
      this.textbookDialog.show = true
    },
    showDetail(index) {
      const course = this.textbookList[index]
      this.textbookDialog.data = course
      this.textbookDialog.type = 'update'
      this.textbookDialog.show = true
    },
    remove(index) {
      this.$confirm('删除该教材？', '警告', {
        confirmButtonText: '是',
        cancelButtonText: '否',
        type: 'warning'
      }).then(() => {
        const courseId = this.textbookList[0].courseId
        removeTextbook(courseId).then(() => {
          this.$message.success('删除成功')
          this.getList()
        }).catch(() => {
          this.$message.error('删除失败')
        })
      }).catch(() => {
        this.$message.info('已取消删除')
      })
    },
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
            this.$message.error('添加失败')
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
          updateMajorCourse(this.value.data).then(() => {
            this.$message.success('更新成功')
            this.value.callback.getList()
            this.value.show = false
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('更新失败')
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
