import { useReducer, useState } from "react";
import TextInput from "../UI/TextInput";
import FormError from "./FormError";
import FormValidator from "./FormValidator";
import { formReducer, ACTIONS } from "./FormReducer";
import AuthService from "../../security/AuthService";
import RegisterRequest from "../../security/RegisterRequest";

function RegisterForm() {
  const initialFormState = {
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  };

  const [state, dispatch] = useReducer(formReducer, initialFormState);

  const validator = new FormValidator([
    {
      field: "username",
      method: "isEmpty",
      validWhen: false,
      message: "Enter username.",
    },
    {
      field: "username",
      method: usernameLength,
      validWhen: true,
      message: "Username length must be at least 8 symbols.",
    },
    {
      field: "email",
      method: "isEmail",
      validWhen: true,
      message: "Email is invalid.",
    },
    {
      field: "password",
      method: "isEmpty",
      validWhen: false,
      message: "Enter password.",
    },
    {
      field: "password",
      method: passwordLength,
      validWhen: true,
      message: "Password length must be at least 8 symbols.",
    },
    {
      field: "confirmPassword",
      method: "isEmpty",
      validWhen: false,
      message: "Enter Password confirmation.",
    },
    {
      field: "confirmPassword",
      method: passwordMatch,
      validWhen: true,
      message: "Password and password confirmation do not match.",
    },
  ]);

  const [validation, setValidation] = useState(validator.valid());

  function passwordMatch(confirmPassword, formData) {
    return confirmPassword === formData.password;
  }

  function passwordLength(password) {
    return password.length >= 8;
  }

  function usernameLength(username) {
    return username.length >= 8;
  }

  function handleInput(e) {
    dispatch({
      type: ACTIONS.HANDLE_INPUT,
      field: e.target.name,
      payload: e.target.value,
    });
  }

  function handleSubmit(e) {
    e.preventDefault();
    let validation = validator.validate(state);
    setValidation(validation);
    if (validation.isValid) {
      AuthService.signup(new RegisterRequest(state));
    }
  }

  return (
    <div>
      <form className="form w-50 mx-auto mt-5 shadow p-3">
        <h2>Registration</h2>
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <TextInput
            placeholder="Enter username"
            type="text"
            id="username"
            name="username"
            onChange={handleInput}
          />
          <FormError validation={validation.username} />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <TextInput
            placeholder="Enter email"
            type="email"
            id="email"
            name="email"
            onChange={handleInput}
          />
          <FormError validation={validation.email} />
        </div>
        <div className="form-group">
          <label htmlFor="username">Password</label>
          <TextInput
            placeholder="Enter password"
            type="password"
            id="password"
            name="password"
            onChange={handleInput}
          />
          <FormError validation={validation.password} />
        </div>
        <div className="form-group">
          <label htmlFor="confirmPassword">Confirm Password</label>
          <TextInput
            placeholder="Confirm Password"
            type="confirmPassword"
            id="confirmPassword"
            name="confirmPassword"
            onChange={handleInput}
          />
          <FormError validation={validation.confirmPassword} />
        </div>
        <button
          className="btn btn-primary mt-3"
          onClick={(e) => handleSubmit(e)}
        >
          Submit
        </button>
      </form>
    </div>
  );
}

export default RegisterForm;
