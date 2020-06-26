<template>
  <div class="app-container">
    <el-form
      v-loading.body="loading"
      :model="Data"
      ref="Data"
      :rules="rules"
      label-width="115px"
    >
<el-row :gutter="18">
	<el-col :span="12">
	<el-form-item label="专业编码" prop="major_id">
    <el-input v-model="Data.major_id" v-if="actionStatus === 'add'"/>
    <el-input v-model="Data.major_id" v-else="" :disabled="true"/>
    </el-form-item>
	</el-col>
	<el-col :span="12">
	<el-form-item label="国家专业代码"  prop="national_major_code"><el-input v-model="Data.national_major_code" /></el-form-item>
	</el-col>
</el-row>
<el-row :gutter="18">
	<el-col :span="12">
<el-form-item label="专业名称"><el-input v-model="Data.major_name" /></el-form-item>
	</el-col>
	<el-col :span="12">
<el-form-item label="专业简介"><el-input v-model="Data.major_brief_introduction" /></el-form-item>
	</el-col>
</el-row>
<el-row :gutter="18">
<el-col :span="8">
  <el-form-item label="专业类型">
    <el-select v-model="Data.major_type" placeholder="请选择专业类型" :value="Data.major_type">
      <el-option label="基础科段" value="基础科段"></el-option>
      <el-option label="专科" value="专科"></el-option>
      <el-option label="本科段" value="本科段"></el-option>
      <el-option label="独立本科段" value="独立本科段"></el-option>
    </el-select>
  </el-form-item>
</el-col>
<el-col :span="8">
  <el-form-item label="学历层次">
    <el-select v-model="Data.education_level" placeholder="请选择学历层次" :value="Data.education_level">
      <el-option label="专科" value="专科"></el-option>
      <el-option label="本科" value="本科"></el-option>
    </el-select>
  </el-form-item>
</el-col>
<el-col :span="8">
  <el-form-item label="开考形式">
    <el-select v-model="Data.exam_type" placeholder="请选择开考形式" :value="Data.exam_type">
      <el-option label="面向高校" value="面向高校"></el-option>
      <el-option label="面向社会" value="面向社会"></el-option>
    </el-select>
  </el-form-item>
</el-col>
</el-row>

<el-row :gutter="18">
<el-col :span="12"><el-form-item label="首次开考考次"><el-input v-model="Data.first_exam_id" /></el-form-item></el-col>
<el-col :span="12"><el-form-item label="批准文号"><el-input v-model="Data.approve_num" /></el-form-item></el-col>
</el-row>

<el-row :gutter="18">
<el-col :span="12"><el-form-item label="停止新生注册考次"><el-input v-model="Data.stop_freshman_registration_exam_id"/></el-form-item></el-col>
<el-col :span="12"><el-form-item label="停止注册文号"><el-input v-model="Data.stop_registration_num" /></el-form-item></el-col>
</el-row>

<el-row :gutter="18">
<el-col :span="12"><el-form-item label="停止报考考次"><el-input v-model="Data.stop_apply_exam_id" /></el-form-item></el-col>
<el-col :span="12"><el-form-item label="停止报考文号"><el-input v-model="Data.stop_apply_num" /></el-form-item></el-col>
</el-row>

<el-row :gutter="18">
<el-col :span="12"><el-form-item label="停止颁发毕业证日期">
  <el-date-picker
      v-model="Data.stop_diploma_date"
      type="date"
      placeholder="停止颁发毕业证日期">
    </el-date-picker>
  </el-form-item></el-col>
<el-col :span="12"><el-form-item label="停止颁发文号"><el-input v-model="Data.stop_diploma_num" /></el-form-item></el-col>
</el-row>

<el-row :gutter="18">
<el-col :span="12">
  <el-form-item label="专业状态">
    <el-select v-model="Data.major_status" placeholder="请选择专业状态" :value="Data.major_status">
      <el-option label="正常" value="正常"></el-option>
      <el-option label="停考" value="停考"></el-option>
      <el-option label="即将停考" value="即将停考"></el-option>
    </el-select>
  </el-form-item>
</el-col>
<el-col :span="12"><el-form-item label="总学分"><el-input v-model="Data.total_credit" /></el-form-item></el-col>
</el-row>

<el-row :gutter="18">
<el-col :span="12"><el-form-item label="毕业学分"><el-input v-model="Data.graduation_credit" /></el-form-item></el-col>
<el-col :span="12"><el-form-item label="总课程数"><el-input v-model="Data.total_course_number" /></el-form-item></el-col>
</el-row>

<el-row :gutter="18">
<el-col :span="12"><el-form-item label="是否分方向">
  <!-- <el-input v-model="Data.whether_divide_direction" /> -->
  <el-select v-model="Data.whether_divide_direction">
      <el-option label="是" value="1"></el-option>
      <el-option label="不分方向" value="0"></el-option>
    </el-select>
