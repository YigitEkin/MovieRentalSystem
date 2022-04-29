import React, { useState, useEffect } from "react";
import "../stylesheets/signup.css";
import Logo from "../images/logo.jpeg";

const SignUp = () => {
  return (
    <div className="black-bg center border-red">
      <form className="box-shadow p-10 m- w-60 p-relative">
        <a href="./Login.js" className="back-to-login btn">
          {"<-"}
        </a>
        <h1 className="display-4 text-red text-center mb-4">Sign Up</h1>
        <div class="form-group">
          <label for="exampleInputPassword1" className="text-gray">
            Username
          </label>
          <input
            type="text"
            class="form-control"
            id="exampleInputPassword1"
            placeholder="exampleUser1"
          />
        </div>
        <div class="form-group">
          <label for="exampleInputEmail1" className="text-gray">
            Email address
          </label>
          <input
            type="email"
            class="form-control"
            id="exampleInputEmail1"
            aria-describedby="emailHelp"
            placeholder="a@b.com"
          />
        </div>
        <div class="form-group">
          <label for="exampleInputPassword1" className="text-gray">
            Password
          </label>
          <input
            type="password"
            class="form-control"
            id="exampleInputPassword1"
            placeholder="examplePassword1"
          />
        </div>
        <div class="form-group">
          <label for="birthDate" className="text-gray">
            Birth Date
          </label>
          <input type="date" class="form-control" id="birthDate" />
        </div>
        <button type="submit" class="btn submit-btn">
          Create Account
        </button>
      </form>
    </div>
  );
};

export default SignUp;
