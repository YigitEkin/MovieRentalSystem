import React from "react";
import Navbar from "../../Components/NavbarEmployee";

function HomePage({ name }) {
  return (
    <>
      <Navbar name={name} />
    </>
  );
}

export default HomePage;
