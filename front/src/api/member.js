import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  console.log("로그인 시도");
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function register(user, success, fail) {
  await api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
}

async function modify(user, success, fail) {
  await api.post(`/user/modify`, JSON.stringify(user)).then(success).catch(fail);
}

async function leave(user, success, fail) {
  await api.post(`/user/delete`, JSON.stringify(user)).then(success).catch(fail);
}

async function findpwd(user, success, fail) {
  await api.post(`/mail/send`, JSON.stringify(user)).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, register, modify, leave, findpwd };
