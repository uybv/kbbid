<template>
  <div id="app">
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand to="/">KBBID</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse">
        <template #default="{ expanded }">
          <b-icon v-if="expanded" icon="chevron-bar-up"></b-icon>
          <b-icon v-else icon="chevron-bar-down"></b-icon>
        </template>
      </b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav v-if="isLogged()">
          <!--b-nav-item to="/">Home</b-nav-item>
          <b-nav-item to="/about">About</b-nav-item-->
          <b-nav-item @click="logout">Logout</b-nav-item>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <b-container fluid class="py-3">
      <router-view/>
    </b-container>
  </div>
</template>

<script>
import api from './api'
export default {
  methods: {
    isLogged() {
      return api.isLogged();
    },
    logout() {
      api.auth.logout();
      this.$router.push('/auth')
    }
  }
}
</script>