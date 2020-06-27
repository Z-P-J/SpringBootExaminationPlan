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
          <el-input v-model="value.data.planVersionId" />
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
            type="date"
            placeholder="创建日期">
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
      <!-- <el-row :gutter="24">
        <el-col :span="8">
          <el-form-item label="学分：">
            <span>{{ value.data.credit }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="合格线：">
            <span>{{ value.data.qualifiedScore }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="记分制：">
            <span>{{ value.data.scoreScale }}</span>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="24">
        <el-col :span="8">
          <el-form-item label="总分：">
            <span>{{ value.data.totalScore }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="主观题分数：">
            <span>{{ value.data.subjectiveScore }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="客观题分数：">
            <span>{{ value.data.objectiveScore }}</span>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="考试时间：">
            <span>{{ value.data.examDuration + "分钟" }}</span>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="试题来源">
            <el-select
              v-model="value.data.testSource"
              placeholder="请选择试题来源"
              :value="value.data.testSource"
            >
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
            <el-select
              v-model="value.data.courseProperty"
              placeholder="请选择课程性质"
              :value="value.data.courseProperty"
            >
              <el-option label="理论" value="理论"></el-option>
              <el-option label="实践" value="实践"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18">
          <el-form-item label="课程状态：">
            <el-select
              v-model="value.data.courseStatus"
              placeholder="请选择课程状态"
              :value="value.data.courseStatus"
            >
              <el-option label="正常" value="正常"></el-option>
              <el-option label="注销" value="注销"></el-option>
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
      </el-row> -->
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click.native.prevent="value.show = false">取消</el-button>
      <el-button
        v-if="value.type === 'add'"
        type="success"
        :loading="btnLoading"
        @click.native.prevent="addMajorplan"
      >添加</el-button>
      <el-button
        v-if="value.type === 'update'"
        type="primary"
        :loading="btnLoading"
        @click.native.prevent="updateMajorplan"
      >更新</el-button>
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
    // console.log('query=' + JSON.stringify(this.$route.query))
    // console.log('params=' + JSON.stringify(this.$route.params))
    // console.log('planName=' + this.$route.query.planName)
    // this.courseData = this.$route.query.data
    console.log('created value=' + JSON.stringify(this.value))
  },
  activated() {
    console.log('activated value=' + JSON.stringify(this.value))
  },
  data() {
    // const validateplanName = (rule, value, callback) => {
    //   if (value.match(/^[0-9]{5}$/)) {
    //     callback()
    //   } else {
    //     callback(new Error('课程编码必须由5位数字组成'))
    //   }
    // }
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
        majorId: [{ required: true, trigger: 'blur', validator: validateEmpty }],
        courseId: [{ required: true, trigger: 'blur', validator: validateEmpty }]
      }
    // ,
    //   tempCourse: {
    //     planName: '',
    //     nationalplanName: '',
    //     planVersionId: '',
    //     courseSpecification: '无',
    //     testSource: '全国命题',
    //     courseStatus: '0',
    //     credit: 3,
    //     qualifiedScore: 60,
    //     scoreScale: '100分制',
    //     subjectiveScore: 40,
    //     objectiveScore: 60,
    //     totalScore: 100,
    //     examDuration: 120,
    //     isProcedural: 0,
    //     courseProperty: '理论',
    //     notes: '无'
    //   }
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
     * 修改角色
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
    }
  }
}
</script>
