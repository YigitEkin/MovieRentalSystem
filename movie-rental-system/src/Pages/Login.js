import React from 'react'
import "../stylesheets/login.css"
import Logo from "../images/logo.jpeg"
const Login = () => {
  return (
      <div className="center">
            <div className='container box-shadow p-5'>
            <div className="row">
                <div className="col-6">
                    <img src={Logo} alt="logo" className='logo'/>
                </div>
                <div className="col-6 border-left-red">
                    <form className='login-form'>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Username</label>
                            <input type="text" class="form-control" id="userName" aria-describedby="emailHelp" placeholder='Künefe123'/>
                            <small id="emailHelp" class="form-text text-gray">We'll never share your email with anyone else.</small>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Password</label>
                            <input type="password" class="form-control" id="exampleInputPassword1" placeholder='PASSWORD'/>
                        </div>
                        <div class="form-group form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1"/>
                            <label class="form-check-label" for="exampleCheck1">Remember my username</label>
                        </div>
                        <button type="submit" class="btn btn-block submit-btn">Login</button>
                        {
                        //These will be changed into react routes
                        }
                    </form>
                        <div className="row mt-3">
                            <div className="col-12 ml-2 p-2">
                                <a href="./Signup.js">
                                    <p className="text-red large-text">Don't have an account?</p>
                                </a>
                            </div>
                        </div>
                </div>
            </div>
            </div>
            </div>
  )
}

export default Login