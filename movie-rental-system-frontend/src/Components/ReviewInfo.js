import React, { useState } from "react";

const Reviewinfo = ({
  date,
  user,
  rating,
  review_message,
  spoiler,
  net_like_count,
}) => {
  const [like_count, set_like_count] = useState(net_like_count);
  const [liked, set_liked] = useState(false);

  const like = () => {
    if (liked) {
      set_like_count(like_count - 1);
      set_liked(false);
    } else {
      set_like_count(like_count + 1);
      set_liked(true);
    }
  };

  return (
    <>
      {spoiler ? (
        <>
          <div className="border mb-2 p-2">
            <div className="row">
              <p className="d-inline col-8 ml-2">
                {user}'s review from {date}
              </p>
              <p className="col-3 ml-auto">
                <a
                  class="btn btn-red"
                  data-toggle="collapse"
                  href="#collapseExample"
                  role="button"
                  aria-expanded="false"
                  aria-controls="collapseExample"
                >
                  SPOILER!!!
                </a>
                <button
                  className={`btn ml-2 border ${
                    liked ? "btn-warning" : "btn-light"
                  }`}
                  onClick={like}
                >
                  {like_count}
                </button>
              </p>
            </div>
            <div class="collapse" id="collapseExample">
              <div
                class="border p-2 bg-red mt-1 text-light"
                style={{ backgroundColor: "red" }}
              >
                {review_message}
                <div className="mt-1">
                  <h1 style={{ fontSize: "1.4rem" }}>
                    Rating: {rating + "/10"}
                  </h1>
                </div>
              </div>
            </div>
          </div>
        </>
      ) : (
        <>
          <div className="border mb-2 p-2">
            <div className="row">
              <p className="d-inline col-10 ml-2">
                {user}'s review from {date}
              </p>
              <p className="col-1 ml-4">
                <button
                  className={`btn btn-block btn-fluid ml-2 border ${
                    liked ? "btn-warning" : "btn-light"
                  }`}
                  onClick={like}
                >
                  {like_count}
                </button>
              </p>
            </div>
            <div class="" id="">
              <div class="p-2 bg-red mt-1 text-dark">
                {review_message}
                <div className="mt-1">
                  <h1 style={{ fontSize: "1.4rem" }}>
                    Rating: {rating + "/10"}
                  </h1>
                </div>
              </div>
            </div>
          </div>
        </>
      )}
    </>
  );
};

export default Reviewinfo;
