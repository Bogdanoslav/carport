import React, { useEffect, useState } from "react";
import CarItem from "./CarItem";
import MyDialog from "./MyDialog/MyDialog";
import AcceptButton from "./UI/Buttons/AcceptButton";
import CreateCarForm from "./Forms/CreateCarForm";
import UpdateCarForm from "./Forms/UpdateCarFrom";
import App from "../App";
import Car from "../models/Car";

function CarList() {
  const [cars, setCars] = useState([]);
  const [createDialog, setCreateDialog] = useState(false);
  const [updateDialog, setUpdateDialog] = useState(false);
  const [carToUpdate, setCarToUpdate] = useState({});

  useEffect(() => {
    getCars();
  }, []);

  async function getCars() {
    Car.getAll().then((res) => {
      setCars(res);
    });
  }

  async function onDelete(car) {
    await car.delete().then(getCars);
  }

  async function onUpdate(formData) {
    await carToUpdate.update(formData).then(getCars);
    setUpdateDialog(false);
    setCarToUpdate({});
  }

  async function onCreate(car) {
    Car.save(car).then(getCars);
    setCreateDialog(false);
  }

  function onOpenUpdateForm(car) {
    setCarToUpdate(car);
    setUpdateDialog(true);
  }

  return (
    <div className="w-50 mx-auto">
      <MyDialog visible={createDialog} setVisible={setCreateDialog}>
        <CreateCarForm links={App.index} onCreate={onCreate}></CreateCarForm>
      </MyDialog>
      <MyDialog visible={updateDialog} setVisible={setUpdateDialog}>
        <UpdateCarForm links={App.index} onUpdate={onUpdate}></UpdateCarForm>
      </MyDialog>
      <table className="table table-striped">
        <thead className="table-dark">
          <tr>
            <th scope="column">#id</th>
            <th scope="column">#model</th>
            <th scope="column">#color</th>
            <th scope="column">#body type</th>
            <th scope="column"></th>
            <th scope="column"></th>
          </tr>
        </thead>
        <tbody>
          {cars.map((car) => (
            <CarItem
              car={car}
              key={car.links.self.href}
              onDelete={onDelete}
              onOpenUpdateForm={onOpenUpdateForm}
            />
          ))}
        </tbody>
      </table>
      <div>
        <AcceptButton onClick={() => setCreateDialog(true)}>
          Create Car
        </AcceptButton>
      </div>
    </div>
  );
}

export default CarList;
