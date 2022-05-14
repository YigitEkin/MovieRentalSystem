import React from "react";
import ActorsModal from "./ActorsModal";
import "../stylesheets/MovieCard.css";
import Reviewsmodal from "./ReviewsModal";

const MovieCard = ({
  img_url,
  title,
  description,
  rating,
  year,
  director,
  genre,
  price,
  actors,
  id,
  name,
}) => (
  <div className="card box-shadow-card mx-auto my-5">
    <img src={img_url} className="card-img-top img" alt="..." />
    <div className="card-body">
      <div className="row">
        <h5 className="card-title col-10">{title}</h5>
        <h5 className="card-title col-2">{`Rating:  ${rating}`}</h5>
      </div>
      <div className="row">
        <h5 className="card-title col-10">{`Director:  ${director}`}</h5>
        <h5 className="card-title col-2">{"<3"}</h5>
      </div>
      <div className="row">
        <h5 className="card-title col-12">{`Genre:  ${genre}`}</h5>
      </div>
      <p className="card-text">{description}</p>
      <h5 className="card-title col-12 text-right">{`Year:  ${year}`}</h5>
      <div className="row">
        <div className="col-4">
          <ActorsModal actors={actors} id={id} />
        </div>
        <div className="col-4">
          <Reviewsmodal id={id} title={title} name={name} />
        </div>
        <div className="col-4">
          <button href="#" className="btn btn-red btn-block">
            {price + " TL"}
          </button>
        </div>
      </div>
    </div>
  </div>
);

export default MovieCard;
