import React, { useEffect, useState } from "react";
import { useReducer } from "react";
import Api from "../../API/Api";
import App from "../../App";
import FormError from "./FormError";
import FormValidator from "./FormValidator";
import AcceptButton from "../UI/Buttons/AcceptButton";
import Select from "../UI/Select";
import { formReducer, ACTIONS } from "./FormReducer";

function CreateCarForm({ links, ...props }) {
  const initialFormState = {
    brandId: "-1",
    modelId: "-1",
    colorId: "-1",
    bodyTypeId: "-1",
    colors: [],
    models: [],
    brands: [],
    bodyTypes: [],
  };

  const [state, dispatch] = useReducer(formReducer, initialFormState);

  useEffect(() => {
    getColors();
    getBodyTypes();
    getBrands();
  }, []);

  const validator = new FormValidator([
    {
      field: "brandId",
      method: "equals",
      args: ["-1"],
      validWhen: false,
      message: "Please select value.",
    },
    {
      field: "modelId",
      method: "equals",
      args: ["-1"],
      validWhen: false,
      message: "Please select value.",
    },
    {
      field: "colorId",
      method: "equals",
      args: ["-1"],
      validWhen: false,
      message: "Please select value.",
    },
    {
      field: "bodyTypeId",
      method: "equals",
      args: ["-1"],
      validWhen: false,
      message: "Please select value.",
    },
  ]);

  const [validation, setValidation] = useState(validator.valid());

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
      props.onCreate(state);
      resetForm();
    }
  }

  function resetForm() {
    dispatch({ type: ACTIONS.RESET, payload: initialFormState });
    getColors();
    getBodyTypes();
    getBrands();
  }

  async function getColors() {
    Api.get(App.index["car-colors"].href).then((res) => {
      dispatch({ type: ACTIONS.HANDLE_INPUT, field: "colors", payload: res });
    });
  }

  async function getBodyTypes() {
    Api.get(App.index["car-bodyTypes"].href).then((res) => {
      dispatch({
        type: ACTIONS.HANDLE_INPUT,
        field: "bodyTypes",
        payload: res,
      });
    });
  }

  async function getBrands() {
    Api.get(App.index["car-brands"].href).then((res) => {
      dispatch({
        type: ACTIONS.HANDLE_INPUT,
        field: "brands",
        payload: res._embedded.carBrandList,
      });
    });
  }

  function getModels(e) {
    let brandId = e.target.value;
    let brand = state.brands.find((brand) => brand.id === parseInt(brandId));
    Api.get(brand._links.models.href).then((res) => {
      dispatch({
        type: ACTIONS.HANDLE_INPUT,
        field: "models",
        payload: res,
      });
    });
  }

  return (
    <div className="">
      <h2>Create car</h2>
      <form className="form">
        <div className="form-group">
          <label htmlFor="brandId">Brand</label>
          <Select
            options={state.brands}
            onChange={handleInput}
            defaultOption="Select Brand"
            value={state.brandId}
            onInput={getModels}
            id="brandId"
            name="brandId"
          />
          <FormError validation={validation.brandId} />
        </div>
        <div className="form-group">
          <label htmlFor="modelId">Model</label>
          <Select
            options={state.models}
            onChange={handleInput}
            defaultOption="Select Model"
            value={state.modelId}
            id="modelId"
            name="modelId"
          />
          <FormError validation={validation.modelId} />
        </div>
        <div className="form-group">
          <label htmlFor="colorId">Color</label>
          <Select
            options={state.colors}
            onChange={handleInput}
            defaultOption="Select Color"
            value={state.colorId}
            id="colorId"
            name="colorId"
          />
          <FormError validation={validation.colorId} />
        </div>
        <div className="form-group mb-3">
          <label htmlFor="bodyTypeId">Body Type</label>
          <Select
            options={state.bodyTypes}
            onChange={handleInput}
            defaultOption="Select Body Type"
            value={state.bodyTypeId}
            id="bodyTypeId"
            name="bodyTypeId"
          />
          <FormError validation={validation.bodyTypeId} />
        </div>
        <AcceptButton onClick={handleSubmit}>Add</AcceptButton>
      </form>
    </div>
  );
}

export default CreateCarForm;
