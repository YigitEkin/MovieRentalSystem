import React from "react";
import "../stylesheets/MovieCard.css";

const MovieRequestCard = ({ title, description, year, user }) => (
  <section className="mb-3">
    <div className="card mb-5 m-auto">
      <div className="card-body">
        <h2 className="card-title">{`${user}'s Movie Request`}</h2>
        <div className="row">
          <h5 className="card-title col-6">{"Title: " + title}</h5>
        </div>
        <h5 className="card-title">Description: </h5>
        <p className="card-text">{description}</p>
        <h5 className="card-title col-12 text-right">{`Year:  ${year}`}</h5>
        <div className="row"></div>
      </div>
    </div>
  </section>
);

export default MovieRequestCard;
