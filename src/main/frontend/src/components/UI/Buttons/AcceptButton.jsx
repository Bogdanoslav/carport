import React from "react";

function AcceptButton({ children, ...props }) {
  return (
    <button {...props} className="btn btn-primary">
      {children}
    </button>
  );
}

export default AcceptButton;
