import React from "react";
import Logo from "../images/logo.jpeg";
import "../stylesheets/Navbar.css";

const Navbar = () => {
  <div className="container dark-bg">
    <nav class="navbar navbar-expand-lg navbar-dark navbar-style">
      <a class="navbar-brand" href="#">
        <img src={Logo} className="logo" alt="" />
      </a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link text-white" href="#">
              HomePage <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white" href="#">
              Top Rated Films
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white" href="#">
              Request Movie
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white" href="#">
              Rent Movie
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white" href="#">
              Request Deleting a Movie
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white" href="#">
              Search Movie
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-white" href="#">
              Recommend Movie
            </a>
          </li>
        </ul>
      </div>
    </nav>
  </div>;
};

export default Navbar;
