import React, { useState, useContext, useRef, useEffect } from "react";
import { Context } from "../../App";
import Navbar from "../../Components/NavbarCustomer";

const tempUsers = [
  {
    id: 1,
    user_name: "John",
  },
  {
    id: 2,
    user_name: "Jane",
  },
  {
    id: 3,
    user_name: "Jack",
  },
  {
    id: 4,
    user_name: "Jill",
  },
];

const Recommendmovie = () => {
  const [state, dispatch] = useContext(Context);
  const [friends, setFriends] = useState(tempUsers);
  const title = useRef(null);
  const friend = useRef(null);

  return (
    <>
      <Navbar name={state.user_name} />
      <div className="container mt-5">
        <div class="jumbotron jumbotron-fluid">
          <div class="container">
            <h1 class="display-4 text-center">Recommend A Movie To Friends</h1>
            <div className="row" style={{ backgroundColor: "red" }}>
              <div className="col-6">
                <div className="form-group">
                  <label
                    htmlFor="exampleInputEmail1"
                    className="text-center text-light text-lg"
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
                    <div
                      className="container"
                      style={{ backgroundColor: "red" }}
                    >
                      <label
                        className="text-center text-light text-lg"
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
                            <option key={user.id} value={user.user_name}>
                              {user.user_name}
                            </option>
                          );
                        })}
                      </select>
                    </div>
                  </>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Recommendmovie;
