import React from "react";
import "../stylesheets/MovieCard.css";

const MovieCard = ({
  img_url,
  title,
  description,
  rating,
  year,
  director,
  genre,
  price,
}) => (
  <div className="card box-shadow m-auto">
    <img src={img_url} className="card-img-top img" alt="..." />
    <div className="card-body">
      <div className="row">
        <h5 className="card-title col-6">{title}</h5>
        <h5 className="card-title col-6 text-right ">{`Rating:  ${rating}/10`}</h5>
      </div>
      <div className="row">
        <h5 className="card-title col-12">{`Director:  ${director}`}</h5>
        <h5 className="card-title col-12">{`Genre:  ${genre}`}</h5>
      </div>
      <p className="card-text">{description}</p>
      <h5 className="card-title col-12 text-right">{`Year:  ${year}`}</h5>
      <button href="#" className="btn btn-red d-block ml-auto">
        {price}
      </button>
    </div>
  </div>
);

export default MovieCard;
