import { Axios } from "axios";
import React, { useState, useRef } from "react";
import "../stylesheets/signup.css";
import axios from "axios";
import { Link } from "react-router-dom";

const SignUp = () => {
  const user_name = useRef(null);
  const email = useRef(null);
  const password = useRef(null);
  const birth_year = useRef(null);
  /*
            "user_name": "user231",
            "password": "customer password",
            "user_email": "customer email",
            "birth_year": "2022-05-20",
            "balance": 0,
            "promotion_code": "aaaa"
*/
  function handleSubmit(e) {
    e.preventDefault();
    const user = {
      user_name: user_name.current.value,
      password: password.current.value,
      user_email: email.current.value,
      birth_year: birth_year.current.value,
      balance: 0,
      promotion_code: "aaaa",
    };
    console.log(user);
    axios.post("http://localhost:8081/customers", user).then((res) => {
      console.log(res);
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
        <h1 className="display-4 text-red text-center mb-4">Sign Up</h1>
        <div className="form-group">
          <label htmlFor="exampleInputPassword1" className="text-gray">
            Username
          </label>
          <input
            type="text"
            ref={user_name}
            required
            className="form-control"
            id="exampleInputPassword1"
            placeholder="exampleUser1"
          />
        </div>
        <div className="form-group">
          <label htmlFor="exampleInputEmail1" className="text-gray">
            Email address
          </label>
          <input
            type="email"
            required
            ref={email}
            className="form-control"
            id="exampleInputEmail1"
            aria-describedby="emailHelp"
            placeholder="a@b.com"
          />
        </div>
        <div className="form-group">
          <label htmlFor="exampleInputPassword1" className="text-gray">
            Password
          </label>
          <input
            type="password"
            required
            ref={password}
            className="form-control"
            id="exampleInputPassword1"
            placeholder="examplePassword1"
          />
        </div>
        <div className="form-group">
          <label htmlFor="birthDate" className="text-gray">
            Birth Date (MM/DD/YY)
          </label>
          <input
            type="date"
            required
            ref={birth_year}
            min={"1990-01-01"}
            max={new Date().toISOString().split("T")[0]}
            className="form-control"
            id="birthDate"
          />
        </div>
        <button type="submit" className="btn submit-btn">
          Create Account
        </button>
      </form>
    </div>
  );
};

export default SignUp;
