<template>
  <div id="nav">
    <div class="container">
      <div class="row hide-on-small-only hide-on-med-only">
        <div id="Logo">
          LOGO
        </div>
        <div id="MenuPuncts">                  
          <router-link to="/login"><div class="MenuElement">Войти</div></router-link>
          <router-link to="/profile"><div class="MenuElement">Профиль</div></router-link>
          <router-link to="/afisha"><div class="MenuElement">Афиша</div></router-link>
          <router-link to="/"><div class="MenuElement">Главная</div></router-link> 
        </div>
      </div>
      <div class="row show-on-medium hide-on-med-and-up" id="MenuGrid">
        <div class="col s12 ">LOGO</div>
        <router-link to="/"><div class="col offset-m4 m4 s12">Главная</div></router-link> 
        <router-link to="/afisha"><div class="col offset-m4 m4 s12">Афиша</div></router-link> 
        <router-link to="/profile"><div class="col offset-m4 m4 s12">Профиль</div></router-link> 
        <router-link to="/login"><div class="col offset-m4 m4 s12">Войти</div></router-link> 
      </div>
    </div>
  </div>
  <router-view/>
</template>
<script>
  import { defineComponent } from 'vue'
  import M from 'materialize-css'
  export default defineComponent({
    name: "App",
    mounted () {
      M.AutoInit()
    },
  })
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
}
#nav {
  margin-top: 30px;
}
.router-link-exact-active>div,.router-link-active>div{
  border-radius: 15px;
  background-color: #FFF;
  color: #51495C;
}
.row{
    font-size: 18px;
    color: #FFF;
  }
#Logo{
    width:35%;
    float: left;
    text-align: left;
  }
#MenuPuncts{
    width: 65%;
    float: right;
  }
.MenuElement{
    color: #FFF;
    margin-left: 15px;
    padding: 0px 15px;
    float: right;
  }
#MenuGrid{
    color: #FFF;
  }
.col{
    height: 100%;
    color: #FFF;
  }
body{
    background-color: #444444;
    }
</style>
