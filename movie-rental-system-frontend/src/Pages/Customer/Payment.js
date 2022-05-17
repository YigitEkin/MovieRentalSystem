import React from "react";
import "../../stylesheets/Payment.css";
import Navbar from "../../Components/NavbarCustomer";
import { useContext, useState, useRef, useEffect } from "react";
import { Context } from "../../App";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const cardsTemp = [
  {
    card_id: 1,
    card_number: "Visa",
    exp_date: "12/20",
    cvv: "123",
    holder_name: "John Doe",
  },
  {
    card_id: 2,
    card_number: "Mastercard",
    exp_date: "12/20",
    cvv: "123",
    holder_name: "John Doe2",
  },
];

function Payment() {
  const ccv = useRef(null);
  const card_no = useRef(null);
  const holder_name = useRef(null);
  const exp_date = useRef(null);

  const [cards, setCards] = useState(cardsTemp);
  const [state, dispatch] = useContext(Context);
  const navigate = useNavigate();
  let cartTotal = state.cart.reduce((total, movie) => {
    return total + movie.price;
  }, 0);
  cartTotal = cartTotal || 0;

  useEffect(() => {
    if (state.user_name === null) {
      navigate("/");
    }
  }, []);
  return (
    <div className="allpage">
      <Navbar name={state.user_name} />
      <div className="maincontent container mt-5">
        <ul className="list-group m-auto">
          {console.log(state.cart, "cart")}
          {console.log("cartTotal", cartTotal)}
          {state.cart.length > 0 ? (
            <div className="row">
              <li className="list-group-item col-6">
                <h3>Cart Total: {cartTotal}TL</h3>
              </li>
              <li className="list-group-item col-6">
                <h3>Budget: {state.budget}</h3>
              </li>
            </div>
          ) : (
            <h1 className="display-4 text-center text-red">Cart Is Empty!</h1>
          )}

          {console.log(state.cart)}
          {state.cart.length > 0
            ? state.cart.map((item) => (
                <div
                  className="card m-auto"
                  style={{ width: "70%" }}
                  key={item.id}
                >
                  <div
                    className="card-header text-center"
                    style={{
                      backgroundColor: "#FFF",
                      color: "#000",
                      fontSize: "1.5rem",
                    }}
                  >
                    {item.title + "   " + item.price + " TL"}
                  </div>
                  <div className="card-body d-flex justify-content-center">
                    <button
                      className="btn btn-success btn-lg mr-4"
                      onClick={() => {
                        if (state.budget - cartTotal < 0) {
                          alert("Budget is not enough!");
                        } else {
                          dispatch({
                            type: "RENT_MOVIE",
                            payload: { id: item.id, price: item.price },
                          });
                          axios
                            .post(
                              `http://localhost:8081/customers/${state.user_name}/rents/${item.id}`
                            )
                            .then((res) => {
                              console.log(res.data);
                              alert("Movie rented successfully!");
                            })
                            .catch((err) => {
                              console.log(err);
                              alert("Unsuccessful Rent!");
                            });
                          //TODO: rent the movie in backend
                        }
                      }}
                    >
                      Rent
                    </button>
                    <button
                      className="btn btn-danger btn-lg"
                      onClick={() => {
                        dispatch({
                          type: "REMOVE_CART",
                          payload: item.id,
                        });
                      }}
                    >
                      Remove
                    </button>
                  </div>
                </div>
              ))
            : null}
        </ul>
        <div className="row mt-5">
          <div class="card col-12">
            <div class="card-body">
              <div className="row">
                <div className="col-4">
                  <div class="card col-12">
                    <div class="card-body">
                      <h5 class="card-title">My Cards</h5>
                    </div>
                    <ul class="list-group list-group-flush">
                      {cards?.length > 0 ? (
                        cards.map((card) =>
                          card !== undefined ? (
                            <li class="list-group-item d-flex">
                              <h6 className="card-title">{card.holder_name}</h6>
                              <div className="ml-auto">
                                <button
                                  className="btn btn-red"
                                  onClick={() => {
                                    const cardtemp = cards.filter(
                                      (card2) => card2.card_id !== card.card_id
                                    );
                                    setCards(cardtemp);
                                  }}
                                >
                                  Remove
                                </button>
                              </div>
                            </li>
                          ) : null
                        )
                      ) : (
                        <h1 className="display-4 text-center text-red">
                          No Cards!
                        </h1>
                      )}
                    </ul>
                  </div>
                </div>
                <div className="col-8">
                  <h5 class="card-title mx-auto">Add a new card</h5>
                  <form
                    onSubmit={(e) => {
                      e.preventDefault();
                      const id = cards.length + 1;
                      const card = {
                        card_id: id,
                        card_number: card_no.current.value,
                        exp_date: exp_date.current.value,
                        cvv: ccv.current.value,
                        holder_name: holder_name.current.value,
                      };
                      setCards([...cards, card]);
                    }}
                  >
                    <div class="form-group">
                      <label for="exampleInputEmail1">Card Number:</label>
                      <input
                        type="text"
                        ref={card_no}
                        class="form-control"
                        id="exampleInputEmail1"
                        placeholder="1*** **** **** ****"
                        aria-describedby="emailHelp"
                      />
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">CCV:</label>
                      <input
                        type="text"
                        ref={ccv}
                        class="form-control"
                        id="exampleInputPassword1"
                        placeholder="XXX"
                      />
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">
                        Cardholder's Name:
                      </label>
                      <input
                        type="text"
                        ref={holder_name}
                        class="form-control"
                        id="exampleInputPassword1"
                        placeholder="Oğuz Ata Çal"
                      />
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">
                        Expiration Date:
                      </label>
                      <input
                        type="text"
                        ref={exp_date}
                        class="form-control"
                        id="exampleInputPassword1"
                        placeholder="08/2023"
                      />
                    </div>
                    <button type="submit" class="btn btn-red btn-block">
                      Add Card
                    </button>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Payment;
