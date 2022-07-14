const ACTIONS = {
  HANDLE_INPUT: "handle_select_input",
  RESET: "reset",
  GET_DATA: "get_colors",
};

const formReducer = (state, action) => {
  switch (action.type) {
    case ACTIONS.HANDLE_INPUT:
      return { ...state, [action.field]: action.payload };
    case ACTIONS.RESET:
      return { ...state, ...action.payload };
    default:
      return state;
  }
};

export { formReducer, ACTIONS };
