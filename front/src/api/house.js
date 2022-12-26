import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/houseApi/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/houseApi/gugun`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  house.get(``, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, houseList };
