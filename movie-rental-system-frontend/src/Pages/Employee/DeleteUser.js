import React, { useState, useEffect, useRef } from "react";
import Navbar from "../../Components/NavbarEmployee";
import { useContext } from "react";
import { Context } from "../../App";
import { useNavigate } from "react-router-dom";
import axios from "axios";

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

const Deleteuser = () => {
  const [state, dispatch] = useContext(Context);
  const navigate = useNavigate();
  const [users, setusers] = useState([]);
  const [filteredUsers, setfilteredUsers] = useState(users);
  const searchBar = useRef(null);

  useEffect(() => {
    if (state.user_id === null) {
      navigate("/");
    }
    axios
      .get("http://localhost:8081/customers")
      .then((res) => {
        setusers(res.data);
        setfilteredUsers(res.data);
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

  function handleDelete(user_name) {
    axios
      .delete(`http://localhost:8081/customers/${user_name}`)
      .then((res) => {
        const newUser = users.filter((user) => {
          return res.data.user_name !== user.user_name;
        });
        setusers(newUser);
        setfilteredUsers(newUser);
        searchBar.current.value = "";
        alert("User deleted successfully");
      })
      .catch((err) => {
        console.log(err);
      });

    //TODO: delete user from database
  }

  return (
    <>
      <Navbar name={state.user_name} />
      <div className="container">
        <label
          className="display-4 text-center text-light d-block"
          htmlFor="DeleteUser"
        >
          Enter The Name Of The User To Delete:
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
              <div
                key={user.user_name}
                className="card m-auto"
                style={{ width: "70%" }}
              >
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
                    onClick={() => handleDelete(user.user_name)}
                  >
                    Delete
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
export default Deleteuser;
