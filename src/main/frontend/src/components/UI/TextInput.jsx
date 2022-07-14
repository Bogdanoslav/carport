import { useState } from "react";

function TextInput({
  value: valueFromProps,
  onChange: onChangeFromProps,
  defaultValue,
  showLetterCount = false,
  ...rest
}) {
  const isControlled = typeof valueFromProps != "undefined";
  const hasDefaultValue = typeof defaultValue != "undefined";
  const [internalValue, setInternalValue] = useState(
    hasDefaultValue ? defaultValue : ""
  );
  const value = isControlled ? valueFromProps : internalValue;
  const onChange = (e) => {
    if (onChangeFromProps) {
      onChangeFromProps(e);
    }
    if (!isControlled) {
      setInternalValue(e.target.value);
    }
  };
  return (
    <input
      className="form-control"
      value={value}
      onChange={onChange}
      {...rest}
    />
  );
}

export default TextInput;
