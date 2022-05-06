import React, { useEffect, useState } from "react";
import Reviewinfo from "./ReviewInfo";

//rating *
//review_message
//spoiler
//user *
//date *
//review_id *
//net_like_count

const reviewsT = [
  {
    review_id: 1,
    date: "2020-01-01",
    user: "user1",
    rating: 5,
    review_message:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad nostrum laborum rem reprehenderit neque dolores ullam pariatur nam omnis impedit.",
    spoiler: false,
    net_like_count: 0,
  },
  {
    review_id: 2,
    date: "2020-01-01",
    user: "user2",
    rating: 5,
    review_message:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad nostrum laborum rem reprehenderit neque dolores ullam pariatur nam omnis impedit.",
    spoiler: false,
    net_like_count: 0,
  },
  {
    review_id: 3,
    date: "2020-01-01",
    user: "user3",
    rating: 5,
    review_message:
      "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad nostrum laborum rem reprehenderit neque dolores ullam pariatur nam omnis impedit.",
    spoiler: true,
    net_like_count: 0,
  },
];
const Reviewsmodal = ({ id, title }) => {
  const [reviews, setReviews] = useState(reviewsT);

  useEffect(() => {
    //TODO: fetch reviews for a spesific movie
    /** *
        fetch(`http://localhost:5000/api/reviews/${id}`)
            .then(res => res.json())
            .then(data => {
                setReviews(data);
            }
            );
            */
  }, []);

  return (
    <>
      <button
        type="button"
        class="btn btn-block btn-warning"
        data-toggle="modal"
        data-target={`#reviewsModal${id}`}
      >
        Reviews
      </button>

      <div
        class="modal fade"
        id={`reviewsModal${id}`}
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title" id="exampleModalLabel">
                Reviews for {title}
              </h5>
              <button
                type="button"
                class="close"
                data-dismiss="modal"
                aria-label="Close"
              >
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              {reviews.map((review) => (
                <Reviewinfo
                  key={review.id}
                  date={review.date}
                  user={review.user}
                  rating={review.rating}
                  review_message={review.review_message}
                  spoiler={review.spoiler}
                  net_like_count={review.net_like_count}
                />
              ))}
            </div>
            <div class="modal-footer"></div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Reviewsmodal;
