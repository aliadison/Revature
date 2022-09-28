import "./App.css";
import React, { useState } from "react";
import { BrowserRouter as Router } from "react-router-dom";
import { AppRoutes } from "./router/AppRoutes";
import { UserContext } from "./context/UserContext";

// import Layout from './components/MainPage/Layout';
// import Login from './components/Login/Login';
// import {BrowserRouter as Router, Routes, Route} from "react-router-dom";

function App() {
  const [userDetail, setUserDetail] = useState([]);
  

  return (
    <UserContext.Provider value={{userDetail, setUserDetail}}>
      <Router>
        <AppRoutes></AppRoutes>
      </Router>
    </UserContext.Provider>
  );
}

export default App;
