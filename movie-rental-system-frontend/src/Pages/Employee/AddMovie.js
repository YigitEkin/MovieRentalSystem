import React, { useState, useEffect } from "react";
import Navbar from "../../Components/NavbarEmployee";
import MovieRequestCard from "../../Components/MovieRequestCard";
import Addmoviemodal from "../../Components/AddMovieModal";
import axios from "axios";
import { useContext } from "react";
import { Context } from "../../App";

function AddMovie() {
  const [state, dispatch] = useContext(Context);
  const [requests, setRequests] = useState([
    {
      id: 1,
      title: "Gora",
      description:
        "A slick young Turk kidnapped by extraterrestrials shows his great « humanitarian spirit » by outwitting the evil commander-in-chief of the planet of G.O.R.A.",
      year: 2003,
      user: "Yigit",
    },
    {
      id: 2,
      title: "Gora2",
      description:
        "A slick young Turk kidnapped by extraterrestrials shows his great « humanitarian spirit » by outwitting the evil commander-in-chief of the planet of G.O.R.A.",
      year: 2003,
      user: "Yigit",
    },
    {
      id: 3,
      title: "Gora3",
      description:
        "A slick young Turk kidnapped by extraterrestrials shows his great « humanitarian spirit » by outwitting the evil commander-in-chief of the planet of G.O.R.A.",
      year: 2003,
      user: "Yigit",
    },
  ]);

  useEffect(() => {
    //axios.get()
  }, []);

  function sendAcceptRequest(item) {
    //axios //!Date eklemeyi unutma
  }

  function handleAddMovie(title) {
    const newRequests = requests.filter(
      (request) => request.title.toLowerCase() !== title.toLowerCase()
    );
    setRequests(newRequests);

    //TODO: implement add movie to database
    //sendAcceptRequest(item);
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
              key={item.id}
              title={item.title}
              description={item.description}
              year={item.year}
              user={item.user}
            />
          );
        })}
      </div>
    </>
  );
}

export default AddMovie;
