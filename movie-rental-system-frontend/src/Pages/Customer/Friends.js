import React from "react";
import Navbar from "../../Components/NavbarCustomer";
import { useContext } from "react";
import { Context } from "../../App";

const Friends = () => {
  const [state, dispatch] = useContext(Context);
  return (
    <>
      <Navbar name={state.user_name} />
      <h1 className="display-1">FRİENDS</h1>
    </>
  );
};

export default Friends;
