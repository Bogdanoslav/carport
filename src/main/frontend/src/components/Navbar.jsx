import React from "react";
import { Link } from "react-router-dom";
import Navigation from "../Navigation";

function Navbar(index) {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <Link className="navbar-brand" to={Navigation.main.home()}>
        Carport_
      </Link>
      <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div className="navbar-nav">
          <Link className="nav-item nav-link" to={Navigation.main.home()}>
            Home
          </Link>
          <Link className="nav-item nav-link" to={Navigation.cars.list()}>
            Cars
          </Link>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
