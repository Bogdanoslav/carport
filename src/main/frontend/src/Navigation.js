export default class Navigation {
  static main = {
    home() {
      return "/";
    },
  };

  static cars = {
    list() {
      return "/cars";
    },
  };

  static auth = {
    signup() {
      return "/signup";
    },
    signin() {
      return "/signin";
    },
  };
}
