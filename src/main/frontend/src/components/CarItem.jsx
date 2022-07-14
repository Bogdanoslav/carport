import React from "react";

function CarItem({ car, ...props }) {
  function handleDelete() {
    props.onDelete(car);
  }

  function handleOpenUpdateForm() {
    props.onOpenUpdateForm(car);
  }
  return (
    <tr>
      <td>{car.id}</td>
      <td>{car.brand.name + " " + car.model.name}</td>
      <td>{car.color.name}</td>
      <td>{car.bodyType.name}</td>
      <td>
        <button className="btn btn-danger" onClick={handleDelete}>
          Delete
        </button>
      </td>
      <td>
        <button className="btn btn-primary" onClick={handleOpenUpdateForm}>
          Update
        </button>
      </td>
    </tr>
  );
}

export default CarItem;
