import React from "react";
import Logo from "../images/logo.jpeg";
import "../stylesheets/Navbar.css";
import { useNavigate } from "react-router-dom";

const Navbar = ({ name }) => {
  const navigate = useNavigate();
  return (
    <div className="container dark-bg">
      <nav className="navbar navbar-expand-lg navbar-dark navbar-style">
        <div
          className="navbar-brand"
          onClick={() => navigate("/employee/mainPage")}
        >
          <img src={Logo} className="logo" alt="" />
        </div>
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
              <div
                className="nav-link text-white"
                onClick={() => navigate("/employee/addMovie")}
              >
                Add Movie
              </div>
            </li>
            <li className="nav-item">
              <div
                className="nav-link text-white"
                onClick={() => navigate("/employee/deleteMovie")}
              >
                Delete Movie
              </div>
            </li>
            <li className="nav-item">
              <div
                className="nav-link text-white"
                onClick={() => navigate("/employee/deleteUser")}
              >
                Delete User
              </div>
            </li>
            <li className="nav-item">
              <div
                className="nav-link text-white"
                onClick={() => {
                  navigate("/");
                }}
              >
                Logout
              </div>
            </li>
          </ul>
          <li className="nav-item no-style">
            <div className="nav-link text-white">{`Hi,  ${name}`}</div>
          </li>
        </div>
      </nav>
    </div>
  );
};

export default Navbar;
