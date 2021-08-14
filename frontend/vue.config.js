// vue.config.js
module.exports = {
    // proxy all webpack dev-server requests starting with /api
    // to our Spring Boot backend (localhost:8081) using http-proxy-middleware
    // see https://cli.vuejs.org/config/#devserver-proxy
    // npm install --save http-proxy-middleware
    devServer: {
      proxy: {
        '^/api/*': {
          target: 'https://rassafel-folklore-service.herokuapp.com/',
          // target: 'http://localhost:8081', 
          // ws: true,
          changeOrigin: true
        }
      }
    },
    // Change build paths to make them Maven compatible
    // see https://cli.vuejs.org/config/
    outputDir: 'target/dist',
    assetsDir: 'static'
  };