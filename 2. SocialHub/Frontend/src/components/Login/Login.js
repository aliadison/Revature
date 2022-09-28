import React from "react";
import "./Login.css";
import socialhubbanner from "../../images/socialhub.png";
import { Grid } from "@mui/material";
import { Paper } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useState } from "react";

export function Login() {
  const [login_panel, setLogin_panel] = useState(true);
  const [login_email, setLogin_email] = useState("");
  const [login_password, setLogin_password] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [signup_email, setSignup_email] = useState("");
  const [signup_password, setSignup_password] = useState("");

  function switchPanel() {
    if (login_panel) setLogin_panel(false);
    else setLogin_panel(true);
  }

  function register() {
    let payload = {
      email: signup_email,
      password: signup_password,
      firstName: firstName,
      lastName: lastName,
    };

    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    };

    fetch("http://localhost:8080/account/register", requestOptions)
      .then((response) => response.text())
      .then((data) => {
        setLogin_panel(!login_panel);
      });
  }
  const navigate = useNavigate();
  function login() {
    let payload = {
      email: login_email,
      password: login_password,
    };

    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
      credentials: "include",
    };
    fetch("http://localhost:8080/account/login", requestOptions)
      .then((response) => response.text())
      .then((data) => {
        if (data === "Logged in successfully") navigate("/");
      })
      .catch((error) => {});
    //if (response.status >= 200 && response.status < 300)
  }
  return (
    <div className="main__container">
      <Grid className="main__content" container>
        <Grid item xs={7}>
          <div className="shlogo">
            <img src={socialhubbanner} alt="SocialHub Banner" width="350px" />
          </div>
          <div>
            <h1 className="text">
              SocialHub helps you connect and share with the people in your
              life.
            </h1>
          </div>
        </Grid>
        <Grid item xs={3}>
          <Paper className="logincard__container">
            {login_panel === true ? (
              <div container="login__panel">
                <div>
                  <input
                    onChange={(event) => {
                      setLogin_email(event.currentTarget.value);
                    }}
                    type="email"
                    className="login__input"
                    placeholder="Email address"
                  />
                </div>
                <div>
                  <input
                    onChange={(event) => {
                      setLogin_password(event.currentTarget.value);
                    }}
                    type="password"
                    className="login__input"
                    placeholder="Password"
                  />
                </div>
                <div>
                  <button onClick={login} className="login__button">
                    Log in
                  </button>
                </div>
                <div>
                  <div className="dividor"></div>
                </div>
                <div>
                  <button className="login__createnew" onClick={switchPanel}>
                    Create New Account
                  </button>
                </div>
              </div>
            ) : (
              <div container="login__panel">
                <div>
                  <input
                    onChange={(event) => {
                      setFirstName(event.currentTarget.value);
                    }}
                    type="text"
                    id="FirstName"
                    className="login__input"
                    placeholder="First Name"
                  />
                </div>
                <div>
                  <input
                    onChange={(event) => {
                      setLastName(event.currentTarget.value);
                    }}
                    type="text"
                    id="LastName"
                    className="login__input"
                    placeholder="Last Name"
                  />
                </div>
                <div>
                  <input
                    onChange={(event) => {
                      setSignup_email(event.currentTarget.value);
                    }}
                    type="email"
                    id="email"
                    className="login__input"
                    placeholder="Email address"
                  />
                </div>
                <div>
                  <input
                    onChange={(event) => {
                      setSignup_password(event.currentTarget.value);
                    }}
                    type="password"
                    id="password"
                    className="login__input"
                    placeholder="Password"
                  />
                </div>
                <div>
                  <button onClick={register} className="login__button">
                    Sign Up
                  </button>
                </div>
                <div>
                  <div onClick={switchPanel} className="forget_Text">
                    Already have account?
                  </div>
                </div>
              </div>
            )}
          </Paper>
        </Grid>
        <Grid item xs={1}></Grid>
      </Grid>
    </div>
  );
}
