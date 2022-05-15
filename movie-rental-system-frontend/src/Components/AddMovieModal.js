import React from "react";
import AddMovieForm from "./AddMovieForm";
const Addmoviemodal = ({ sendAcceptRequest, handleAddMovie }) => {
  return (
    <>
      <button
        className="btn btn-block btn-red btn-lg mb-5"
        data-toggle="modal"
        data-target="#addMovieModal"
      >
        Add Movie
      </button>
      <div
        className="modal fade"
        id="addMovieModal"
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg text-dark">
          <div
            className="modal-content text-left"
            style={{ fontSize: "1.3rem" }}
          >
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                Add Movie
              </h5>
              <button
                type="button"
                className="close"
                data-dismiss="modal"
                aria-label="Close"
              >
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div className="">
              <AddMovieForm handleAddMovie={handleAddMovie} />
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Addmoviemodal;
