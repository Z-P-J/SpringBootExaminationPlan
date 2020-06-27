<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form
        v-loading.body="loading"
        :model="tmpData"
        ref="tmpData"
        :rules="rules"
        label-width="115px"
      >
      <el-row :gutter="18">
        <el-col :span="12">
        <el-form-item label="专业编码" prop="major_id">
          <el-input v-model="tmpData.major_id" v-if="actionStatus === 'add'"/>
          <el-input v-model="tmpData.major_id" v-else="" :disabled="true"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
        <el-form-item label="国家专业代码" prop="national_major_code">
          <el-input v-model="tmpData.national_major_code" @focus="getNationMajorList"/>
        </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="12">
      <el-form-item label="专业名称"><el-input v-model="tmpData.major_name" /></el-form-item>
        </el-col>
        <el-col :span="12">
      <el-form-item label="专业简介"><el-input v-model="tmpData.major_brief_introduction" /></el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
      <el-col :span="8">
        <el-form-item label="专业类型">
          <el-select v-model="tmpData.major_type" placeholder="请选择专业类型" :value="tmpData.major_type">
            <el-option label="基础科段" value="基础科段"></el-option>
            <el-option label="专科" value="专科"></el-option>
            <el-option label="本科段" value="本科段"></el-option>
            <el-option label="独立本科段" value="独立本科段"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="学历层次">
          <el-select v-model="tmpData.education_level" placeholder="请选择学历层次" :value="tmpData.education_level">
            <el-option label="专科" value="专科"></el-option>
            <el-option label="本科" value="本科"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="8">
        <el-form-item label="开考形式">
          <el-select v-model="tmpData.exam_type" placeholder="请选择开考形式" :value="tmpData.exam_type">
            <el-option label="面向高校" value="面向高校"></el-option>
            <el-option label="面向社会" value="面向社会"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      </el-row>

      <el-row :gutter="18">
      <el-col :span="12"><el-form-item label="首次开考考次" prop="first_exam_id"><el-input v-model="tmpData.first_exam_id" /></el-form-item></el-col>
      <el-col :span="12"><el-form-item label="批准文号"><el-input v-model="tmpData.approve_num" /></el-form-item></el-col>
      </el-row>

      <el-row :gutter="18">
      <el-col :span="12"><el-form-item label="停止新生注册考次" prop="stop_freshman_registration_exam_id"><el-input v-model="tmpData.stop_freshman_registration_exam_id"/></el-form-item></el-col>
      <el-col :span="12"><el-form-item label="停止注册文号"><el-input v-model="tmpData.stop_registration_num" /></el-form-item></el-col>
      </el-row>

      <el-row :gutter="18">
      <el-col :span="12"><el-form-item label="停止报考考次" prop="stop_apply_exam_id"><el-input v-model="tmpData.stop_apply_exam_id" /></el-form-item></el-col>
      <el-col :span="12"><el-form-item label="停止报考文号"><el-input v-model="tmpData.stop_apply_num" /></el-form-item></el-col>
      </el-row>

      <el-row :gutter="18">
      <el-col :span="12"><el-form-item label="停止颁发毕业证日期">
        <el-date-picker
            v-model="tmpData.stop_diploma_date"
            type="date"
            placeholder="停止颁发毕业证日期">
          </el-date-picker>
        </el-form-item></el-col>
      <el-col :span="12"><el-form-item label="停止颁发文号"><el-input v-model="tmpData.stop_diploma_num" /></el-form-item></el-col>
      </el-row>

      <el-row :gutter="18">
      <el-col :span="12">
        <el-form-item label="专业状态">
          <el-select v-model="tmpData.major_status" placeholder="请选择专业状态" :value="tmpData.major_status">
            <el-option label="正常" value="正常"></el-option>
            <el-option label="停考" value="停考"></el-option>
            <el-option label="即将停考" value="即将停考"></el-option>
          </el-select>
        </el-form-item>
      </el-col>
      <el-col :span="12"><el-form-item label="总学分"><el-input v-model="tmpData.total_credit"  type="number"/></el-form-item></el-col>
      </el-row>

      <el-row :gutter="18">
      <el-col :span="12"><el-form-item label="毕业学分"><el-input v-model="tmpData.graduation_credit"  type="number"/></el-form-item></el-col>
      <el-col :span="12"><el-form-item label="总课程数"><el-input v-model="tmpData.total_course_number" type="number"/></el-form-item></el-col>
      </el-row>

      <el-row :gutter="18">
      <el-col :span="12"><el-form-item label="是否分方向">
        <!-- <el-input v-model="tmpData.whether_divide_direction" /> -->
        <el-select v-model="tmpData.whether_divide_direction">
            <el-option label="是" value="1"></el-option>
            <el-option label="不分方向" value="0"></el-option>
          </el-select>
      </el-form-item></el-col>
      <el-col :span="12"><el-form-item label="专业大类级别"><el-input v-model="tmpData.major_category_level" :disabled="true"/></el-form-item></el-col>
      </el-row>

      <el-row :gutter="18">
      <el-col :span="12"><el-form-item label="专业大类代码" prop="major_category_code"><el-input v-model="tmpData.major_category_code" @focus="getCategoryMajorList"/></el-form-item></el-col>
      <el-col :span="12"><el-form-item label="专业大类名称"><el-input v-model="tmpData.major_category_name" :disabled="true"/></el-form-item></el-col>
      </el-row>

      <el-row :gutter="18">
      <el-col :span="12"><el-form-item label="报考条件说明">
        <el-input
        type="textarea"
        autosize
        placeholder="请输入内容"
        v-model="tmpData.apply_condition">
      </el-input></el-form-item></el-col>
      <el-col :span="12"><el-form-item label="毕业条件说明">
        <el-input
        type="textarea"
        autosize
        placeholder="请输入内容"
        v-model="tmpData.graduation_condition">
      </el-input></el-form-item></el-col>
      </el-row>

      <el-row><el-form-item label="备注">
      <el-input
        type="textarea"
        autosize
        placeholder="请输入内容"
        v-model="tmpData.notes">
      </el-input></el-form-item></el-row>
          </el-form>
          <el-row>
            <el-col :margin-left="24">
              <div class="grid-content bg-purple-dark">
                <el-button @click.native.prevent="back">返回</el-button>
                <el-button
                  type="success"
                  v-if="actionStatus === 'add'"
                  :loading="btnLoading"
                  @click.native.prevent="addData"
                >添加</el-button>
                <el-button
                  type="success"
                  v-else
                  :loading="btnLoading"
                  @click.native.prevent="updateData"
                >更新</el-button>
              </div>
            </el-col>
          </el-row>
      </div>
    
  <el-dialog :visible.sync="dialogFormVisible" v-if="operationStatus === 'nation'">
    <el-table
      :data="nationDataList"
      highlight-current-row
    >
      <el-table-column label="专业全国编码" prop="national_major_code"/>
      <el-table-column label="名称" prop="major_name" />
      <el-table-column label="学历层次" prop="education_level"/>
      <el-table-column label="选择" align="center">
        <template slot-scope="scope">
          <el-button
            type="success"
            size="mini"
            @click.native.prevent="updateNationMajorData(scope.row)"
          >选择此项</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- <div slot="footer" class="dialog-footer">
      <el-button @click.native.prevent="dialogFormVisible = false">取消</el-button>
    </div> -->
  </el-dialog>
   <el-dialog :visible.sync="dialogFormVisible" v-if="operationStatus === 'category'">
    <el-table
      :data="categoryDataList"
      highlight-current-row
    >
      <el-table-column label="专业大类编码" align="center" prop="category_code" />
      <el-table-column label="名称" align="center" prop="major_category_name" />
      <el-table-column sortable label="学科等级" align="center" prop="major_category_level"/>
      <el-table-column label="选择" align="center">
        <template slot-scope="scope">
          <el-button
            type="success"
            size="mini"
            @click.native.prevent="updateCategoryMajorData(scope.row)"
          >选择此项</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- <div slot="footer" class="dialog-footer">
      <el-button @click.native.prevent="dialogFormVisible = false">取消</el-button>
    </div> -->
  </el-dialog>
    </div>
  
