import React, { useContext, useEffect } from "react";
import "./NavBar.css";
import Grid from "@mui/material/Grid";
import SearchIcon from "@mui/icons-material/Search";
import { Link } from "react-router-dom";
import { Avatar } from "@mui/material";
import Button from "@mui/material/Button";
import { UserContext } from "../../context/UserContext";

function NavBar() {
  const {userDetail, setUserDetail} = useContext(UserContext);
  
  useEffect(() => {
    //check for user login session
    const requestOptions = {
      method: "GET",
      headers: { "Content-Type": "application/json" },
      credentials: "include",
    };

    fetch("http://localhost:8080/account/checksession", requestOptions)
      .then((response) => response.text())
      .then((data) => {
        if (data === "Not logged in") window.location.href = "/login";
      });

      //for fetching username and profile pic
      fetch("http://localhost:8080/profile/account", {
      method: "get",
      credentials: "include",
    })
      .then((response) => response.json())
      .then((result) => {
        setUserDetail(result);
      });
  }, []);

  //log out button
  function logOut() {
    fetch("http://localhost:8080/account/logout", {
      credentials: "include",
    })
      .then((response) => response.text())
      .then((result) => {
        window.location.reload(false);
      });
  }

  

  return (
    <div>
      <Grid container className="navbar">
        <Grid item xs={3}>
          <div className="navbar__left">
            <Link to="/">
              <img
                className="navbar__logo"
                src="http://www.blogcdn.com/www.joystiq.com/media/2010/11/bf10-logo-sears.jpg"
                alt="SocialHub Logo"
              />
            </Link>
          </div>
        </Grid>
        <Grid item xs={6}>
          <div className="navbar__middle">
            <div className="navbar__search">
              <SearchIcon />
              <input type="text" placeholder="Search SocialHub" />
            </div>
          </div>
        </Grid>
        <Grid item xs={3}>
          <div className="navbar__right">
          <Link to="/userpage">
            <div className="navbar__righttab">
              <Avatar src={userDetail.profilePic}/>
              <h4>{userDetail?.accountId?.firstName +" "+ userDetail?.accountId?.lastName}</h4>

              
            </div>
            </Link>
            <Button id="logOutBtn" variant="outlined" color="error" onClick={logOut}>
                Log out
              </Button>
          </div>
        </Grid>
      </Grid>
    </div>
  );
}

export default NavBar;
