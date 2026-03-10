import { api } from "boot/axios";

// GET 请求
const get = (url, callback, params = {}) => {
  api.get(url, { params }).then((response) => {
    callback(response);
  });
};

// POST 请求
const post = (url, data, callback) => {
  api.post(url, data).then((response) => {
    callback(response);
  });
};

// PUT 请求
const put = (url, data, callback) => {
  api.put(url, data).then((response) => {
    callback(response);
  });
};

// DELETE 请求(delete是关键字改为remove)
const remove = (url, data, callback) => {
  // 用 "remove" 而不是 "delete" 作为函数名
  api.delete(url, data).then((response) => {
    callback(response);
  });
};

export { get, post, put, remove };
