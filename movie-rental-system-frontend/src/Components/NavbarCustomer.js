import React from "react";
import Logo from "../images/logo.jpeg";
import { useNavigate } from "react-router-dom";
import { Context } from "../App";
import { useContext } from "react";
import "../stylesheets/Navbar.css";

const Navbar = ({ name }) => {
  const [state, dispatch] = useContext(Context);
  const navigate = useNavigate();
  return (
    <div className="container dark-bg">
      <nav className="navbar navbar-expand-lg navbar-dark navbar-style">
        <div
          className="navbar-brand"
          onClick={() => navigate("/customer/mainPage")}
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
                onClick={() => navigate("/customer/requestMovie")}
              >
                Request Movie
              </div>
            </li>
            <li className="nav-item">
              <div
                className="nav-link text-white"
                onClick={() => navigate("/customer/myMovies")}
              >
                Personal
              </div>
            </li>
            <li className="nav-item">
              <div
                className="nav-link text-white"
                onClick={() => navigate("/customer/mainPage")}
              >
                Search Movie
              </div>
            </li>
            <li className="nav-item">
              <div
                className="nav-link text-white"
                onClick={() => navigate("/customer/friends")}
              >
                Friends
              </div>
            </li>
            <li className="nav-item">
              <div
                className="nav-link text-white"
                onClick={() => navigate("/customer/payment")}
              >
                Payment
              </div>
            </li>
            <li className="nav-item">
              <div
                className="nav-link text-white"
                onClick={() => navigate("/customer/recommendMovie")}
              >
                Recommend Movie
              </div>
            </li>
            <li className="nav-item">
              <div
                className="nav-link text-white"
                onClick={() => navigate("/customer/recommendedMovies")}
              >
                Recommendations
              </div>
            </li>
            <li className="nav-item">
              <div
                className="nav-link text-white"
                onClick={() => {
                  navigate("/");
                  dispatch({ type: "LOGOUT" });
                }}
              >
                Logout
              </div>
            </li>
          </ul>
          <li className="nav-item no-style">
            <div className="nav-link text-white">{`Hi,  ${state.user_name}`}</div>
          </li>
        </div>
      </nav>
    </div>
  );
};

export default Navbar;
