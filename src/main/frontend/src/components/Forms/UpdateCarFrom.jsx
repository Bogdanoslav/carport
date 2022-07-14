import React, { useEffect, useReducer, useState } from "react";
import Api from "../../API/Api";
import App from "../../App";
import FormError from "./FormError";
import FormValidator from "./FormValidator";
import AcceptButton from "../UI/Buttons/AcceptButton";
import Select from "../UI/Select";
import { formReducer, ACTIONS } from "./FormReducer";

function UpdateCarForm({ links, ...props }) {
  const initialFormState = {
    colorId: "-1",
    colors: [],
  };

  const [state, dispatch] = useReducer(formReducer, initialFormState);

  const validator = new FormValidator([
    {
      field: "colorId",
      method: "equals",
      args: ["-1"],
      validWhen: false,
      message: "Please select value.",
    },
  ]);
  const [validation, setValidation] = useState(validator.valid());

  useEffect(() => {
    getColors();
  }, []);

  async function getColors() {
    Api.get(App.index["car-colors"].href).then((res) => {
      dispatch({
        type: ACTIONS.HANDLE_INPUT,
        field: "colors",
        payload: res,
      });
    });
  }

  function handleUpdate(e) {
    e.preventDefault();
    let validation = validator.validate(state);
    setValidation(validation);
    if (validation.isValid) {
      props.onUpdate(state);
      resetForm();
    }
  }

  function resetForm() {
    dispatch({ type: ACTIONS.RESET, payload: initialFormState });
    getColors();
  }

  function handleInput(e) {
    dispatch({
      type: ACTIONS.HANDLE_INPUT,
      field: e.target.name,
      payload: e.target.value,
    });
  }

  return (
    <div className="">
      <form className="form">
        <h2>Update car</h2>
        <div className="form-group mb-3">
          <label htmlFor="colorId">Color</label>
          <Select
            options={state.colors}
            onChange={handleInput}
            value={state.colorId}
            defaultOption="Select Color"
            id="colorId"
            name="colorId"
          ></Select>
        </div>
        <FormError validation={validation.colorId} />

        <AcceptButton onClick={handleUpdate} className="mt-3">
          Update
        </AcceptButton>
      </form>
    </div>
  );
}

export default UpdateCarForm;
