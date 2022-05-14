import { Axios } from "axios";
import React, { useState } from "react";
import "../stylesheets/signup.css";
import axios from "axios";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const ForgotPassword = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState({
    user_name: "",
    password: "",
  });

  function handleSubmit(e) {
    e.preventDefault();
    //year should be casted to number
    //axios.get("http://localhost:8081/Signup", user).then().then();
    navigate("/");
    alert("Your password has been changed successfully");
  }

  function handleChange(e) {
    const key = e.target.id;
    const value = e.target.value;
    let obj = user;
    obj[key] = value;
    setUser(obj);
  }

  return (
    <div className="black-bg center border-red">
      <form
        className="box-shadow p-10 m- w-60 p-relative"
        onSubmit={handleSubmit}
      >
        <Link to={"/"} className="back-to-login btn">
          {"<-"}
        </Link>
        <h1 className="display-4 text-red text-center mb-4">Change Password</h1>
        <div className="form-group">
          <label htmlFor="exampleInputPassword1" className="text-gray">
            Username
          </label>
          <input
            type="text"
            required
            className="form-control"
            id="exampleInputPassword1"
            placeholder="exampleUser1"
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="exampleInputPassword1" className="text-gray">
            New Password
          </label>
          <input
            type="password"
            required
            className="form-control"
            id="exampleInputPassword1"
            placeholder="examplePassword1"
            onChange={handleChange}
          />
        </div>
        <button type="submit" className="btn submit-btn btn-block mt-4">
          Change Password
        </button>
      </form>
    </div>
  );
};

export default ForgotPassword;
