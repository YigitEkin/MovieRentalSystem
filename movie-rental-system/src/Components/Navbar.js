import React from "react";
import Logo from "../images/logo.jpeg";
import "../stylesheets/Navbar.css";

const Navbar = ({ name }) => (
  <div className="container dark-bg">
    <nav className="navbar navbar-expand-lg navbar-dark navbar-style">
      <a className="navbar-brand" href="#">
        <img src={Logo} className="logo" alt="" />
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      <div className="collapse navbar-collapse" id="navbarSupportedContent">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item">
            <a className="nav-link text-white" href="#">
              HomePage <span className="sr-only">(current)</span>
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">
              Top Rated Films
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">
              Request Movie
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">
              Rent Movie
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">
              Search Movie
            </a>
          </li>
          <li className="nav-item">
            <a className="nav-link text-white" href="#">
              Recommend Movie
            </a>
          </li>
        </ul>
        <li className="nav-item no-style">
          <a className="nav-link text-white">{`Hi,  ${name}`}</a>
        </li>
      </div>
    </nav>
  </div>
);

export default Navbar;
