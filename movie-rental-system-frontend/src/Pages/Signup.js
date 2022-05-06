import { Axios } from "axios";
import React, { useState } from "react";
import "../stylesheets/signup.css";
import axios from "axios";

const SignUp = () => {
  const [user, setUser] = useState({
    user_name: "",
    user_email: "",
    password: "",
    birth_year: "",
  });

  function handleSubmit(e) {
    e.preventDefault();
    //year should be casted to number
    //axios.get("http://localhost:8081/Signup", user).then().then();
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
        <a href="./Login.js" className="back-to-login btn">
          {"<-"}
        </a>
        <h1 className="display-4 text-red text-center mb-4">Sign Up</h1>
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
          <label htmlFor="exampleInputEmail1" className="text-gray">
            Email address
          </label>
          <input
            type="email"
            required
            className="form-control"
            id="exampleInputEmail1"
            aria-describedby="emailHelp"
            placeholder="a@b.com"
            onChange={handleChange}
          />
        </div>
        <div className="form-group">
          <label htmlFor="exampleInputPassword1" className="text-gray">
            Password
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
        <div className="form-group">
          <label htmlFor="birthDate" className="text-gray">
            Birth Date (MM/DD/YY)
          </label>
          <input
            type="date"
            required
            min={"1990-01-01"}
            max={new Date().toISOString().split("T")[0]}
            className="form-control"
            id="birthDate"
            onChange={handleChange}
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
