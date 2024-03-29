import axios from "axios";

// local vue api axios instance
function apiInstance() {
  const instance = axios.create({
    // baseURL: "http://70.12.50.89:9999/", // "http://localhost:9999/"
    baseURL: "http://localhost:9999/",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

// house deal API axios instance
function houseInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_HOUSE_DEAL_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { apiInstance, houseInstance };
