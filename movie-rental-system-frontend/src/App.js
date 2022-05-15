import EmployeeAddMovie from "./Pages/Employee/AddMovie";
import EmployeeDeleteUser from "./Pages/Employee/DeleteUser";
import SignUp from "./Pages/Signup";
import EmployeeDeleteMovie from "./Pages/Employee/DeleteMovie";
import Login from "./Pages/Login";
import CustomerSearchMovie from "./Pages/Customer/SearchMovie";
import CustomerRequestMovie from "./Pages/Customer/RequestMovie";
import CustomerPayment from "./Pages/Customer/Payment";
import MyMovies from "./Pages/Customer/MyMovies";
import Friends from "./Pages/Customer/Friends";
import ForgotPassword from "./Pages/ForgotPassword";
import Recommendedmovies from "./Pages/Customer/RecommendedMovies";
import Recommendmovie from "./Pages/Customer/RecommendMovie";
import { Routes, Route } from "react-router-dom";
import { initialState } from "./StateManagement/initialState";
import { reducer } from "./StateManagement/reducer";
import { useReducer, createContext } from "react";
import "../src/App.css";
export let Context;
function App() {
  const [state, dispatch] = useReducer(reducer, initialState);
  Context = createContext(state);

  return (
    <Context.Provider value={[state, dispatch]}>
      <Routes>
        <Route exact path="/" element={<Login />} />
        <Route exact path="/employee/mainPage" element={<EmployeeAddMovie />} />
        <Route exact path="/signup" element={<SignUp />} />
        <Route
          exact
          path="/employee/deleteUser"
          element={<EmployeeDeleteUser />}
        />
        <Route exact path="/employee/addMovie" element={<EmployeeAddMovie />} />
        <Route
          exact
          path="/employee/deleteMovie"
          element={<EmployeeDeleteMovie />}
        />
        <Route
          exact
          path="/customer/mainPage"
          element={<CustomerSearchMovie />}
        />
        <Route
          exact
          path="/customer/requestMovie"
          element={<CustomerRequestMovie />}
        />
        <Route exact path="/customer/payment" element={<CustomerPayment />} />
        <Route exact path="/customer/myMovies" element={<MyMovies />} />
        <Route exact path="/customer/friends" element={<Friends />} />
        <Route exact path="/forgotPassword" element={<ForgotPassword />} />
        <Route
          exact
          path="/customer/recommendedMovies"
          element={<Recommendedmovies />}
        />
        <Route
          exact
          path="/customer/recommendMovie"
          element={<Recommendmovie />}
        />
      </Routes>
    </Context.Provider>
  );
}

export default App;