</el-form-item></el-col>
<el-col :span="12"><el-form-item label="专业大类级别"><el-input v-model="Data.major_category_level" :disabled="true"/></el-form-item></el-col>
</el-row>

<el-row :gutter="18">
<el-col :span="12"><el-form-item label="专业大类代码" prop="major_category_code"><el-input v-model="Data.major_category_code" /></el-form-item></el-col>
<el-col :span="12"><el-form-item label="专业大类名称"><el-input v-model="Data.major_category_name" :disabled="true"/></el-form-item></el-col>
</el-row>

<el-row :gutter="18">
<el-col :span="12"><el-form-item label="报考条件说明">
  <el-input
  type="textarea"
  autosize
  placeholder="请输入内容"
  v-model="Data.apply_condition">
</el-input></el-form-item></el-col>
<el-col :span="12"><el-form-item label="毕业条件说明">
  <el-input
  type="textarea"
  autosize
  placeholder="请输入内容"
  v-model="Data.graduation_condition">
</el-input></el-form-item></el-col>
</el-row>

<el-row><el-form-item label="备注">
<el-input
  type="textarea"
  autosize
  placeholder="请输入内容"
  v-model="Data.notes">
</el-input></el-form-item></el-row>
    </el-form>
    <el-row>
      <el-col :margin-left="24">
        <div class="grid-content bg-purple-dark">
          <el-button @click.native.prevent="back">返回</el-button>
          <el-button
            type="success"
            v-if="actionStatus === 'update'"
            @click.native.prevent="updateData"
          >更新</el-button>
          <el-button
            type="success"
            v-if="actionStatus === 'add'"
            :loading="btnLoading"
            @click.native.prevent="addData"
          >添加</el-button>
        </div>
      </el-col>
    </el-row>
  
  </div>
  
</template>

<script>
import { unix2CurrentTime } from '@/utils'
import { add, update } from '@/api/major'
export default {
  created() {
    // console.log('Id=' + this.$route.query.Id)
    this.Data = this.$route.query.data
    this.actionStatus = this.$route.query.actionStatus
    this.dataCheck()
  },
  data() {
    return {
      loading: false,
      btnLoading: false,
      Data: { whether_divide_direction: 1 },
      actionStatus: 'add',
      rules: {
        major_id: [
          { required: true, message: '请输入专业编码', trigger: 'blur' },
          { min: 7, max: 7, message: '7 位编码（1 位字符+6 位数字）', trigger: 'blur' }
        ],
        national_major_code: [
          { min: 7, max: 7, message: '7 位编码（1 位字符+6 位数字）', trigger: 'blur' }
        ],
        major_category_code: [
          { required: true, message: '请输入大类专业编码', trigger: 'blur' },
          { min: 7, max: 7, message: '7 位编码（1 位字符+6 位数字）', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    unix2CurrentTime,
    /**
     * 更新用户资料
     */
    dataCheck() {
      // console.log('this.Data.major_id', this.Data.major_id)
      if (this.Data.major_id != null & this.Data.major_id !== '') {
        return
      }
      this.Data.major_id = 'A100000'
      this.Data.national_major_code = 'A100000'
      this.Data.major_name = '测试专业'
      this.Data.major_brief_introduction = '后续补充介绍'
      this.Data.major_type = '本科段'
      this.Data.education_level = '本科'
      this.Data.exam_type = '面向高校'
      this.Data.main_target_school = '四川大学'
      this.Data.first_exam_id = '001'
      this.Data.approve_num = 'BS001'
      this.Data.stop_freshman_registration_exam_id = '000'
      this.Data.stop_registration_num = '000'
      this.Data.stop_apply_exam_id = '000'
      this.Data.stop_apply_num = '000'
      this.Data.stop_diploma_date = '2022-12-31'
      this.Data.stop_diploma_num = '000'
      this.Data.major_status = '正常'
      this.Data.total_credit = 200
      this.Data.graduation_credit = 110
      this.Data.total_course_number = 55
      this.Data.whether_divide_direction = 1
      this.Data.major_category_code = '1'
      this.Data.apply_condition = '暂无'
      this.Data.graduation_condition = '暂无'
      this.Data.notes = '暂无'
    },
    updateData() {
      this.btnLoading = true
      this.$refs.Data.validate((valid) => {
        if (valid) {
          update(this.Data).then(() => {
            this.$message.success('更新成功')
          }).catch(res => {
            this.$message.error('更新失败')
          })
        } else {
          this.btnLoading = false
          return false
        }
      })
    },
    addData() {
      this.btnLoading = true
      this.$refs.Data.validate((valid) => {
        if (valid) {
          add(this.Data).then(() => {
            this.$message.success('添加成功')
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('添加失败')
            this.btnLoading = false
          })
        } else {
          this.btnLoading = false
          return false
        }
      })
    },
    back() {
      this.$router.go(-1)
    }
  }
}
</script>
