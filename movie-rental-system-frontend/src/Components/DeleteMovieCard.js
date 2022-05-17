import React from "react";
import "../stylesheets/MovieCard.css";

const DeleteMovieCard = ({
  img_url,
  title,
  description,
  rating,
  year,
  director,
  genre,
  deleteHandler,
  id,
}) => (
  <div className="card m-auto mb-2">
    <img
      src={
        img_url === undefined
          ? "https://static.wikia.nocookie.net/gora/images/3/39/Er%C5%9FanKuneriDizi.jpeg/revision/latest/top-crop/width/360/height/450?cb=20210619132146&path-prefix=tr"
          : img_url
      }
      className="card-img-top img"
      alt="..."
    />
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
      <button
        href="#"
        className="btn btn-red d-block btn-fluid  ml-auto"
        onClick={() => deleteHandler(id)}
      >
        Delete
      </button>
    </div>
  </div>
);

export default DeleteMovieCard;
