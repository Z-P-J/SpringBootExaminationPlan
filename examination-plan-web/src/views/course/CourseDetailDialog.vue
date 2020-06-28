<template>
<div>
  <el-dialog title="课程详情" :visible.sync="value.show" @open="onOpen">
      <el-form
      v-loading.body="loading"
      :model="value.data"
      :rules="courseDetailRules"
      ref="courseForm"
      label-width="115px"
    >
    <el-form-item label="课程名称" prop="courseName">
    <el-col :span="6">
      <el-input v-model="value.data.courseName" />
    </el-col>
  </el-form-item>
  <el-form-item label="课程编码" prop="courseId">
    <!-- <span>{{ value.data.courseId }}</span> -->
    <el-col :span="6">
      <el-input v-model="value.data.courseId" />
    </el-col>
  </el-form-item>
  <el-form-item label="国家课程代码">
    <!-- <span>{{ value.data.nationalCourseId }}</span> -->
    <el-col :span="6">
      <el-input v-model="value.data.nationalCourseId" />
    </el-col>
  </el-form-item>
  <el-form-item label="课程说明：">
    <el-col :span="6">
      <el-input v-model="value.data.courseSpecification" />
    </el-col>
  </el-form-item>

      <el-row :gutter="24">
        <el-col :span="8">
          <el-form-item label="学分："><span>{{ value.data.credit }}</span></el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="合格线："><span>{{ value.data.qualifiedScore }}</span></el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="记分制："><span>{{ value.data.scoreScale }}</span></el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="24">
        <el-col :span="8">
          <el-form-item label="总分："><span>{{ value.data.totalScore }}</span></el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="主观题分数："><span>{{ value.data.subjectiveScore }}</span></el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="客观题分数："><span>{{ value.data.objectiveScore }}</span></el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="考试时间："><span>{{ value.data.examDuration + "分钟" }}</span></el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="试题来源">
            <el-select v-model="value.data.testSource" placeholder="请选择试题来源" :value="value.data.testSource">
              <el-option label="全国命题" value="全国命题"></el-option>
              <el-option label="省内命题" value="省内命题"></el-option>
              <el-option label="省外协作命题" value="省外协作命题"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="课程性质">
            <el-select v-model="value.data.courseProperty" placeholder="请选择课程性质" :value="value.data.courseProperty">
              <el-option label="理论" value="理论"></el-option>
              <el-option label="实践" value="实践"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="课程状态：">
            <el-select v-model="value.data.courseStatus" placeholder="请选择课程状态" :value="value.data.courseStatus">
              <el-option label="正常" value="正常"></el-option>
              <el-option label="注销" value="注销"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="备注：">
            <!-- <span>{{ value.data.notes == null ? '无' : value.data.notes }}</span> -->
            <el-input type="textarea" v-model="value.data.notes"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-button
        v-if="textbookList.length === 0"
        type="primary"
        @click.native.prevent="addTextbook"
        >添加教材</el-button>
      <el-table
      v-else
      :data="textbookList"
      border
      fit
      highlight-current-row
    >
      <!-- <el-table-column label="#" align="center" width="40">
        <template slot-scope="scope">
          <span v-text="getTableIndex(scope.$index)"></span>
        </template>
      </el-table-column> -->
      <el-table-column label="教材编码" align="center" prop="textbookCode" />
      <el-table-column label="教材名称" align="center" prop="textbookName" />
      <el-table-column label="作者" align="center" prop="author"/>
      <!-- <el-table-column label="单价" align="center" prop="price" /> -->
      <el-table-column label="选用类型" align="center" prop="textbookSelectType" />
      <el-table-column label="教材状态" align="center" prop="textbookUseStatus" />
      <el-table-column
        label="管理"
        align="center"
        width="200"
      >
        <template slot-scope="scope">
        <el-button
            type="info"
            size="mini"
            @click.native.prevent="showDetail(scope.$index)"
          >查看</el-button>
          <el-button
            type="danger"
            size="mini"
            v-if="hasPermission('role:delete') && scope.row.name !== '超级管理员'"
            @click.native.prevent="remove(scope.$index)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

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
    <textbook-detail-dialog v-model="textbookDialog"></textbook-detail-dialog>
</div>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
// import { isValidateEmail } from '@/utils/validate'
import {
  addCourse,
  updateCourse,
  getTextbook,
  removeTextbook
} from '@/api/course'
import TextbookDetailDialog from './TextbookDetailDialog'

export default {
  components: {
    TextbookDetailDialog
  },
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
