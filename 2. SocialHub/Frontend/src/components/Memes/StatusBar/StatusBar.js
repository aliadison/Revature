import React, { useEffect, useState } from "react";
import "./StatusBar.css";
import StatusSender from "./StatusSender";
import Post from "../Post/Post";

function StatusBar() {
  const [photos, setPhotos] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/photo/meme")
      .then((response) => response.json())
      .then((result) => {
        setPhotos(result);
      });
  }, []);
  return (
    <div className="statusbar">
      <StatusSender />
      {photos.map((photo) => (
        <Post
          profilePic="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT7oeegjCS9uIRPQlcTpEtMSWgcHuP6Wm-glw&usqp=CAU"
          message={photo.description}
          photoId={photo.id}
          username={photo.uploadedBy.firstName}
          avgRating={photo.avgRating}
          ratingsCount={photo.ratingsCount}
          key={photo.id}
          image={photo.urllink}
        />
      ))}
    </div>
  );
}

export default StatusBar;
