function ActorsModal({ actors, id }) {
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
              {actors.map((item, index) => {
                return <h1 key={index}>{`${index + 1}) ${item}`}</h1>;
              })}
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