</template>

<script>
import { unix2CurrentTime } from '@/utils'
import { add, update } from '@/api/major'
import { nationList, categoryList } from '@/api/major'
export default {
  created() {
    // console.log('Id=' + this.$route.query.Id)
    this.tmpData = this.$route.query.data
    this.actionStatus = this.$route.query.actionStatus
    this.dataCheck()
  },
  data() {
    return {
      loading: false,
      btnLoading: false,
      tmpData: { whether_divide_direction: 1 },
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
        ],
        first_exam_id: [
          { required: true, message: '请输入大类专业编码', trigger: 'blur' },
          { min: 3, max: 3, message: '3 位数字编码（例：171-17 年 4 月考试）', trigger: 'blur' }
        ],
        stop_freshman_registration_exam_id: [
          { required: true, message: '请输入大类专业编码', trigger: 'blur' },
          { min: 3, max: 3, message: '3 位数字编码（例：171-17 年 4 月考试）', trigger: 'blur' }
        ],
        stop_apply_exam_id: [
          { required: true, message: '请输入大类专业编码', trigger: 'blur' },
          { min: 3, max: 3, message: '3 位数字编码（例：171-17 年 4 月考试）', trigger: 'blur' }
        ]
      },
      listQuery: {
        page: 1, // 页码
        size: 9 // 每页数量
      },
      operationStatus: 'nation',
      dialogFormVisible: false,
      nationDataList: [],
      categoryDataList: []
    }
  },
  methods: {
    unix2CurrentTime,
    /**
     * 更新用户资料
     */
    dataCheck() {
      // console.log('this.tmpData.major_id', this.tmpData.major_id)
      if (this.tmpData.major_id != null & this.tmpData.major_id !== '') {
        return
      }
      this.tmpData.major_id = 'A100000'
      this.tmpData.national_major_code = 'A100000'
      this.tmpData.major_name = '测试专业'
      this.tmpData.major_brief_introduction = '后续补充介绍'
      this.tmpData.major_type = '本科段'
      this.tmpData.education_level = '本科'
      this.tmpData.exam_type = '面向高校'
      this.tmpData.main_target_school = '四川大学'
      this.tmpData.first_exam_id = '001'
      this.tmpData.approve_num = 'BS001'
      this.tmpData.stop_freshman_registration_exam_id = '000'
      this.tmpData.stop_registration_num = '000'
      this.tmpData.stop_apply_exam_id = '000'
      this.tmpData.stop_apply_num = '000'
      this.tmpData.stop_diploma_date = '2022-12-31'
      this.tmpData.stop_diploma_num = '000'
      this.tmpData.major_status = '正常'
      this.tmpData.total_credit = 200
      this.tmpData.graduation_credit = 110
      this.tmpData.total_course_number = 55
      this.tmpData.whether_divide_direction = 1
      this.tmpData.major_category_code = 'A100000'
      this.tmpData.apply_condition = '暂无'
      this.tmpData.graduation_condition = '暂无'
      this.tmpData.notes = '暂无'
    },
    updateData() {
      this.btnLoading = true
      this.$refs.tmpData.validate((valid) => {
        if (valid) {
          update(this.tmpData).then(() => {
            this.$message.success('更新成功')
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('更新失败')
          })
        } else {
          this.$message.error('请检查输入格式')
          this.btnLoading = false
          return false
        }
      })
    },
    addData() {
      this.btnLoading = true
      this.$refs.tmpData.validate((valid) => {
        if (valid) {
          add(this.tmpData).then(() => {
            this.$message.success('添加成功')
            this.btnLoading = false
          }).catch(res => {
            this.$message.error('添加失败')
            this.btnLoading = false
          })
        } else {
          this.$message.error('请检查输入格式')
          this.btnLoading = false
          return false
        }
      })
    },
    back() {
      this.$router.go(-1)
    },
    getNationMajorList() {
      this.dialogFormVisible = true
      this.operationStatus = 'nation'
      nationList(this.listQuery).then(response => {
        this.nationDataList = response.data.list
      }).catch(res => {
        this.$message.error('加载列表失败')
      })
    },
    updateNationMajorData(item) {
      // console.log(item)
      this.tmpData.national_major_code = item.national_major_code
      // console.log(this.tmpData)
      this.dialogFormVisible = false
      this.$message.success('选择成功')
    },
    getCategoryMajorList() {
      this.dialogFormVisible = true
      this.operationStatus = 'category'
      categoryList(this.listQuery).then(response => {
        this.categoryDataList = response.data.list
      }).catch(res => {
        this.$message.error('加载列表失败')
      })
    },
    updateCategoryMajorData(item) {
      console.log(item)
      this.tmpData.major_category_code = item.category_code
      // console.log(this.tmpData)
      this.dialogFormVisible = false
      this.$message.success('选择成功')
    }
  }
}
</script>
