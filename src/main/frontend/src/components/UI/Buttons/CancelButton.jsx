import React from "react";

function CancelButton({ children, ...props }) {
  return (
    <button {...props} className="btn btn-secondary">
      {children}
    </button>
  );
}

export default CancelButton;
