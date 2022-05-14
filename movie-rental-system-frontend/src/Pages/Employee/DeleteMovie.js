import React, { useState, useEffect, useRef } from "react";
import Navbar from "../../Components/NavbarEmployee";
import Logo from "../../images/gora.jpeg";
import DeleteMovieCard from "../../Components/DeleteMovieCard";
import { useContext } from "react";
import { Context } from "../../App";

const tempMovies = [
  {
    id: 1,
    img_url: Logo,
    title: "abc",
    rating: 8.1,
    description:
      "A slick young Turk kidnapped by extraterrestrials shows his great « humanitarian spirit » by outwitting the evil commander-in-chief of the planet of G.O.R.A.",
    year: 2003,
    director: "Ömer Faruk Sorak",
    genre: "Comedy",
  },
  {
    id: 2,
    img_url: Logo,
    title: "abcde",
    rating: 8.1,
    description:
      "A slick young Turk kidnapped by extraterrestrials shows his great « humanitarian spirit » by outwitting the evil commander-in-chief of the planet of G.O.R.A.",
    year: 2003,
    director: "Ömer Faruk Sorak",
    genre: "Comedy",
  },
  {
    id: 3,
    img_url: Logo,
    title: "abcdef",
    rating: 8.1,
    description:
      "A slick young Turk kidnapped by extraterrestrials shows his great « humanitarian spirit » by outwitting the evil commander-in-chief of the planet of G.O.R.A.",
    year: 2003,
    director: "Ömer Faruk Sorak",
    genre: "Comedy",
  },
];

const DeleteMovie = () => {
  const [state, dispatch] = useContext(Context);
  const [movies, setMovies] = useState(tempMovies);
  const [filteredMovies, setfilteredMovies] = useState(movies);
  const searchBar = useRef(null);

  useEffect(() => {
    // fetching all movies
  }, []);

  const handleSearch = () => {
    const searchValue = searchBar.current.value;
    const filteredMovies = movies.filter((movie) => {
      return movie.title.toLowerCase().includes(searchValue.toLowerCase());
    });
    setfilteredMovies(filteredMovies);
  };

  function handleDelete(id) {
    const filteredMovies = movies.filter((movie) => {
      return movie.id !== id;
    });
    setMovies(filteredMovies);
    setfilteredMovies(filteredMovies);
    searchBar.current.value = "";
    alert("Movie deleted successfully");
    //TODO: delete movie from database
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
                  title={movie.title}
                  description={movie.description}
                  rating={movie.rating}
                  year={movie.year}
                  director={movie.director}
                  genre={movie.genre}
                  deleteHandler={handleDelete}
                  id={movie.id}
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
