import { apiInstance } from "./index.js";

const api = apiInstance();

async function write(notice, success, fail) {
  await api.post(`/noticeApi/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

async function modify(notice, success, fail) {
  await api.post(`/noticeApi/modify`, JSON.stringify(notice)).then(success).catch(fail);
}

export { write, modify };