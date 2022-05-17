import React, { useEffect, useRef, useState } from "react";
import "../../stylesheets/SearchMovie.css";
import Navbar from "../../Components/NavbarCustomer";
import MovieCard from "../../Components/MovieCard";
import RentedMovieCard from "../../Components/RentedMovieCard";
import { useContext } from "react";
import { Context } from "../../App";
import AddFriend from "./AddFriends";
import RemoveFriends from "./RemoveFriends";
import { useNavigate } from "react-router-dom";

const Friends = () => {
  const [state, dispatch] = useContext(Context);
  const navigate = useNavigate();
  const clickedNav = { backgroundColor: "red", color: "white" };
  const unCLickedNav = { backgroundColor: "white", color: "red" };
  const [isAddFriendsShown, setIsAddFriendsShown] = useState(true);

  useEffect(() => {
    if (state.user_name === null) {
      navigate("/");
    }
    //TODO: fetch movies from API (rented movies)
    //axios.get("/api/movies").then((res) => {
    //  setMovies(res.data);
    //}
    //setMovies(moviesTemp);
  }, []);

  return (
    <div className="allpage bg-black">
      <Navbar name={state.user_name} />
      <div className="mainContent container mt-5">
        <nav class="nav nav-pills nav-justified">
          <div
            class="nav-link"
            style={isAddFriendsShown ? clickedNav : unCLickedNav}
            onClick={() => setIsAddFriendsShown(true)}
          >
            Add Friends
          </div>
          <div
            class="nav-link"
            style={isAddFriendsShown ? unCLickedNav : clickedNav}
            onClick={() => setIsAddFriendsShown(false)}
          >
            Remove Friends
          </div>
        </nav>
        {isAddFriendsShown ? (
          <AddFriend id={state.user_name} />
        ) : (
          <RemoveFriends id={state.user_name} />
        )}
      </div>
    </div>
  );
};

export default Friends;
