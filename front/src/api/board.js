import { apiInstance } from "./index.js";

const api = apiInstance();

async function write(board, success, fail) {
  await api.post(`/boardApi/board`, JSON.stringify(board)).then(success).catch(fail);
}

async function modify(board, success, fail) {
  await api.post(`/boardApi/modify`, JSON.stringify(board)).then(success).catch(fail);
}

export { write, modify };