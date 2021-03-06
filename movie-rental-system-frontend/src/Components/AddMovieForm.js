import { useRef, useContext } from "react";
import { Context } from "../App";
import React from "react";

//actors
//genre
//image
//price

const Addmovieform = ({ handleAddMovie, sendAcceptRequest }) => {
  const [state, dispatch] = useContext(Context);
  const title = useRef(null);
  const director = useRef(null);
  const year = useRef(null);
  const genre = useRef(null);
  const actors = useRef(null);
  const price = useRef(null);
  const image = useRef(null);

  // write a function to check whether the string contains , or not
  const checkComma = (str) => {
    if (str.includes(",")) {
      return true;
    } else {
      return false;
    }
  };

  const handleActors = () => {
    const actorsArray = actors.current.value.split(",");

    if (actorsArray.length === 1 && !checkComma(actorsArray[0])) {
      return null;
    }
    const finalActorsArray = actorsArray.map((actor) => {
      return actor.trim();
    });
    return finalActorsArray;
  };

  function addMovie() {
    console.log(title.current.value, "title");
    console.log(director.current.value, "director");
    console.log(year.current.value, "year");
    console.log(genre.current.value, "genre");
    console.log(actors.current.value, "actors");
    console.log(price, "price");
    console.log(image.current.value, "image");

    const isFormValid =
      handleActors() !== null &&
      title.current.value !== "" &&
      director.current.value !== "" &&
      year.current.value !== "" &&
      genre.current.value !== "" &&
      actors.current.value !== "" &&
      price.current?.value !== "" &&
      image.current.value !== "";

    console.log(isFormValid);
    if (isFormValid) {
      const movie = {
        title: title.current.value,
        director: director.current.value,
        year: +year.current.value,
        genre: genre.current.value,
        actors: handleActors(),
        price: +price.current.value,
        image: image.current.value,
      };
      handleAddMovie(
        title.current.value,
        +year.current.value,
        director.current.value,
        genre.current.value,
        +price.current.value,
        state.user_name,
        handleActors(),
        image.current.value
      );
      sendAcceptRequest(movie);

      title.current.value = "";
      director.current.value = "";
      year.current.value = "";
      genre.current.value = "";
      actors.current.value = "";
      price.current.value = "";
      image.current.value = "";
    } else {
      alert("Bad credentials");
    }
  }

  return (
    <form className="px-5 py-2">
      <div className="form-row">
        <div className="form-group col-md-6">
          <label htmlFor="title">Title</label>
          <input
            type="text"
            ref={title}
            placeholder="A Movie Title"
            className="form-control"
            id="title"
          />
        </div>
        <div className="form-group col-md-6">
          <label htmlFor="director">Director</label>
          <input
            type="text"
            ref={director}
            placeholder="Quentin Tarantino"
            className="form-control"
            id="director"
          />
        </div>
      </div>
      <div className="form-row">
        <div className="form-group col-md-6">
          <label htmlFor="year">Production Year</label>
          <input
            type={"number"}
            ref={year}
            className="form-control"
            min={1920}
            id="year"
          />
        </div>
        <div className="form-group col-md-6">
          <label htmlFor="inpu = {true}text4">Genre</label>
          <input
            type="text"
            ref={genre}
            placeholder="Comedy"
            className="form-control"
            id="input = {true}text4"
          />
        </div>
      </div>
      <div className="form-group">
        <label htmlFor="textarea2">
          Actors (Enter the actors in comma separted fashion)
        </label>
        <textarea
          placeholder="Ryan Reynolds, Tom Cruise, Elon Musk"
          className="form-control"
          ref={actors}
          id="textarea2"
        />
      </div>
      <div className="form-row">
        <div className="form-group col-md-6">
          <label htmlFor="price">Price</label>
          <input
            type="number"
            min={0}
            placeholder={20.99}
            ref={price}
            className="form-control"
            id="price"
          />
        </div>
        <div className="form-group col-md-6">
          <label htmlFor="price">Image</label>
          <input
            type={"text"}
            ref={image}
            className="form-control"
            id="price"
          />
        </div>
      </div>
      <button
        type="button"
        className="btn btn-red btn-block btn-lg mb-3"
        onClick={addMovie}
      >
        Register Movie
      </button>
    </form>
  );
};

export default Addmovieform;
