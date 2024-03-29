import axios from "axios";

// axios 객체 생성
export default axios.create({
  // baseURL: "http://70.12.50.89:9999/",
  baseURL: "http://localhost:9999/",
  headers: {
    "Content-Type": "application/json;charset=utf-8",
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "DELETE, POST, GET, OPTIONS",
    "Access-Control-Allow-Headers":
      "Content-Type, Authorization, X-Requested-With",
  },
});
