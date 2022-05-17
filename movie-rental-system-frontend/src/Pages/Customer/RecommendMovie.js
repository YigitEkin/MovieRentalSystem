import axios from "axios";
import React, { useState, useContext, useRef, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Context } from "../../App";
import Navbar from "../../Components/NavbarCustomer";

const Recommendmovie = () => {
  const [state, dispatch] = useContext(Context);
  const navigate = useNavigate();
  const [friends, setFriends] = useState([]);
  const title = useRef(null);
  const friend = useRef(null);
  const message = useRef(null);

  const handleSubmit = (e) => {
    e.preventDefault();
    let movies = [];
    axios
      .get(`http://localhost:8081/movies`)
      .then((res) => {
        movies = res.data.filter((movie) => {
          return (
            movie.movie_title.toLowerCase() ===
            title.current.value.toLowerCase()
          );
        });
        console.log(movies);
        const recommendation = {
          recommend_id: 0,
          recommender_user_name: state.user_name,
          recommended_user_name: friend.current.value,
          movie_id: movies[0].movie_id,
          message: message.current.value,
        };

        axios
          .post(`http://localhost:8081/recommends`, recommendation)
          .then((res) => {
            alert("Recommendation sent successfully");
            console.log(res);
          })
          .catch((err) => {
            alert("Invalid input");
          });
      })
      .catch((err) => {
        console.log(err);
      });
  };

  //!const user = friend_name !== state.user_name ? customer_name : friend_name;
  useEffect(() => {
    if (state.user_name === null) {
      navigate("/");
    }
    axios
      .get(`http://localhost:8081/customers/${state.user_name}/friends`)
      .then((res) => {
        console.log(res.data, "friends");
        const filteredFriends = res.data.map((friend) => {
          const { friend_name, customer_name } = friend;
          return friend_name === state.user_name ? customer_name : friend_name;
        });
        console.log(filteredFriends, "filteredFriends");
        setFriends(filteredFriends);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);
  return (
    <>
      <Navbar name={state.user_name} />
      <div className="container mt-5">
        <div class="jumbotron jumbotron-fluid">
          <div class="container px-5">
            <form onSubmit={handleSubmit}>
              <h1 class="display-4 text-center">
                Recommend A Movie To Friends
              </h1>
              <div className="row">
                <div className="col-6">
                  <div className="form-group">
                    <label
                      htmlFor="exampleInputEmail1"
                      className="text-center text-dark text-lg"
                    >
                      Movie Title:
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="exampleInputEmail1"
                      ref={title}
                      aria-describedby="emailHelp"
                      placeholder="Example: The Godfather"
                    />
                  </div>
                </div>
                <div className="col-6">
                  <div className="form-group">
                    <>
                      <div className="container">
                        <label
                          className="text-center text-dark text-lg"
                          htmlFor="DeleteUser"
                        >
                          Friends:
                        </label>
                        <select
                          name="friends"
                          ref={friend}
                          className="form-control"
                          id=""
                        >
                          {friends.map((user) => {
                            return (
                              <option key={user} value={user}>
                                {user}
                              </option>
                            );
                          })}
                        </select>
                      </div>
                    </>
                  </div>
                </div>
              </div>
              <div>
                <div className="form-group">
                  <label htmlFor="exampleInputPassword1" className="text-lg">
                    Description:
                  </label>
                  <textarea
                    type="number"
                    placeholder="Give a brief description of the movie"
                    rows={7}
                    min={1920}
                    className="form-control"
                    id="exampleInputPassword1"
                    ref={message}
                  />
                </div>
              </div>
              <button type="submit" className="btn btn-block btn-red">
                Recommend Movie
              </button>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

export default Recommendmovie;
