import React, { useRef } from "react";
import Navbar from "../../Components/NavbarCustomer";

const Requestmovie = ({ name }) => {
  const titleRef = useRef(null);
  const descriptionRef = useRef(null);
  const yearRef = useRef(null);

  function handleSubmit(e) {
    e.preventDefault();
    if (
      titleRef.current.value === "" ||
      descriptionRef.current.value === "" ||
      yearRef.current.value === ""
    ) {
      alert("Please fill in all fields");
    } else {
      const obj = {
        title: titleRef.current.value,
        description: descriptionRef.current.value,
        year: +yearRef.current.value,
        user: name,
        //id: user.id,
      };
      //axios
    }
  }
  return (
    <>
      <Navbar name={name} />
      <div className="container">
        <div className="row">
          <div className="col-md-12 mt-3">
            <h1 className="text-center text-light display-4">Request Movie</h1>
          </div>
          <div className="col-md-12 mt-3">
            <form
              className="p-5 m-auto bg-light"
              style={{ width: "80%" }}
              onSubmit={handleSubmit}
            >
              <div className="form-group">
                <label htmlFor="exampleInputEmail1" className="text-lg">
                  Movie Title:
                </label>
                <input
                  type="text"
                  className="form-control"
                  id="exampleInputEmail1"
                  ref={titleRef}
                  aria-describedby="emailHelp"
                  placeholder="Example: The Godfather"
                />
              </div>
              <div className="form-group">
                <label htmlFor="exampleInputPassword1" className="text-lg">
                  Movie Year:
                </label>
                <input
                  type="number"
                  placeholder="Example: 1972"
                  min={1920}
                  className="form-control"
                  ref={yearRef}
                  id="exampleInputPassword1"
                />
              </div>
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
                  ref={descriptionRef}
                />
              </div>
              <button type="submit" className="btn btn-red btn-block">
                Send Request
              </button>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

export default Requestmovie;
