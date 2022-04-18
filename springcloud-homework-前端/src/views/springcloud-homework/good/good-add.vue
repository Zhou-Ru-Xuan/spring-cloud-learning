<template>
  <div class="app-container">
     <el-form label-width="120px" :model="good" :rules="rules" ref="good">
     
      <el-form-item prop="goodName"   label="商品名称">
        <el-input v-model="good.goodName"/>
      </el-form-item>

      <el-form-item prop="price"  label="商品价格">
        <el-input  v-model="good.price"/>
       </el-form-item> 

      <el-form-item label="厂家" prop="venderName">
            <el-select v-model="good.venderName" placeholder="请选择商品类型">
            <el-option  v-for="(type,index) in venders" :key="index" :value="type.name">{{type.name}}</el-option>
            </el-select>
      </el-form-item>

      <!-- <el-form-item prop="norms"  label="商品规格">
        <el-input  v-model="good.norms"/>
      </el-form-item>

      <el-form-item prop="des"  label="商品描述">
        <el-input  v-model="good.des"/>
       </el-form-item> -->

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="submitForm('good')">保存</el-button>
        <el-button @click="resetForm('good')">重置</el-button>
      </el-form-item>


    </el-form>

  </div>
</template>
<script>
import goodApi from '@/api/good/good'
export default {
  data() {
    return {
      saveBtnDisabled:false,  // 保存按钮是否禁用,
      venders:[],
       good:{
        goodName:'',
        // norms: '',
        // des: '',
        price:0,
        venderName:'',
      },
        rules: {
          goodName: [
            { required: true, message: '请输入商品名称', trigger: 'blur' },
            // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
          ],
          price:[
            {required:true,message:'请输入商品价格',trigger:'blur'},
          ],
          venderName: [
            { required: true, message: '请选择商品类型', trigger: 'change' }
          ]
        }
    };
  },
  created() { //页面渲染之前执行
    this.init()
    this.getGoodTypes();
  },
  watch: {  //监听
    $route(to, from) { //路由发生变化，方法就会执行
      this.init()
    }
  },
  methods:{
    init() {
      //判断路径有id值,做修改
      if(this.$route.params && this.$route.params.id) {
          //从路径获取id值
          const id = this.$route.params.id
          //调用根据id查询的方法
          this.getInfo(id)
      } else { //路径没有id值，做添加
        //清空表单
        this.good = {}
      }
    },
    getGoodTypes(){
        goodApi.getGoodTypes()
        .then(response =>{
            this.venders = response.data.venders
            console.log(this.venders)
        })
    },
    //根据商品id查询的方法
    getInfo(id) {
      goodApi.getGood(id)
        .then(response => {
          this.good = response.data.good
          console.log(this.good)
        })
    },
     submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            this.saveOrUpdate();
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
       resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    saveOrUpdate() {
      //判断修改还是添加
      //根据Good是否有id
      if(!this.good.id) {
        //添加
        this.saveGood()
      } else {
        //修改
        this.updateGood()
      }
    },
    //修改商品的方法
    updateGood() {
      goodApi.updateGood(this.good)
        .then(response => {
          //提示信息
          this.$message({
              type: 'success',
              message: '修改成功!'
          });
          //回到列表页面 路由跳转
          this.$router.push({path:'/good/good-info'})
        })
        
    },
    //添加商品的方法
    saveGood() {
                
      goodApi.addGood(this.good)
        .then(response => {//添加成功
          //提示信息
          this.$message({
              type: 'success',
              message: '添加成功!'
          });

        
          //回到列表页面 路由跳转
          this.$router.push({path:'/good/good-info'})
        })
        

 
    }

  }
}
</script>
