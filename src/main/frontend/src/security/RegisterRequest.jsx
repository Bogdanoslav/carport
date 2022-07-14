export default class RegisterRequest {
  username;
  email;
  password;
  confirmPassword;

  constructor(data = {}) {
    this.username = data.username;
    this.email = data.email;
    this.password = data.password;
    this.confirmPassword = data.confirmPassword;
  }
}
