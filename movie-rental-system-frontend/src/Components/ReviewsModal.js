import React, { useEffect, useState, useRef } from "react";
import Reviewinfo from "./ReviewInfo";
import axios from "axios";

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
const Reviewsmodal = ({ id, title, name }) => {
  const [reviews, setReviews] = useState([]);
  const [isSpoiler, setIsSpoiler] = useState(false);
  const review_message = useRef();
  const rating = useRef();

  useEffect(() => {
    axios.get(`http://localhost:8081/movies/${id}/reviews`).then((res) => {
      setReviews(res.data);
    });
  }, []);

  function validateForm() {
    return (
      review_message.current.value.length > 0 &&
      rating.current.value >= 0 &&
      rating.current.value <= 10
    );
  }

  function addReview() {
    if (validateForm()) {
      /*
         private Long review_id;
    private Integer movie_id;
    private String customer_name;
    private Double rating;
    private String review_message;
    private boolean spoiler;
    private Integer net_like;
    private String review_date;
      */
      const review = {
        review_id: reviews.length + 1,
        movie_id: id,
        review_date: new Date().toISOString().slice(0, 10),
        customer_name: name,
        rating: +rating.current.value,
        review_message: review_message.current.value,
        spoiler: isSpoiler,
        net_like: 0,
      };
      axios
        .post(`http://localhost:8081/movie_reviews`, review)
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          alert("Error in adding review");
        });

      setReviews([...reviews, review]);
      review_message.current.value = "";
      rating.current.value = "";
      setIsSpoiler(false);
      //send review to server
    } else {
      alert("Invalid form");
    }
  }

  return (
    <>
      <button
        type="button"
        className="btn btn-block btn-warning"
        data-toggle="modal"
        data-target={`#reviewsModal${id}`}
      >
        Reviews
      </button>

      <div
        className="modal fade"
        id={`reviewsModal${id}`}
        tabIndex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog modal-dialog-centered modal-dialog-scrollable modal-lg">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                Reviews for {title}
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
              {reviews.map((review, index) => (
                <Reviewinfo
                  key={index}
                  date={review.review_date}
                  user={review.customer_name}
                  rating={review.rating}
                  review_message={review.review_message}
                  spoiler={review.spoiler}
                  net_like_count={review.net_like}
                  index={index}
                  review_date={review.review_date}
                  movie_id={review.movie_id}
                  review_id={review.review_id}
                />
              ))}
            </div>
            <div className="modal-footer d-flex justify-content-between">
              <div className="form-group">
                <input
                  type="number"
                  min={0}
                  max={10}
                  step={0.1}
                  name=""
                  id=""
                  placeholder="rating p-2"
                  className="form-control"
                  ref={rating}
                />
              </div>
              <div className="form-group">
                <textarea
                  name=""
                  id=""
                  placeholder="review"
                  className="form-control p-2"
                  ref={review_message}
                />
              </div>
              <div className="form-group">
                <div
                  className={`btn btn-fluid p-2 ${
                    !isSpoiler ? "btn-light border" : "btn-warning"
                  }`}
                  onClick={() => setIsSpoiler((prev) => !prev)}
                >
                  Spoiler
                </div>
              </div>
              <button
                className="btn btn-block d-block btn-success col-12"
                onClick={addReview}
              >
                Add Review
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default Reviewsmodal;
