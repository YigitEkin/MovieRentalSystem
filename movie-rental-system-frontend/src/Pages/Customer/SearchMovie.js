import React, { useEffect, useRef, useState } from "react";
import "../../stylesheets/SearchMovie.css";
import Navbar from "../../Components/NavbarCustomer";
import MovieCard from "../../Components/MovieCard";

const moviesTemp = [
  {
    id: 1,
    title: "The Shawshank Redemption",
    year: "1994",
    genre: "Drama",
    rating: "9.3",
    price: "10",
    img_url:
      "data:image/jpeg;base64,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",
    description:
      "A banker who has a long-term grudge with a rich banker becomes a fugitive in the world of banker/burglar.",
    director: "Frank Darabont",
    actors: ["Tim Robbins", "Morgan Freeman", "Bob Gunton", "William Sadler"],
  },
  {
    id: 2,
    title: "The Godfather",
    year: "1972",
    genre: "Drama",
    rating: "9.2",
    price: "10",
    img_url:
      "data:image/jpeg;base64,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",
    description:
      "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
    director: "Francis Ford Coppola",
    actors: ["Marlon Brando", "Al Pacino", "James Caan", "Diane Keaton"],
  },
  {
    id: 3,
    title: "The Godfather: Part II",
    year: "1974",
    genre: "Drama",
    rating: "9.0",
    price: "10",
    img_url:
      "https://m.media-amazon.com/images/M/MV5BMWMwMGQzZTItY2JlNC00OWZiLWIyMDctNDk2ZDQ2YjRjMWQ0XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
    description:
      "The early life and career of Vito Corleone in 1920s New York is portrayed, while his son, Michael, expands and tightens his grip on the family crime syndicate.",
    director: "Francis Ford Coppola",
    actors: ["Marlon Brando", "Al Pacino", "James Caan", "Diane Keaton"],
  },
  {
    id: 4,
    title: "The Dark Knight",
    year: "2008",
    genre: "Action",
    rating: "9.0",
    price: "10",
    img_url: "https://m.media-amazon.com/images/I/51r4i5VQK3L._AC_SY1000_.jpg",
    description:
      "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, the caped crusader must come to terms with one of the greatest psychological tests of his ability to fight injustice.",
    director: "Christopher Nolan",
    actors: [
      "Christian Bale",
      "Heath Ledger",
      "Aaron Eckhart",
      "Michael Caine",
    ],
  },
];

const SearchMovie = ({ name }) => {
  console.log("mounted");
  const [filter, setFilter] = useState("");
  const [price, setPrice] = useState(false);
  const [rating, setRating] = useState(false);
  const filterElement = useRef(null);
  const [movies, setMovies] = useState(moviesTemp);

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
    //axios.get("/api/movies").then((res) => {
    //  setMovies(res.data);
    //}
    //setMovies(moviesTemp);
  }, []);

  return (
    <div className="allpage bg-black">
      <Navbar name={name} />
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
            name={name}
            key={movie.id}
            img_url={movie.img_url}
            title={movie.title}
            description={movie.description}
            rating={movie.rating}
            year={movie.year}
            director={movie.director}
            genre={movie.genre}
            price={movie.price}
            actors={movie.actors}
            id={movie.id}
          />
        ))}
      </div>
    </div>
  );
};

export default SearchMovie;
