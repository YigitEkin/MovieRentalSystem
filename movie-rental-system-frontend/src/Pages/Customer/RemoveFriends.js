import React from "react";
import Navbar from "../../Components/NavbarCustomer";
import { useContext, useState, useRef, useEffect } from "react";
import { Context } from "../../App";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const RemoveFriends = ({ id }) => {
  const [state, dispatch] = useContext(Context);
  const navigate = useNavigate();
  const [friends, setFriends] = useState([]);
  const [filteredFriends, setfilteredFriends] = useState(friends);
  const searchBar = useRef(null);

  useEffect(() => {
    if (state.user_name === null) {
      navigate("/");
    }

    axios
      .get(`http://localhost:8081/customers/${state.user_name}/friends`)
      .then((res) => {
        setFriends(res.data);
        setfilteredFriends(res.data);
      })
      .catch((err) => {
        console.log(err);
      });

    // fetching all users
  }, []);

  const handleSearch = () => {
    const searchValue = searchBar.current.value;
    const filteredUsers = friends.filter((user) => {
      return user.user_name.toLowerCase().includes(searchValue.toLowerCase());
    });
    setfilteredFriends(filteredUsers);
  };

  function handleDelete(friendship) {
    const friend_name = friendship.friend_name;
    const customer_name = friendship.customer_name;
    /*
    const filteredUsers = friends.filter((user) => {
      return (
        user.customer_name !== friend_name ||
        user.friend_name !== friend_name ||
        user.customer_name !== customer_name ||
        user.friend_name !== customer_name
      );
    });
    setFriends(filteredUsers);
    setfilteredFriends(filteredUsers);
    */
    const user = friend_name !== state.user_name ? customer_name : friend_name;
    console.log(user);
    axios
      .delete(`http://localhost:8081/customers/${user}/friends/${friend_name}`)
      .then((res) => {
        console.log(res);
        searchBar.current.value = "";
        alert("removed friend successfully");
      })
      .catch((err) => {
        console.log(err);
        alert("Cannot delete friend");
      });

    //TODO: delete user from database
  }

  return (
    <>
      <div className="container mt-5">
        <label
          className="display-4 text-center text-light d-block"
          htmlFor="DeleteUser"
        >
          Friends of {state.user_name}:
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
          {filteredFriends.length > 0 ? (
            filteredFriends.map((user) => (
              <div className="card m-auto" style={{ width: "70%" }}>
                <div
                  className="card-header text-center "
                  style={{
                    backgroundColor: "#FFF",
                    color: "#000",
                    fontSize: "1.5rem",
                  }}
                >
                  {user.friend_name === state.user_name
                    ? user.customer_name
                    : user.friend_name}
                </div>
                <div className="card-body d-flex justify-content-center">
                  <button
                    className="btn btn-danger btn-lg"
                    onClick={() =>
                      handleDelete({
                        customer_name: user.customer_name,
                        friend_name: user.friend_name,
                      })
                    }
                  >
                    Remove Friend
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

export default RemoveFriends;
