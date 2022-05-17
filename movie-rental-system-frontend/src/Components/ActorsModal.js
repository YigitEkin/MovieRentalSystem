import axios from "axios";
import { useEffect, useState } from "react";
function ActorsModal({ id }) {
  const [actors, setActors] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:8081/movies/${id}/actors`).then((res) => {
      console.log(res.data, "actors");
      setActors(res.data);
    });
  }, []);
  return (
    <>
      <button
        className="btn btn-light btn-block border"
        data-toggle="modal"
        data-target={`#actorsModal${id}`}
      >
        Actors
      </button>
      <div
        className="modal fade"
        id={`actorsModal${id}`}
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                Actors:
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
            <div className="modal-body">
              {actors?.length > 0 ? (
                actors.map((item, index) => {
                  return (
                    <div key={index}>
                      <p>{item.actor_name}</p>
                    </div>
                  );
                })
              ) : (
                <h1>No Actors</h1>
              )}
            </div>
            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-secondary"
                data-dismiss="modal"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default ActorsModal;
