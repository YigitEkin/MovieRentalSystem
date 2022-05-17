import React, { useState, useEffect, useRef } from "react";
import Navbar from "../../Components/NavbarEmployee";
import Logo from "../../images/gora.jpeg";
import DeleteMovieCard from "../../Components/DeleteMovieCard";
import { useContext } from "react";
import { Context } from "../../App";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const DeleteMovie = () => {
  const [state, dispatch] = useContext(Context);
  const navigate = useNavigate();
  const [movies, setMovies] = useState([]);
  const [filteredMovies, setfilteredMovies] = useState(movies);
  const searchBar = useRef(null);

  useEffect(() => {
    if (state.user_name === null) {
      navigate("/");
    }
    axios
      .get("http://localhost:8081/movies")
      .then((res) => {
        setMovies(res.data);
        setfilteredMovies(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
    // fetching all movies
  }, []);

  const handleSearch = () => {
    const searchValue = searchBar.current.value;
    const filteredMovies = movies.filter((movie) => {
      return movie.movie_title
        .toLowerCase()
        .includes(searchValue.toLowerCase());
    });
    setfilteredMovies(filteredMovies);
  };

  function handleDelete(id) {
    axios
      .delete(`http://localhost:8081/movies/${id}`)
      .then((res) => {
        console.log(res);
        alert("Movie deleted successfully");
      })
      .catch((err) => {
        console.log(err.response);
        alert("Movie not deleted");
      });

    const filteredMovies = movies.filter((movie) => {
      return movie.movie_id !== id;
    });
    setMovies(filteredMovies);
    setfilteredMovies(filteredMovies);
    searchBar.current.value = "";
  }

  return (
    <>
      <Navbar name={state.user_name} />
      <div className="container">
        <label
          className="display-4 text-center text-light d-block"
          htmlFor="DeleteMovie"
        >
          Enter The Name Of The Movie To Delete:
        </label>
        <input
          className="form-control mb-4"
          Placeholder="Example: Paddington 2"
          onChange={handleSearch}
          type="text"
          name="DeleteMovie"
          id="DeleteMovie"
          style={{ width: "70%", margin: "auto" }}
          ref={searchBar}
        />
        <ul className="list-group m-auto">
          {filteredMovies.length > 0 ? (
            filteredMovies.map((movie) => {
              return (
                <DeleteMovieCard
                  img_url={movie.img_url}
                  title={movie.movie_title}
                  description={movie.description}
                  rating={movie.avg_rating}
                  year={movie.production_year}
                  director={movie.director}
                  genre={movie.genre}
                  deleteHandler={handleDelete}
                  id={movie.movie_id}
                />
              );
            })
          ) : (
            <h1 className="display-4 text-center text-red">No movies Found</h1>
          )}
        </ul>
      </div>
    </>
  );
};
export default DeleteMovie;
