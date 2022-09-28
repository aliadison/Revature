import NavBar from "../NavBar/NavBar";
import "./UserPage.css";
import { Grid } from "@mui/material";
// import { useState } from 'react';
import { Avatar } from "@mui/material";
import { Paper } from "@mui/material";
import Button from "@mui/material/Button";
import { useState } from "react";
import { useRef } from "react";
import { useEffect } from "react";

export function UserPage() {
  const [profilePhotoSrc, setProfilePhotoSrc] = useState(null);
  const hiddenFileInput = useRef(null);
  const [fullName, setFullName] = useState("");
  const firstName = useRef(" ");
  const lastName = useRef(" ");
  const email = useRef(" ");
  const password = useRef(" ");
  const location = useRef(" ");
  const phone = useRef(" ");

  //when photo upload button is pressed
  const photoUploadBtn = () => {
    hiddenFileInput.current.click();
  };

  //uplads profile photo
  const uploadPhoto = (e) => {
    const formData2 = new FormData();
    if (e.target.files[0]) {
      const profilePhoto = e.target.files[0];

      formData2.append("file", profilePhoto);

      const photoEndPoint = "http://localhost:8080/profile/photoupload";
      fetch(photoEndPoint, {
        method: "post",
        body: formData2,
        credentials: "include",
      }).then((res) => {
        if (res.ok) {
          window.location.reload(false);
        }
      });
    }
  };
  //for posting data to database for update
  const updateProfile = () => {
    console.log("Button clicked");
    const formData = new FormData();
    formData.append("firstName", firstName.current.value);
    formData.append("lastName", lastName.current.value);
    formData.append("email", email.current.value);
    formData.append("password", password.current.value);
    formData.append("location", location.current.value);
    formData.append("phone", phone.current.value);

    fetch("http://localhost:8080/profile/update", {
      method: "post",
      body: formData,
      credentials: "include",
    })
      .then((response) => response.json())
      .then((result) => {
        firstName.current.value = result.accountId.firstName;
        lastName.current.value = result.accountId.lastName;
        email.current.value = result.accountId.email;
        password.current.value = result.accountId.password;
        location.current.value = result.location;
        phone.current.value = result.phone;
      });
  };

  //to fecth data for fields
  useEffect(() => {
    fetch("http://localhost:8080/profile/account", {
      credentials: "include",
    })
      .then((response) => response.json())
      .then((result) => {
        firstName.current.value = result.accountId.firstName;
        lastName.current.value = result.accountId.lastName;
        email.current.value = result.accountId.email;
        password.current.value = result.accountId.password;
        location.current.value = result.location;
        phone.current.value = result.phone;
        setProfilePhotoSrc(result.profilePic);
        setFullName(
          result.accountId.firstName + " " + result.accountId.lastName
        );
      });
  }, []);

  return (
    <div className="main__container">
      <div className="user__navbar">
        <NavBar />
      </div>
      <Grid className="user__container" container>
        <Grid item xs={2}></Grid>
        <Grid item xs={8}>
          <Paper className="userpage__container">
            <div className="userpage__header">
              <Avatar
                src={profilePhotoSrc}
                title="Jane Doe"
                sx={{ width: 150, height: 150 }}
              />
              <Button variant="contained" onClick={photoUploadBtn}>
                Upload Photo
              </Button>
              <input
                type="file"
                onChange={uploadPhoto}
                ref={hiddenFileInput}
                hidden
                accept="image/*"
              />
              <p>{fullName}</p>
            </div>

            <div>
              <div className="dividor"></div>
            </div>
            <div className="userpage__rows">
              <label>First Name</label>
              <input
                type="text"
                className="form__control"
                placeholder="Enter First Name"
                ref={firstName}
              />
            </div>
            <div className="userpage__rows">
              <label>Last Name</label>
              <input
                type="text"
                className="form__control"
                placeholder="Enter Last Name"
                ref={lastName}
              />
            </div>
            <div className="userpage__rows">
              <label>Email Address</label>
              <input
                type="email"
                className="form__control"
                placeholder="example@domain.com"
                ref={email}
              />
            </div>
            <div className="userpage__rows">
              <label>Password</label>
              <input
                type="password"
                className="form__control"
                placeholder="Enter Password"
                ref={password}
              />
            </div>
            <div className="userpage__rows">
              <label>Location</label>
              <input
                type="text"
                className="form__control"
                placeholder="Your location"
                ref={location}
              />
            </div>
            <div className="userpage__rows">
              <label>Phone Number</label>
              <input
                type="phone"
                className="form__control"
                placeholder="(xxx)-xxx-xxxx"
                ref={phone}
              />
            </div>
            <div className="userpage__rows">
              <Button
                variant="contained"
                color="success"
                onClick={updateProfile}
              >
                Upload Profile
              </Button>
            </div>
          </Paper>
        </Grid>
        <Grid item xs={2}></Grid>
      </Grid>
    </div>
  );
}
