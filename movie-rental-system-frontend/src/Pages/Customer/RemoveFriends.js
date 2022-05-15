import React from "react";
import Navbar from "../../Components/NavbarCustomer";
import { useContext, useState, useRef, useEffect } from "react";
import { Context } from "../../App";

const friendsTemp = [
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

const RemoveFriends = () => {
  const [state, dispatch] = useContext(Context);
  const [friends, setFriends] = useState(friendsTemp);
  const [filteredFriends, setfilteredFriends] = useState(friends);
  const searchBar = useRef(null);

  useEffect(() => {
    // fetching all users
  }, []);

  const handleSearch = () => {
    const searchValue = searchBar.current.value;
    const filteredUsers = friends.filter((user) => {
      return user.user_name.toLowerCase().includes(searchValue.toLowerCase());
    });
    setfilteredFriends(filteredUsers);
  };

  function handleDelete(id) {
    const filteredUsers = friends.filter((user) => {
      return user.id !== id;
    });
    setFriends(filteredUsers);
    setfilteredFriends(filteredUsers);
    searchBar.current.value = "";
    alert("removed friend successfully");
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
                  {user.user_name}
                </div>
                <div className="card-body d-flex justify-content-center">
                  <button
                    className="btn btn-danger btn-lg"
                    onClick={() => handleDelete(user.id)}
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
