<template>
  <div id="nav">
    <div class="container">
      <div id="header" class="row hide-on-small-only hide-on-med-only valign-wrapper">
        <div id="Logo" class="col l1 xl1 Logo">
          LOGO
        </div>
        <div id="MenuPuncts" class="col offset-l2 offset-xl2 l9 xl9 right-align ">
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
import Vue, { defineComponent } from "vue";
import * as Materialize from "materialize-css";

export default defineComponent({
  name: "App",
  mounted() {
    Materialize.AutoInit();
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
#header {
  padding: 0.75rem 0;
}
.Logo {
}
#Logo {
  /* transform: translate(0%, 0%);
  position: relative;
  top: 50%; */

  height: 100%;
  /* width: 35%; */
  float: left;
  padding: 0 0.75rem;
  text-align: center;
  font-weight: bold;
  font-size: 2em;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}
#MenuPuncts {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  /* width: 65%; */
  float: right;
  font-size: 1em;
  padding: 0;
 
}
.MenuElement {
  color: #fff;
  margin-left: 15px;
  padding: 0px 15px;
  float: right;
  /* transform: translate(0%, -50%);
  position: relative;
  top: 50%; */
}
#MenuGrid {
  color: #fff;
}
.col {
  height: 100%;
  color: #fff;
}
body {
  /* background-color: #201F1C;
  background-color: #fcd56b;
  background-color: #00028B;
  background-color: #FEFBF7; */
  background-color: #BACEE2;
}
#nav {
  /* background-color: #BACEE2;
  background-color: #FEFBF7;
  background-color: #00028B; */
  background-color: #201F1C;
}
</style>
