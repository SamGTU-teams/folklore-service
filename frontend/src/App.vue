<template>
  <div id="nav">
    <div class="container">
      <div class="row hide-on-small-only hide-on-med-only">
        <div id="Logo">
          LOGO
        </div>
        <div id="MenuPuncts">
          <router-link to="/login"
            ><div class="MenuElement">Войти</div></router-link
          >
          <router-link to="/profile"
            ><div class="MenuElement">Профиль</div></router-link
          >
          <router-link to="/afisha"
            ><div class="MenuElement">Афиша</div></router-link
          >
          <router-link to="/"
            ><div class="MenuElement">Главная</div></router-link
          >
        </div>
      </div>
      <div class="row show-on-medium hide-on-med-and-up" id="MenuGrid">
        <div class="col s12 ">LOGO</div>
        <router-link to="/"
          ><div class="col offset-m4 m4 offset-s4 s4">Главная</div></router-link
        >
        <router-link to="/afisha"
          ><div class="col offset-m4 m4 offset-s4 s4">Афиша</div></router-link
        >
        <router-link to="/profile"
          ><div class="col offset-m4 m4 offset-s4 s4">Профиль</div></router-link
        >
        <router-link to="/login"
          ><div class="col offset-m4 m4 offset-s4 s4">Войти</div></router-link
        >
      </div>
    </div>
  </div>
  <router-view />
</template>
<script>
import { defineComponent } from "vue";
import M from "materialize-css";
export default defineComponent({
  name: "App",
  mounted() {
    M.AutoInit();
  },
});
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
#nav {
  margin-top: 10px;
}
.router-link-exact-active > div,
.router-link-active > div {
  border-radius: 15px;
  background-color: #fff;
  color: #2b3f8d;
}
.row {
  font-size: 18px;
  color: #fff;
}
#Logo {
  width: 35%;
  float: left;
  text-align: left;
}
#MenuPuncts {
  width: 65%;
  float: right;
}
.MenuElement {
  color: #fff;
  margin-left: 15px;
  padding: 0px 15px;
  float: right;
}
#MenuGrid {
  color: #fff;
}
.col {
  height: 100%;
  color: #fff;
}
body {
  background-color: #2b3f8d;
}
</style>
