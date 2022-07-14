import React from "react";
import "./MyDialog.css";

function MyDialog({ children, visible, setVisible }) {
  const rootClasses = ["dialog"];

  if (visible) {
    rootClasses.push("dialog-active");
  }

  return (
    <div className={rootClasses.join(" ")} onClick={() => setVisible(false)}>
      <div className="dialog-content" onClick={(e) => e.stopPropagation()}>
        {children}
      </div>
    </div>
  );
}

export default MyDialog;
