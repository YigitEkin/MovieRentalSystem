import React, { useState, useEffect } from "react";
import Navbar from "../../Components/NavbarEmployee";
import MovieRequestCard from "../../Components/MovieRequestCard";
import Addmoviemodal from "../../Components/AddMovieModal";
import axios from "axios";
import { useContext } from "react";
import { Context } from "../../App";
import { useNavigate } from "react-router-dom";

function AddMovie() {
  const [state, dispatch] = useContext(Context);
  const navigate = useNavigate();
  const [requests, setRequests] = useState([]);

  useEffect(() => {
    if (state.user_name === null) {
      navigate("/");
    }
    axios
      .get("http://localhost:8081/movie_requests")
      .then((res) => {
        console.log(res.data, "requests");
        setRequests(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  function sendAcceptRequest(item) {}

  function handleAddMovie(
    title,
    year,
    director,
    genre,
    price,
    employee_name,
    actors
  ) {
    const newRequests = requests.filter(
      (request) => request.movie_title.toLowerCase() !== title.toLowerCase()
    );
    setRequests(newRequests);
    const newMovie = {
      movie_title: title,
      production_year: year,
      director: director,
      genre: genre,
      price: price,
      employee_name: employee_name,
      actors: [],
    };
    let Allactors = [];
    axios
      .get("http://localhost:8081/actors")
      .then((res) => {
        Allactors = res.data;
        Allactors.forEach((actor) => {
          console.log(actor.actor_name, "actor");
          console.log(actors, "actors");
          if (actors.includes(actor.actor_name)) {
            newMovie.actors.push(actor.actor_id);
          }
        });
        console.log(newMovie, "newMovie");
        axios.post("http://localhost:8081/movies", newMovie).then((res) => {
          requests.forEach((request) => {
            if (request.movie_title.toLowerCase() === title.toLowerCase()) {
              axios
                .delete(
                  `http://localhost:8081/movie_requests/${request.request_id}`
                )
                .then((res) => {
                  console.log(res, "deleted");
                })
                .catch((err) => {
                  console.log(err);
                });
              console.log(res.data);
            }
          });
        });
        alert("Movie added");
      })
      .catch((err) => {
        alert("Movie not added");
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return (
    <>
      <Navbar name={state.user_name} />
      <div className="container mt-5">
        <h1 className="text-white display1 mb-5 text-center">
          <Addmoviemodal
            sendAcceptRequest={sendAcceptRequest}
            handleAddMovie={handleAddMovie}
          />
          You Have {requests.length} Register Request's
        </h1>
        {requests.map((item) => {
          return (
            <MovieRequestCard
              key={item.request_id}
              title={item.movie_title}
              description={item.description}
              year={item.production_year}
              user={item.customer.user_name}
            />
          );
        })}
      </div>
    </>
  );
}

export default AddMovie;
