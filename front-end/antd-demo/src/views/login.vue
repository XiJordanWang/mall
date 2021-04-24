<template>
  <div class="login">
    <h1>测试平台</h1>
    <a-form-model ref="ruleForm" :model="form" :rules="rules">
      <h5>登录</h5>
      <a-form-model-item prop="name">
        <a-input v-model="form.name" placeholder="请输入邮箱/手机号" />
      </a-form-model-item>
      <a-form-model-item prop="password">
        <a-input v-model="form.password" placeholder="请输入密码" />
      </a-form-model-item>
      <a-form-model-item prop="code" class="identify">
        <a-input v-model="form.code" placeholder="请输入验证码" />
        <Identify :identifyCode="identifyCode" @click="refreshCode" />
      </a-form-model-item>
      <a-form-model-item>
        <a-button type="primary" @click="onSubmit" block> 提交 </a-button>
        <router-link :to="{ name: 'Password' }" style="float: right" class="pointer">忘记密码</router-link>
      </a-form-model-item>
    </a-form-model>
  </div>
</template>

<script>
import Identify from '@/components/identify'

export default {
  name: 'Login', //登录
  components: { Identify },
  data() {
    var validateCode = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('验证码为空'))
        return false
      } else if (value !== this.identifyCode) {
        callback(new Error('验证码不正确'))
        return false
      } else {
        callback()
      }
    }
    return {
      identifyCodes: '1234567890',
      identifyCode: '',
      form: {
        name: '',
        password: '',
        code: ''
      },
      rules: {
        name: { required: true, message: '请输入邮箱/手机号', trigger: 'blur' },
        password: { required: true, message: '请输入密码', trigger: 'blur' },
        code: [{ validator: validateCode, trigger: 'blur' }]
      }
    }
  },
  mounted() {
    this.identifyCode = ''
    this.makeCode(this.identifyCodes, 4)
  },
  methods: {
    onSubmit() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          this.$router.push('/home')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm() {
      this.$refs.ruleForm.resetFields()
    },
    // 验证码相关
    randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min)
    },
    refreshCode() {
      this.identifyCode = ''
      this.makeCode(this.identifyCodes, 4)
    },
    makeCode(o, l) {
      for (let i = 0; i < l; i++) {
        this.identifyCode += this.identifyCodes[this.randomNum(0, this.identifyCodes.length)]
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.login {
  width: 100%;
  height: 100%;
  background: #eeeeee;
  overflow: auto;
  h1 {
    font-size: 40px;
    padding: 60px 0 0 40px;
  }
  .ant-form {
    width: 440px;
    border-radius: 5px;
    background: #fff;
    margin: auto;
    padding: 18px 40px;
    .ant-dropdown-trigger {
      float: right;
      margin-right: -20px;
    }
    h5 {
      font-size: 24px;
      text-align: center;
      margin-bottom: 25px;
    }
  }
}
</style>
<style lang="scss">
// 验证码样式
.login .identify .ant-form-item-children {
  display: flex;
  align-items: center;
  .ant-input {
    flex: 1;
    margin-right: 10px;
  }
}
</style>