import { useRef } from "react";
import React from "react";

//actors
//genre
//image
//price

const Addmovieform = ({ handleAddMovie }) => {
  const title = useRef(null);
  return (
    <form
      className="px-5 py-2"
      onSubmit={() => handleAddMovie(title.current.value)}
    >
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
            className="form-control"
            min={1920}
            id="year"
          />
        </div>
        <div className="form-group col-md-6">
          <label htmlFor="inpu = {true}text4">Genre</label>
          <input
            type="text"
            placeholder="Comedy"
            className="form-control"
            id="input = {true}text4"
          />
        </div>
      </div>
      <div className="form-group">
        <label htmlhtmlFor="textarea1">Description</label>
        <textarea
          rows={5}
          placeholder={"A brief description about the movie"}
          className="form-control"
          id="textarea1"
        />
      </div>
      <div className="form-group">
        <label htmlhtmlFor="textarea2">
          Actors (Enter the actors in comma separted fashion)
        </label>
        <textarea
          placeholder="Ryan Reynolds, Tom Cruise, Elon Musk"
          className="form-control"
          id="textarea2"
        />
      </div>
      <div className="form-row">
        <div className="form-group col-md-6">
          <label htmlFor="price">Price</label>
          <input
            type={"number"}
            min={0}
            placeholder={20.99}
            className="form-control"
            id="price"
          />
        </div>
        <div className="form-group col-md-6">
          <label htmlFor="price">Image</label>
          <input
            type={"file"}
            accept=".jpg, .jpeg, .png"
            className="form-control"
            id="price"
          />
        </div>
      </div>
      <button type="button" className="btn btn-red btn-block btn-lg mb-3">
        Register Movie
      </button>
    </form>
  );
};

export default Addmovieform;
