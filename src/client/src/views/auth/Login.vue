<template>
  <div class="login">
    <b-row>
      <b-col cols="12">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 class="h2">Login</h1>
        </div>
        <b-alert show variant="danger" v-for="error in errors" :key="error">{{error}}</b-alert>
        <b-form @submit="onSubmit" v-if="show">
          <b-form-group label="Username" label-for="auth-name">
            <b-form-input id="auth-name" v-model="form.username" type="text" placeholder="" required></b-form-input>
          </b-form-group>
          <b-form-group label="Password" label-for="auth-password">
            <b-form-input id="auth-password" v-model="form.password" type="password" placeholder="" required></b-form-input>
          </b-form-group>
          <div class="text-center">
            <b-button class="ml-1 mr-1" type="submit" variant="primary">Submit</b-button>
          </div>
        </b-form>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import api from '../../api'
export default {
  data() {
    return {
      form: {
        username: '',
        password: ''
      },
      show: false,
      errors: []
    }
  },
  mounted() {
    this.show = true;
    if (api.isLogged()) {
      this.$router.push('/bid')
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault()
      this.errors = [];
      api.auth.login(this.form.username, this.form.password)
        .then(() => {
          this.$log.debug('Logged!');
          this.$router.push('/bid')
        })
        .catch((error) => {
          if (error.response && error.response.data && error.response.data.message) { 
            this.errors.push(error.response.data.message);
          } else {
            this.errors.push('Invalid username/password!')
          }
        });
    }
  }
}
</script>
