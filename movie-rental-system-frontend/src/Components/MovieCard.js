import React, { useEffect, useState } from "react";
import ActorsModal from "./ActorsModal";
import "../stylesheets/MovieCard.css";
import Reviewsmodal from "./ReviewsModal";
import { useContext } from "react";
import { Context } from "../App";
import axios from "axios";

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
}) => {
  const [state, dispatch] = useContext(Context);
  const FavouriteStyle = {
    backgroundColor: "red",
    color: "white",
    border: "5px solid white",
    borderRadius: "50%",
  };
  const notFavouriteStyle = {
    backgroundColor: "white",
    color: "black",
    border: "5px solid red",
    borderRadius: "5px",
  };

  const [isFavourite, setIsFavourite] = useState(false);
  useEffect(() => {
    axios
      .get(`http://localhost:8081/customers/${state.user_name}/favorites`)
      .then((res) => {
        console.log(res.data, "favourites");
        res.data.forEach((movie) => {
          if (movie.movie_id === id) {
            setIsFavourite(true);
          }
        });
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  useEffect(() => {
    //if isFavourite is true, remove the movie from favourites in the backend
    //else add the movie to favourites in the backend
    if (isFavourite) {
      //add to favourites
    } else {
      //remove from favourites
    }
  }, [isFavourite]);

  const handleFavourite = (id) => {
    if (isFavourite) {
      axios
        .delete(
          `http://localhost:8081/customers/${state.user_name}/favorites/${id}`
        )
        .then((res) => {
          console.log(res.data, "delete");
          setIsFavourite(false);
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      axios
        .post(
          `http://localhost:8081/customers/${state.user_name}/favorites/${id}`
        )
        .catch((err) => {
          alert("unable to add to favourites");
          console.log(err);
        });
    }
    setIsFavourite(!isFavourite);
  };
  return (
    <div className="card box-shadow-card mx-auto my-5">
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
          <h5 className="card-title col-10">{title}</h5>
          <h5 className="card-title col-2">{`Rating:  ${rating?.toFixed(
            2
          )}`}</h5>
        </div>
        <div className="row">
          <h5 className="card-title col-10">{`Director:  ${director}`}</h5>
          <h5
            className="card-title col-2"
            style={isFavourite ? FavouriteStyle : notFavouriteStyle}
            onClick={() => handleFavourite(id)}
          >
            {"Favourite"}
          </h5>
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
            <button
              className="btn btn-red btn-block"
              onClick={() => {
                dispatch({
                  type: "SET_CART",
                  payload: { id: id, title: title, price: +price },
                });
              }}
            >
              {price + " TL"}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default MovieCard;
