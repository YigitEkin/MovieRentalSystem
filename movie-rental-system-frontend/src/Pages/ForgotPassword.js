import { Axios } from "axios";
import React, { useState, useRef } from "react";
import "../stylesheets/signup.css";
import axios from "axios";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const ForgotPassword = () => {
  const navigate = useNavigate();
  const username = useRef(null);
  const password = useRef(null);

  async function handleSubmit(e) {
    e.preventDefault();
    const userName = username.current.value;
    let user = null;
    await axios
      .get(`http://localhost:8081/customers/${userName}`)
      .then((res) => {
        console.log(res, "get");
        user = res.data;
      })
      .catch((err) => {
        alert("User not found");
      });
    user = {
      ...user,
      password: password.current.value,
    };
    await axios
      .put(`http://localhost:8081/customers/${userName}`, user)
      .then((res) => {
        if (res.status === 200) {
          console.log(res, "put");
          alert("Password changed successfully");
          navigate("/");
        }
      })
      .catch((err) => {
        alert("Error");
      });
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
            ref={username}
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
            ref={password}
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
