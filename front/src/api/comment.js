import { apiInstance } from "./index.js";

const api = apiInstance();

async function write(comment, success, fail) {
  await api.post(`/commentApi/comment/${comment.article_no}`, JSON.stringify(comment)).then(success).catch(fail);
}

async function modify(comment, success, fail) {
  await api.post(`/commentApi/modify`, JSON.stringify(comment)).then(success).catch(fail);
}

async function del(comment, success, fail) {
  await api.post(`/commentApi/delete/${comment.memo_no}`, JSON.stringify(comment)).then(success).catch(fail);
}

async function deleteComments(articleNo, success, fail) {
  await api.post(`/commentApi/deleteComments/${articleNo}`, JSON.stringify(articleNo)).then(success).catch(fail);
}

export { write, modify, del, deleteComments };