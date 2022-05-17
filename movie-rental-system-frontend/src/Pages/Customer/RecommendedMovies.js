import React, { useEffect, useRef, useState } from "react";
import "../../stylesheets/SearchMovie.css";
import Navbar from "../../Components/NavbarCustomer";
import MovieCard from "../../Components/MovieCard";
import { useContext } from "react";
import { Context } from "../../App";
import RecommendedMovieCard from "../../Components/RecommendedMovieCard";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Recommendedmovies = () => {
  const [state, dispatch] = useContext(Context);
  const [movies, setMovies] = useState([]);
  const navigate = useNavigate();
  console.log(movies, "movies");

  useEffect(() => {
    if (state.user_name === null) {
      navigate("/");
    }
    setMovies([]);
    axios
      .get(`http://localhost:8081/customers/${state.user_name}/recommended`)
      .then((res) => {
        console.log(res.data, "recommended");
        res.data.forEach((movie) => {
          axios
            .get(`http://localhost:8081/movies/${movie.movie_id}`)
            .then((res2) => {
              const obj = {
                ...res2.data,
                ...res.data[0],
              };
              let filterMovies = [...movies, obj];
              filterMovies = new Set(filterMovies);
              setMovies(Array.from(filterMovies));
            })
            .catch((err) => {
              console.log(err);
            });
        });
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <div className="allpage bg-black">
      <Navbar name={state.user_name} />
      <div className="container">
        {movies.length > 0 ? (
          movies.map((movie) => (
            <RecommendedMovieCard
              name={state.user_name}
              key={movie.movie_id}
              img_url={movie.img_url}
              title={movie.movie_title}
              description={movie.description}
              rating={movie.avg_rating}
              year={movie.production_year}
              director={movie.director}
              genre={movie.genre}
              price={movie.price}
              id={movie.movie_id}
              message={movie.message}
              recommended_user={movie.recommended_user_name}
            />
          ))
        ) : (
          <h1 className="display-1 text-red text-center">No movies found</h1>
        )}
      </div>
    </div>
  );
};

export default Recommendedmovies;
