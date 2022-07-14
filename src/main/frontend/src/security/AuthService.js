import Api from "../API/Api";

export default class AuthService {
  static signup(registerRequest) {
    Api.post(Api.index["signup"], registerRequest);
  }
}
