import React, { useEffect, useRef, useState } from "react";
import "../../stylesheets/SearchMovie.css";
import Navbar from "../../Components/NavbarCustomer";
import MovieCard from "../../Components/MovieCard";
import { useContext } from "react";
import { Context } from "../../App";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const SearchMovie = () => {
  const [state, dispatch] = useContext(Context);
  const navigate = useNavigate();
  const [filter, setFilter] = useState("");
  const [price, setPrice] = useState(false);
  const [rating, setRating] = useState(false);
  const filterElement = useRef(null);
  const [movies, setMovies] = useState([]);

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

  useEffect(() => {
    if (state.user_name === null) {
      navigate("/");
    }
    axios
      .get("http://localhost:8081/movies")
      .then((res) => {
        console.log(res.data, "movies");
        setMovies(res.data);
      })
      .catch((err) => {
        console.log(err, "err in movies");
      });
  }, []);
  return (
    <div className="allpage bg-black">
      <Navbar name={state.user_name} />
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
            <div className="form-check form-check-inline">
              <input
                className="form-check-input"
                type="checkbox"
                id="inlineCheckbox1"
                value="option1"
              />
              <label
                className="form-check-label text-white text-lg"
                htmlFor="inlineCheckbox1"
              >
                Low-High
              </label>
            </div>
            <div className="form-check form-check-inline">
              <input
                className="form-check-input"
                type="radio"
                id="inlineCheckbox2"
                value="option2"
              />

              <label
                className="form-check-label text-white text-lg"
                htmlFor="inlineCheckbox2"
              >
                High-Low
              </label>
            </div>
          </div>
          <div className="col-1"></div>
          <div className="col-4">
            <h1 className="text-red">Sort By Rating:</h1>
            <div className="form-check form-check-inline">
              <input
                className="form-check-input"
                type="checkbox"
                id="inlineCheckbox1"
                value="option1"
              />
              <label
                className="form-check-label text-white text-lg"
                htmlFor="inlineCheckbox1"
              >
                Low-High
              </label>
            </div>
            <div className="form-check form-check-inline">
              <input
                className="form-check-input"
                type="checkbox"
                id="inlineCheckbox2"
                value="option2"
              />
              <label
                className="form-check-label text-white text-lg"
                htmlFor="inlineCheckbox2"
              >
                High-Low
              </label>
            </div>
          </div>
        </div>
        {movies.map((movie) => (
          <MovieCard
            name={state.user_name}
            key={movie.movie_id}
            title={movie.movie_title}
            rating={movie.avg_rating}
            year={movie.production_year}
            director={movie.director}
            genre={movie.genre}
            price={movie.price}
            actors={movie.actors}
            id={movie.movie_id}
          />
        ))}
      </div>
    </div>
  );
};

export default SearchMovie;
