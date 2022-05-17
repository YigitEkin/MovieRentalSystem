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
  const [filter, setfilter] = useState("");
  const [filterval, setfilterval] = useState("");
  const rating = useRef(null);
  const price = useRef(null);
  const [selectRating, setselectRating] = useState("None");
  const [selectPrice, setselectPrice] = useState("None");
  const [movies, setMovies] = useState([]);
  const [filteredMovies, setFilteredMovies] = useState([]);

  useEffect(() => {
    if (state.user_name === null) {
      navigate("/");
    }
    axios
      .get("http://localhost:8081/movies")
      .then((res) => {
        console.log(res.data, "movies");
        setMovies(res.data);
        setFilteredMovies(res.data);
      })
      .catch((err) => {
        console.log(err, "err in movies");
      });
  }, []);

  useEffect(() => {
    console.log(filter, "filter");

    const debounce = setTimeout(() => {
      if ((filter !== "" && filterval !== "") || filter !== "None") {
        console.log("enter");
        if (filter === "actor") {
          console.log("enter 2");
          axios
            .get(`http://localhost:8081/actors`)
            .then((res) => {
              console.log(res.data, "actors 3");
              let actors = res.data;
              for (let actor of actors) {
                if (
                  actor.actor_name
                    .toLowerCase()
                    .trim()
                    .includes(filterval.toLowerCase().trim())
                ) {
                  axios
                    .get(
                      `http://localhost:8081/actors/${actor.actor_id}/movies`
                    )
                    .then((res) => {
                      console.log(res.data, "movies 4");
                      setFilteredMovies(res.data);
                    })
                    .catch((err) => {
                      console.log(err, "err in movies");
                    });
                } else {
                  setFilteredMovies([]);
                }
              }
            })
            .catch((err) => {
              console.log(err, "err in actors");
            });
        } else {
          console.log(`http://localhost:8081/movies?${filter}=${filterval}`);
          axios
            .get(`http://localhost:8081/movies?${filter}=${filterval}`)
            .then((res) => {
              console.log(res.data, "movies 5");
              setFilteredMovies(res.data);
            })
            .catch((err) => {
              setFilteredMovies(movies);
              console.log(err, "err in movies");
            });
        }
      } else {
        setFilteredMovies(movies);
      }
    }, 500);
    return () => clearTimeout(debounce);
  }, [filter, filterval, movies]);

  useEffect(() => {
    let sortedMovies = [];
    console.log(selectPrice, "selectPrice");
    if (price.current.value !== "None") {
      console.log("enter rating 2");

      if (price.current.value === "high-to-low") {
        console.log("enter rating 3");

        sortedMovies = filteredMovies.sort((a, b) => {
          return b.price - a.price;
        });
      } else if (price.current.value === "low-to-high") {
        console.log("enter rating 4");

        sortedMovies = filteredMovies.sort((a, b) => {
          return a.price - b.price;
        });
      } else {
        console.log("enter rating 5");

        sortedMovies = filteredMovies;
      }
      console.log(sortedMovies, "sortedMoviesprice");
      setFilteredMovies(sortedMovies);
    }
  }, [selectPrice]);

  useEffect(() => {
    let sortedMovies = [];
    console.log(selectRating, "selectRating");
    if (rating.current.value !== "None") {
      console.log("enter rating 2");
      if (rating.current.value === "high-to-low") {
        console.log("enter rating 3");

        sortedMovies = filteredMovies.sort((a, b) => {
          console.log(a.rating, b.rating, "rating");
          return b.rating - a.rating;
        });
      } else if (rating.current.value === "low-to-high") {
        console.log("enter rating 4");

        sortedMovies = filteredMovies.sort((a, b) => {
          console.log(a.rating, b.rating, "rating");
          return a.rating - b.rating;
        });
      } else {
        console.log("enter rating 5");

        sortedMovies = filteredMovies;
      }
      console.log(sortedMovies, "sortedMoviesrating");
      setFilteredMovies(sortedMovies);
    }
  }, [selectRating]);

  return (
    <div className="allpage bg-black">
      <Navbar name={state.user_name} />
      <div className="mainContent container mt-5">
        <select
          onChange={(e) => {
            setfilter(e.target.value);
          }}
          value={filter}
          name=""
          className="m-auto custom-select"
          placeholder="Search Movie By"
          id=""
        >
          <option value="None">None</option>
          <option value="actor">Actor</option>
          <option value="title">Title</option>
          <option value="director">Director</option>
          <option value="year">Year</option>
          <option value="genre">Genre</option>
          <option value="rating">Rating</option>
          <option value="price">Price</option>
        </select>
        <div className="input-group input-group-lg mb-5">
          <input
            type="text"
            value={filterval}
            onChange={(e) => {
              setfilterval(e.target.value);
            }}
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
              <select
                name="rating"
                ref={price}
                id="rating"
                className="form-control"
                onChange={(e) => {
                  e.preventDefault();
                  setselectPrice(price.current.value);
                }}
                value={selectPrice}
              >
                <option value="None">None</option>
                <option value="high-to-low">High To Low</option>
                <option value="low-to-high">Low To High</option>
              </select>
            </div>
          </div>
          <div className="col-1"></div>
          <div className="col-4">
            <h1 className="text-red">Sort By Rating:</h1>
            <div className="form-check form-check-inline">
              <select
                name="rating"
                id="rating"
                className="form-control"
                ref={rating}
                onChange={(e) => {
                  e.preventDefault();
                  setselectRating(rating.current.value);
                }}
                value={selectRating}
              >
                <option value="None">None</option>
                <option value="high-to-low">High To Low</option>
                <option value="low-to-high">Low To High</option>
              </select>
            </div>
          </div>

          {filteredMovies.length > 0 ? (
            filteredMovies.map((movie) => (
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
                img_url={movie.img_url}
              />
            ))
          ) : (
            <h1 className="display-1 text-red text-center m-auto">
              No Movies Found
            </h1>
          )}
        </div>
      </div>
    </div>
  );
};

export default SearchMovie;
