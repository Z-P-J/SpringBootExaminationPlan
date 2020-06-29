<template>
  <div class="app-container">
    <el-form
      v-loading.body="loading"
      :model="tmpData"
      ref="tmpData"
      label-width="115px"
    >
     <el-row >
      <el-col :span="12"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="12"><div class="grid-content bg-purple-light"></div></el-col>
    </el-row>
      <el-row :gutter="18">
        <el-col :span="9">
          <el-form-item label="申请表编码:" prop="approve_id">
          <span >{{ tmpData.approve_id }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="申请表名称:" prop="approve_name">
            <span>{{ tmpData.approve_name }}</span>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="18">
        <el-col :span="9">
          <el-form-item label="申请院校编码:" prop="school_id">
            <span>{{ tmpData.school_id }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="申请院校名称:" prop="school_name">
            <span >{{ tmpData.school_name }}</span>
          </el-form-item>
        </el-col>
      </el-row>

     <el-row :gutter="18">
        <el-col :span="9">
          <el-form-item label="申请表创建时间:" prop="apply_time">
            <span >{{ tmpData.apply_time }}</span>
          </el-form-item>
        </el-col>
        <el-col :span="9">
          <el-form-item label="申请表状态:" prop="approve_status">
            <span >{{ tmpData.approve_status }}</span>
          </el-form-item>
        </el-col>
    </el-row>

        <el-row :gutter="18">
          <el-col :span="18" v-if="this.ShowStatus === 'adjust'">
            <el-form-item label="专业调整如下："></el-form-item>
          </el-col>
          <el-col :span="18" v-if="this.ShowStatus === 'continue'">
            <el-form-item  label="续办专业如下："></el-form-item>
          </el-col>
          <el-col :span="18" v-if="this.ShowStatus === 'extend'">
            <el-form-item label="扩办专业如下："></el-form-item>
          </el-col>
          <el-col :span="18" v-if="this.ShowStatus === 'new'">
            <el-form-item label="新专业如下："></el-form-item>
          </el-col>
        </el-row>
        <el-table
          :data="ApproveMajorDataList"
          border
          fit
          highlight-current-row
          v-if="this.ShowStatus === 'continue' || this.ShowStatus === 'extend'">
          <el-table-column label="专业编码" align="center" prop="major_id" width="500" />
          <el-table-column label="专业名称" align="center" prop="major_name"/>
        </el-table>
                                 <!-- 专业调整 -->
        <el-table
          :data="ApproveCourseDataList"
          border
          fit
          highlight-current-row
          v-if="this.ShowStatus === 'adjust'">
          <el-table-column label="专业编码" align="center" prop="major_id" width="150" />
          <el-table-column label="课程编码" align="center" prop="course_name" width="150"/>
          <el-table-column label="课程名称" align="center" prop="course_id" width="150" />
          <el-table-column label="替换课程编码" align="center" prop="new_course_name" width="150"/>
          <el-table-column label="替换课程名称" align="center" prop="new_course_id" width="150" />
          <el-table-column label="课程类型" align="center" prop="course_property"/>
        </el-table>
                                 <!-- 专业调整结束 -->
     
    <!-- 新专业显示 -->
        <el-form
          v-loading.body="loading"
          :model="ApproveNewData"
          ref="ApproveNewData"
          label-width="115px"
          v-if="this.ShowStatus === 'new'"
        >
          <el-row :gutter="18" >
            <el-row :gutter="18">
              <el-col :span="12">
                <el-form-item label="专业编码" prop="major_id">
                  <el-input v-model="ApproveNewData.major_id"
                  :disabled="true" />
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="国家专业代码" prop="national_major_code">
                  <el-input v-model="ApproveNewData.national_major_code"
                  :disabled="true" />
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="18">
              <el-col :span="12">
              <el-form-item label="专业名称"><el-input v-model="ApproveNewData.major_name" :disabled="true"/></el-form-item>
              </el-col>
              <el-col :span="12">
              <el-form-item label="总学分"><el-input v-model="ApproveNewData.total_credit"  type="number" :disabled="true"/></el-form-item></el-col>
            </el-row>
            <el-row :gutter="18">
              <el-col :span="8">
                <el-form-item label="专业类型">
                  <el-input v-model="ApproveNewData.major_type" :disabled="true">
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="学历层次">
                  <el-input v-model="ApproveNewData.education_level" :disabled="true">
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="8">
                <el-form-item label="开考形式">
                  <el-input v-model="ApproveNewData.exam_type"  :disabled="true">
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row :gutter="18">
              <el-col :span="12"><el-form-item label="毕业学分"><el-input v-model="ApproveNewData.graduation_credit"  type="number" :disabled="true" /></el-form-item></el-col>
              <el-col :span="12"><el-form-item label="总课程数"><el-input v-model="ApproveNewData.total_course_number" type="number" :disabled="true"/></el-form-item></el-col>
            </el-row>
            <el-row :gutter="18">
              <el-col :span="12"><el-form-item label="是否分方向">
                <el-input v-model="ApproveNewData.whether_divide_direction" :disabled="true">
                </el-input></el-form-item>
              </el-col>
              <el-col :span="12"><el-form-item label="专业大类级别"><el-input v-model="ApproveNewData.major_category_level" :disabled="true"/></el-form-item></el-col>
            </el-row>
            <el-row :gutter="18">
              <el-col :span="12"><el-form-item label="专业大类代码" prop="major_category_code"><el-input v-model="ApproveNewData.major_category_code" :disabled="true"/></el-form-item></el-col>
              <el-col :span="12"><el-form-item label="专业大类名称"><el-input v-model="ApproveNewData.major_category_name" :disabled="true"/></el-form-item></el-col>
            </el-row>
            <el-row :gutter="18">
              <el-col :span="12">
                <el-form-item label="报考条件说明">
                  <el-input
                  type="textarea"
                  autosize
                  placeholder="请输入内容"
                  v-model="ApproveNewData.apply_condition"
                  :disabled="true">
                  </el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="毕业条件说明">
                  <el-input
                  type="textarea"
                  autosize
                  placeholder="请输入内容"
                  v-model="ApproveNewData.graduation_condition"
                  :disabled="true">
                  </el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-form-item label="备注">
                  <el-input
                    type="textarea"
                    autosize
                    placeholder="请输入内容"
                    v-model="ApproveNewData.notes"
                    :disabled="true">
                  </el-input>
              </el-form-item>
            </el-row> 
          </el-row> 
        </el-form>
                        <!-- 新专业显示结束 -->
    <!-- //审核部分 -->
      <el-row :gutter="18">
        <el-col :span="18" >
          <el-form-item  >
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="18">
        <el-col :span="18" >
          <el-form-item label="审核情况如下：" >
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item label="审核意见：" prop="approve_comment">
        <el-col :span="18">
          <el-input
          type="textarea"
          :autosize="{ minRows: 2, maxRows: 4}"
          placeholder="请输入审核意见"
          v-model="tmpData.approve_comment"
          >
          </el-input>
        </el-col>
      </el-form-item>

      <el-form-item  v-if="ShowStatus === 'new'" label="批准文号：">
        <el-col :span="18">
          <el-input  
          placeholder="请输入新专业的批准文号" 
          v-model="tmpData.approve_num"/>
        </el-col>
      </el-form-item>

      <el-form-item label="审核人签名：">
        <el-col :span="8">
          <el-input  
          placeholder="请输入审核人姓名" 
          v-model="tmpData.approver"/>
        </el-col>
      </el-form-item>

    </el-form>
                 <!-- //顶部按钮操作 -->
          <el-button 
          @click="$router.back(-1)"
          >返回</el-button>

          <el-button  type="primary" 
          @click.native.prevent="update"
          >确认</el-button>
  </div>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
import { list , remove, update, addData, add } from '@/api/approve'
import { getApproveMajorDataList, getApproveNewData, getApproveCourseDataList } from '@/api/approve_major'

export default {
  created() {
    this.tmpData = this.$route.query.data
    console.log('tmpData=' + JSON.stringify(this.tmpData))
    console.log('aaaaaShowStatus=' + JSON.stringify(this.tmpData.approve_name))
    this.changeShow()
   
  },
  data() {
    return {
      ShowStatus:'',
      ApproveNewData:{},
      ApproveMajorDataList: [],
      ApproveCourseDataList:[],
      loading: false,
      btnLoading: false,
      toUpdate: false,
      tmpData: {
        approve_id: '',
        approve_name: '',
        apply_time:'',
        school_id:'',
        school_name:'',
        approve_time:'',
        approve_comment:'',
        approve_status: '',
        approver: '',
        approve_num:'',
      },
    }
  },

  methods: {
    unix2CurrentTime,
    changeShow(){
      if(this.tmpData.approve_name === '专业调整'){
        this.ShowStatus = "adjust";
        this.getApproveCourseDataList(this.tmpData.approve_id)
      }
      if(this.tmpData.approve_name === '续办专业'){
       this.ShowStatus = "continue";
        this.getApproveMajorDataList(this.tmpData.approve_id)
      }
      if(this.tmpData.approve_name === '扩办专业'){
        this.ShowStatus = "extend";
         this.getApproveMajorDataList(this.tmpData.approve_id)
      }
      if(this.tmpData.approve_name === '新专业'){
        this.ShowStatus = "new";
        this.getApproveNewData(this.dataList.approve_id)
    
      }
    },

    /**
     * 进行审批
     */
    update() {
        this.btnLoading = true
        this.tmpData.approve_status = "已审批"
        update(this.tmpData).then(() => {
          this.$message.success('审批成功！')
          this.btnLoading = false
          this.$router.back(-1)
        }).catch(res => {
          this.$message.error('审批提交失败！')
          this.btnLoading = false
        })
    },
    /**
     * 更新用户资料
     */
    updateDetail() {
     
    },
    getApproveMajorDataList(id){
      console.log('id',id)
      getApproveMajorDataList(id).then(response => {
        this.ApproveMajorDataList = response.data.list
        console.log(' this.ApproveMajorDataList'+ JSON.stringify(this.ApproveMajorDataList))
      }).catch(res => {
        
      })
    },
    getApproveCourseDataList(id){
      console.log('id',id)
      getApproveCourseDataList(id).then(response => {
        this.ApproveCourseDataList = response.data.list
        console.log(' this.ApproveCourseDataList'+ JSON.stringify(this.ApproveCourseDataList))
      }).catch(res => {
        
      })
    },
     getApproveNewData(id){
      console.log('id',id)
      getApproveNewData(id).then(response => {
        this.ApproveNewData = response.data
        console.log(' this.ApproveNewData'+ JSON.stringify(response))
      }).catch(res => {
        
      })
    },
  }
}
</script>
