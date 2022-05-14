import React, { useState, useEffect, useRef } from "react";
import "../stylesheets/login.css";
import Logo from "../images/logo.jpeg";
import axios from "axios";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import { Context } from "../App";

const Login = () => {
  //usecontext is used to get the state and dispatch from the context provider
  const [state, dispatch] = useContext(Context);
  const [user, setUser] = useState({ user_name: "", password: "" });
  const [isUsernameRemebered, setIsUsernameRemebered] = useState(false);
  const userNameInput = useRef(null);
  const checkbox = useRef(null);
  const navigate = useNavigate();

  function handleLogin(e) {
    e.preventDefault();
    //axios.get("http://localhost:8081/Login", user).then().then();
    //router.push("/")
    navigate("/customer/mainPage");
    dispatch({ type: "SET_USER_ID", payload: 1 });
    dispatch({ type: "SET_USER_NAME", payload: user.user_name });
    dispatch({ type: "SET_CART", payload: [] });

    if (user.user_name === "admin@admin.com" || user.password === 123456) {
      dispatch({ type: "SET_USER_ID", payload: "admin" });
      dispatch({ type: "SET_USER_NAME", payload: "admin@admin.com" });
      dispatch({ type: "SET_CART", payload: [] });
      navigate("/employee/mainPage");
    }
  }

  function handleInput(e) {
    const key = e.target.id;
    const value = e.target.value;

    setUser((prev) => {
      const obj = prev;
      obj[key] = value;
      return obj;
    });

    if (isUsernameRemebered) {
      localStorage.setItem("username", value);
    }
  }

  useEffect(() => {
    const userName = localStorage.getItem("username");
    const checked = JSON.parse(localStorage.getItem("checked"));

    if (userName && checked) {
      userNameInput.current.value = userName;
      setIsUsernameRemebered(checked);
      checkbox.current.checked = true;
    }
  }, []);

  function handleRememberUsername(e) {
    let isChecked = e.target.checked;
    if (isChecked) {
      setIsUsernameRemebered(true);
      localStorage.setItem("checked", JSON.stringify(true));
      localStorage.setItem("username", user.user_name);
    } else {
      setIsUsernameRemebered(false);
      localStorage.setItem("checked", JSON.stringify(false));
      localStorage.removeItem("username");
    }
  }

  return (
    <div className="center black-bg">
      <div className="container box-shadow p-5 black-bg">
        <div className="row">
          <div className="col-md-6 d-md-block d-none d-flex justify-content-center">
            <img src={Logo} alt="" className="logo-styling-login m-auto" />
          </div>
          <div className="col-md-6 col-12 border-left-red">
            <form className="login-form" onSubmit={handleLogin}>
              <div className="form-group">
                <label htmlFor="user_name">Username</label>
                <input
                  type="text"
                  className="form-control"
                  id="user_name"
                  aria-describedby="emailHelp"
                  placeholder="Künefe123"
                  ref={userNameInput}
                  onChange={handleInput}
                  required
                />
                <small id="emailHelp" className="form-text text-gray">
                  We'll never share your email with anyone else.
                </small>
              </div>
              <div className="form-group">
                <label htmlFor="password">Password</label>
                <input
                  type="password"
                  className="form-control"
                  id="password"
                  placeholder="PASSWORD"
                  onChange={handleInput}
                  required
                />
              </div>
              <div className="form-group form-check">
                <input
                  type="checkbox"
                  className="form-check-input checkbox-red"
                  id="exampleCheck1"
                  onClick={handleRememberUsername}
                  ref={checkbox}
                />
                <label className="form-check-label" htmlFor="exampleCheck1">
                  Remember my username
                </label>
              </div>
              <button type="submit" className="btn btn-block submit-btn">
                Login
              </button>
            </form>
            <div className="row mt-3">
              <div className="col-12 ml-2 p-2">
                <Link to={"/signup"}>
                  <p className="text-red large-text">Don't have an account?</p>
                </Link>
                <div onClick={() => navigate("/forgotPassword")}>
                  <p className="text-red large-text">Forgot my Password</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
