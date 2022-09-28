import { Avatar } from "@mui/material";
import React, { useEffect, useState } from "react";
import "./Post.css";
import Rating from "@mui/material/Rating";
import StarIcon from "@mui/icons-material/Star";

function Post({
  profilePic,
  image,
  username,
  message,
  photoId,
  avgRating,
  ratingsCount,
}) {
  const [value, setValue] = useState(null);

  //sends rating to DB
  const handleChange = (event, newValue) => {
    const formData = new FormData();
    formData.append("newStarValue", newValue);
    formData.append("photoId", photoId);

    fetch("http://localhost:8080/rating/new", {
      method: "post",
      body: formData,
      credentials: "include",
    })
      .then((response) => response.json())
      .then((result) => {
        setValue(result.points);
      });
  };
  //fetches rating value
  useEffect(() => {
    const formData = new FormData();
    formData.append("photoId", photoId);

    fetch("http://localhost:8080/rating/photo", {
      method: "post",
      body: formData,
      credentials: "include",
    })
      .then((response) => response.text())
      .then((result) => {
        setValue(Number(result));
      });
  }, []);
  return (
    <div className="post">
      <div className="post__top">
        <Avatar className="post__avatar" />
        <div className="post__topInfo">
          <h3>{username}</h3>
        </div>
      </div>

      <div className="post__bottom">
        <p>{message}</p>
      </div>

      <div className="post__image">
        <img src={image} alt="" />
        <StarIcon color="primary" />
        {avgRating} (based on {ratingsCount} ratings)
      </div>

      <div className="post__options">
        <Rating
          color="primary"
          name="customized-10"
          value={value}
          icon={<StarIcon color="primary" />}
          onChange={handleChange}
          max={10}
        />
      </div>
    </div>
  );
}

export default Post;
