import React, { useState, useEffect, useRef } from "react";
import Navbar from "../../Components/NavbarEmployee";
import { useContext } from "react";
import { Context } from "../../App";
import axioss from "axios";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const AddFriend = ({ id }) => {
  const [state, dispatch] = useContext(Context);
  const [users, setusers] = useState([]);
  const [filteredUsers, setfilteredUsers] = useState(users);
  const searchBar = useRef(null);

  useEffect(() => {
    axios
      .get(`http://localhost:8081/customers`)
      .then((res) => {
        const users = res.data.filter((user) => user.user_name !== id);
        axios
          .get(`http://localhost:8081/customers/${state.user_name}/friends`)
          .then((res) => {
            const friends = res.data;
            const filteredUsers = users.filter((user) => {
              let found = false;
              friends.forEach((friend) => {
                if (
                  friend.customer_name === user.user_name ||
                  user.user_name === friend.friend_name
                ) {
                  found = true;
                }
              });
              if (!found) {
                return user;
              }
            });
            setusers(filteredUsers);
            setfilteredUsers(filteredUsers);
          });
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const handleSearch = () => {
    const searchValue = searchBar.current.value;
    const filteredUsers = users.filter((user) => {
      return user.user_name.toLowerCase().includes(searchValue.toLowerCase());
    });
    setfilteredUsers(filteredUsers);
  };

  function handleDelete(id) {
    const filteredUsers = users.filter((user) => {
      return user.user_name !== id;
    });
    axios.post(`http://localhost:8081/friends`, {
      customer_name: state.user_name,
      friend_name: id,
      friend_request_date: new Date(),
    });

    setusers(filteredUsers);
    setfilteredUsers(filteredUsers);
    searchBar.current.value = "";
  }

  return (
    <>
      <div className="container mt-5">
        <label
          className="display-4 text-center text-light d-block"
          htmlFor="DeleteUser"
        >
          Users:
        </label>
        <input
          className="form-control mb-2 m-auto"
          Placeholder="Example: JohnWick2"
          onChange={handleSearch}
          type="text"
          name="DeleteUser"
          id="DeleteUser"
          style={{ width: "70%" }}
          ref={searchBar}
        />
        <ul className="list-group m-auto">
          {filteredUsers.length > 0 ? (
            filteredUsers.map((user) => (
              <div className="card m-auto" style={{ width: "70%" }}>
                <div
                  className="card-header text-center "
                  style={{
                    backgroundColor: "#FFF",
                    color: "#000",
                    fontSize: "1.5rem",
                  }}
                >
                  {user.user_name}
                </div>
                <div className="card-body d-flex justify-content-center">
                  <button
                    className="btn btn-success btn-lg"
                    onClick={() => handleDelete(user.user_name)}
                  >
                    Add Friend
                  </button>
                </div>
              </div>
            ))
          ) : (
            <h1 className="display-4 text-center text-red">No Users Found</h1>
          )}
        </ul>
      </div>
    </>
  );
};
export default AddFriend;
