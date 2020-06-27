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
        <el-col :span="18">
          <el-form-item label="申请内容如下："></el-form-item>
        </el-col>
      </el-row>
     
    <!-- //审核部分 -->
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

      <el-form-item  v-if="ShowStatus === 'new'" label="专业批准文号：">
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

          <!-- <el-button type="danger" 
          @click.native.prevent="$refs['tmpData'].resetFields()"
          >重置</el-button> -->

          <el-button  type="primary" 
          @click.native.prevent="update"
          >确认</el-button>
  </div>
</template>

<script>
import { unix2CurrentTime } from '@/utils'
import { list , remove, update, addData, add } from '@/api/approve'


export default {
  created() {
    this.tmpData = this.$route.query.data
    console.log('tmpData=' + JSON.stringify(this.tmpData))
    this.changeShow()
  },
  data() {
    return {
      ShowStatus:'adjust',
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
      }
      if(this.tmpData.approve_name === '续办专业'){
       this.ShowStatus = "continue";
      }
      if(this.tmpData.approve_name === '扩办专业'){
        this.ShowStatus = "extend";
      }
      if(this.tmpData.approve_name === '新专业'){
        this.ShowStatus = "new";
      }
      if(this.tmpData.approve_status === '已审批'){
        this.ShowStatus = "Ischeck";
      }
    },

  // /**
  //    * 进行申请表添加
  //    */
  //   addData() {
  //       this.btnLoading = true
  //       this.tmpData = this.dataList[index]
  //       console.log("提交表单"+JSON.stringify(this.tmpData))
  //       add(this.tmpData).then(() => { 
  //           this.$message.success('申请表提交成功！')
  //           this.btnLoading = false
  //       }).catch(res => {
  //           this.$message.error('申请表提交失败！')
  //           this.btnLoading = false
  //       })
  //   },
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
     
    }
  }
}
</script>
