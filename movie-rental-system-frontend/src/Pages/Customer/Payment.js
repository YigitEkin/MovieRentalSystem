import React from "react";
import "../../stylesheets/Payment.css";
import Navbar from "../../Components/NavbarCustomer";

function Payment() {
  return (
    <div className="allpage">
      <Navbar name={"Ytekin12"} />
      <div className="maincontent container mt-5">
        <div className="row">
          <div class="card col-12">
            <div class="card-body">
              <div className="row">
                <div className="col-4">
                  <div class="card col-12">
                    <div class="card-body">
                      <h5 class="card-title">My Cards</h5>
                    </div>
                    <ul class="list-group list-group-flush">
                      <li class="list-group-item d-flex">
                        <h6 className="card-title">Temp Card1</h6>
                        <div className="ml-auto">
                          <button className="btn btn-red mr-1">Edit</button>
                          <button className="btn btn-red">Remove</button>
                        </div>
                      </li>
                      <li class="list-group-item d-flex">
                        <h6 className="card-title">Temp Card2</h6>
                        <div className="ml-auto">
                          <button className="btn btn-red mr-1">Edit</button>
                          <button className="btn btn-red">Remove</button>
                        </div>
                      </li>
                      <li class="list-group-item d-flex">
                        <h6 className="card-title">Temp Card3</h6>
                        <div className="ml-auto">
                          <button className="btn btn-red mr-1">Edit</button>
                          <button className="btn btn-red">Remove</button>
                        </div>
                      </li>
                    </ul>
                  </div>
                </div>
                <div className="col-8">
                  <h5 class="card-title mx-auto">Add a new card</h5>
                  <form>
                    <div class="form-group">
                      <label for="exampleInputEmail1">Card Number:</label>
                      <input
                        type="text"
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
