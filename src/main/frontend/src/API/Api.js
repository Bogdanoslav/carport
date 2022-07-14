import axios from "axios";

export default class Api {
  static async get(link) {
    return await Api.do("get", link, null);
  }

  static async delete(link) {
    return await Api.do("delete", link, null);
  }

  static async post(link, body) {
    return await Api.do("post", link, body);
  }

  static async update(link, body) {
    return await Api.do("put", link, body);
  }

  static async patch(link, body) {
    return await Api.do("patch", link, body);
  }

  static async do(method, link, body) {
    try {
      const headers = {
        "Content-Type": "application/json;charset=UTF-8",
      };

      const response = await axios({
        method: method,
        url: link,
        data: body,
        headers: headers,
      });

      return response.data;
    } catch (e) {
      console.log(e);
    }
  }
}
