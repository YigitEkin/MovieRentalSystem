import React, { useEffect, useRef, useState } from "react";
import "../../stylesheets/SearchMovie.css";
import Navbar from "../../Components/NavbarCustomer";
import MovieCard from "../../Components/MovieCard";
import RentedMovieCard from "../../Components/RentedMovieCard";
import { useContext } from "react";
import { Context } from "../../App";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const SearchMovie = () => {
  const navigate = useNavigate();
  const [state, dispatch] = useContext(Context);
  const clickedNav = { backgroundColor: "red", color: "white" };
  const unCLickedNav = { backgroundColor: "white", color: "red" };
  const [isFavouritesShown, setFavouritesShown] = useState(true);
  const [favourites, setFavourites] = useState([]);
  const [rented, setRented] = useState([]);

  useEffect(() => {
    if (state.user_name === null) {
      navigate("/");
    }
    axios
      .get(`http://localhost:8081/customers/${state.user_name}/favorites`)
      .then((res) => {
        setFavourites(res.data);
      })
      .catch((err) => {
        console.log(err);
      });

    axios
      .get(`http://localhost:8081/customers/${state.user_name}/rents`)
      .then((res) => {
        setRented(res.data);
        console.log(res.data, "rented");
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <div className="allpage bg-black">
      <Navbar name={state.user_name} />
      <div className="mainContent container mt-5">
        <nav class="nav nav-pills nav-justified">
          <div
            class="nav-link"
            style={isFavouritesShown ? clickedNav : unCLickedNav}
            onClick={() => setFavouritesShown(true)}
          >
            Favourites
          </div>
          <div
            class="nav-link"
            style={isFavouritesShown ? unCLickedNav : clickedNav}
            onClick={() => setFavouritesShown(false)}
          >
            Rented Movies
          </div>
        </nav>
        {isFavouritesShown ? (
          favourites.length > 0 ? (
            favourites.map((movie) => (
              <MovieCard
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
                actors={movie.actors}
                id={movie.movie_id}
              />
            ))
          ) : (
            <h1 className="display-1 text-red text-center">
              You have no favourites
            </h1>
          )
        ) : rented.length > 0 ? (
          rented.map((movie) => (
            <RentedMovieCard
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
          <h1 className="display-1 text-red text-center">
            You have no rented movies
          </h1>
        )}
      </div>
    </div>
  );
};

export default SearchMovie;
