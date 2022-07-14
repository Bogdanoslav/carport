import React, { Component } from "react";
import Navbar from "./components/Navbar";
import HomePage from "./pages/HomePage";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Api from "./API/Api";
import Environment from "./API/Environment";
import CarList from "./components/CarList";
import Navigation from "./Navigation";
import RegisterForm from "./components/Forms/RegisterForm";

class App extends Component {
  static index;

  constructor(props) {
    super(props);
    this.state = {
      index: {},
    };
    this.setIndex = this.setIndex.bind(this);
  }

  componentDidMount() {
    this.loadIndex();
  }

  loadIndex() {
    Api.get(Environment.index).then((response) => {
      this.setIndex(response._links);
    });
  }

  setIndex(index) {
    App.index = index;
    this.setState({
      index: index,
    });
  }

  render() {
    const index = this.state.index;
    if (Object.keys(index).length > 0) {
      return (
        <BrowserRouter>
          <Navbar />
          <Routes>
            <Route path={Navigation.main.home()} element={<HomePage />} />
            <Route path={Navigation.cars.list()} element={<CarList />} />
            <Route path={"/register"} element={<RegisterForm />} />
          </Routes>
        </BrowserRouter>
      );
    } else {
      return null;
    }
  }
}

export default App;
