import React, { useState } from "react";
import axios from "axios";

const Reviewinfo = ({
  date,
  user,
  rating,
  review_message,
  spoiler,
  net_like_count,
  review_id,
  movie_id,
  review_date,
}) => {
  const [like_count, set_like_count] = useState(net_like_count);
  const [liked, set_liked] = useState(false);
  const [isSpoilerClicked, set_isSpoilerClicked] = useState(false);

  const like = () => {
    if (liked) {
      axios
        .put(`http://localhost:8081/movie_reviews/${review_id}`, {
          net_like: like_count - 1,
          review_id: review_id,
          movie_id: movie_id,
          review_date: review_date,
          customer_name: user,
          rating: rating,
          review_message: review_message,
          spoiler: spoiler,
        })
        .then((res) => {
          set_like_count(like_count - 1);
          set_liked(false);
        })
        .catch((err) => {
          alert("Error in disliking");
        });
    } else {
      axios
        .put(`http://localhost:8081/movie_reviews/${review_id}`, {
          net_like: like_count + 1,
          review_id: review_id,
          movie_id: movie_id,
          review_date: review_date,
          customer_name: user,
          rating: rating,
          review_message: review_message,
          spoiler: spoiler,
        })
        .then((res) => {
          set_like_count(like_count + 1);
          set_liked(true);
        })
        .catch((err) => {
          alert("Error in liking review");
        });
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
              <div className="col-3 ml-auto">
                <div
                  className="btn btn-red"
                  role="button"
                  aria-expanded="false"
                  aria-controls="collapseExample"
                  onClick={() => set_isSpoilerClicked((prev) => !prev)}
                >
                  SPOILER!!!
                </div>
                <button
                  className={`btn ml-2 border ${
                    liked ? "btn-warning" : "btn-light"
                  }`}
                  onClick={like}
                >
                  {like_count}
                </button>
              </div>
            </div>
            {isSpoilerClicked ? (
              <div id="collapseExample">
                <div
                  className="border p-2 bg-red mt-1 text-light"
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
            ) : null}
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
            <div className="" id="">
              <div className="p-2 bg-red mt-1 text-dark">
                {review_message}
                <div className="mt-1">
                  <h1 style={{ fontSize: "1.4rem" }}>
                    Rating: {rating.toFixed(2) + "/10"}
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
