import React from "react";
import "../stylesheets/SearchMovie.css";
import Navbar from "../Components/Navbar";

const SearchMovie = () => {
  return (
    <div className="allpage dark-bg">
      <Navbar />
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
    </div>
  );
};

export default SearchMovie;
