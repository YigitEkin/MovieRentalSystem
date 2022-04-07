import React, { useEffect, useRef, useState } from "react";
import "../stylesheets/SearchMovie.css";
import Navbar from "../Components/Navbar";
import SignUp from "./Signup";
import MovieCard from "../Components/MovieCard";
import Logo from "../images/breakingbad.jpeg";

const SearchMovie = () => {
  const [filter, setFilter] = useState("");
  const [price, setPrice] = useState(false);
  const [rating, setRating] = useState(false);
  const filterElement = useRef(null);

  useEffect(() => {
    if (filter === "price") {
      setPrice(true);
    } else if (filter === "rating") {
      setRating(true);
    } else {
      setRating(false);
      setPrice(false);
    }
  }, [filter]);
  return (
    <div className="allpage bg-black">
      <Navbar name="ArdaTheJapon_07" />
      <div className="mainContent container mt-5">
        <select
          ref={filterElement}
          name=""
          className="m-auto custom-select"
          placeholder="Search Movie By"
          id=""
          onChange={(e) => setFilter(e.target.value)}
        >
          <option value="Actor">Actor</option>
          <option value="Actor">Title</option>
          <option value="Actor">Director</option>
          <option value="Year">Year</option>
          <option value="Genre">Genre</option>
        </select>
        <div className="input-group input-group-lg mb-5">
          <input
            type="text"
            className="form-control"
            aria-label="Sizing example input"
            aria-describedby="inputGroup-sizing-lg"
            placeholder="Search Movie by the filter selected above"
          />
        </div>
        <div className="row mb-10">
          <div className="col-2"></div>
          <div className="col-4">
            <h1 className="text-red">Sort By Price:</h1>
            <div class="form-check form-check-inline">
              <input
                class="form-check-input"
                type="checkbox"
                id="inlineCheckbox1"
                value="option1"
              />
              <label
                class="form-check-label text-white text-lg"
                for="inlineCheckbox1"
              >
                Low-High
              </label>
            </div>
            <div class="form-check form-check-inline">
              <input
                class="form-check-input"
                type="checkbox"
                id="inlineCheckbox2"
                value="option2"
              />
              <label
                class="form-check-label text-white text-lg"
                for="inlineCheckbox2"
              >
                High-Low
              </label>
            </div>
          </div>
          <div className="col-1"></div>
          <div className="col-4">
            <h1 className="text-red">Sort By Price:</h1>
            <div class="form-check form-check-inline">
              <input
                class="form-check-input"
                type="checkbox"
                id="inlineCheckbox1"
                value="option1"
              />
              <label
                class="form-check-label text-white text-lg"
                for="inlineCheckbox1"
              >
                Low-High
              </label>
            </div>
            <div class="form-check form-check-inline">
              <input
                class="form-check-input"
                type="checkbox"
                id="inlineCheckbox2"
                value="option2"
              />
              <label
                class="form-check-label text-white text-lg"
                for="inlineCheckbox2"
              >
                High-Low
              </label>
            </div>
          </div>
        </div>
        <MovieCard
          img_url={Logo}
          title={"Breaking Bad"}
          rating={5}
          description={
            "Walter White, a chemistry teacher, discovers that he has cancer and decides to get into the meth-making business to repay his medical debts. His priorities begin to change when he partners with Jesse."
          }
          year={2008}
          director={"Vince Gilligan"}
          genre={"Drama"}
          price={"20.99$"}
        />
      </div>
    </div>
  );
};

export default SearchMovie;
