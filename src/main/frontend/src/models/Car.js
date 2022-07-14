import Api from "../API/Api";
import App from "../App";
import Resource from "../common/Resource";

export default class Car extends Resource {
  id;
  modelId;
  brandId;
  colorId;
  bodyTypeId;
  model;
  brand;
  color;
  bodyType;

  constructor(data = {}) {
    let links = data._links ? data._links : data.links;
    super(links);
    this.id = data.id;
    this.modelId = data.modelId;
    this.brandId = data.brandId;
    this.colorId = data.colorId;
    this.bodyTypeId = data.bodyTypeId;
    this.model = data.model;
    this.brand = data.brand;
    this.color = data.color;
    this.bodyType = data.bodyType;
  }

  static async save(data) {
    // todo handle create
    let link = App.index.cars.href;
    await Api.post(link, data);
  }
  async delete() {
    let link = this.link("delete");
    await Api.delete(link);
  }

  async update(fields) {
    await Api.patch(this.link("patch"), fields);
  }

  static getAll() {
    let link = App.index.cars.href;
    let cars = [];
    return Api.get(link).then((res) => {
      let carList = res._embedded.carList;
      carList.forEach((car) => {
        cars.push(new Car(car));
      });
      return cars;
    });
  }
}
