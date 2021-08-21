import axios, { AxiosResponse } from "axios";

const axiosApi = axios.create({
  baseURL: `/api`,
  timeout: 3000,
  headers: {
    "Content-Type": "application/json",
    // CORS params
    // "Access-Control-Allow-Origin": [
    //   "http://localhost:8080",
    //   "http://localhost:8081",
    //   "https://rassafel-folklore-service.herokuapp.com/",
    // ],
    // "Access-Control-Allow-Methods": "GET,POST,DELETE,PUT,OPTIONS",
    // "Access-Control-Allow-Headers": "*",
    // "Access-Control-Allow-Credentials": true,
  },
});


export default axiosApi;
export { AxiosResponse };
