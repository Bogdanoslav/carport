import { React } from "react";

function Select({
  value,
  onChange,
  showLetterCount = false,
  options,
  defaultOption = undefined,
  ...props
}) {
  let defaultOptionEl = defaultOption ? (
    <option value="-1" disabled>
      {defaultOption}
    </option>
  ) : undefined;

  return (
    <select
      className="form-control"
      value={value}
      onChange={onChange}
      {...props}
    >
      {defaultOptionEl}
      {options.map((option) => (
        <option key={option.id} value={option.id}>
          {option.name}
        </option>
      ))}
    </select>
  );
}

export default Select;
