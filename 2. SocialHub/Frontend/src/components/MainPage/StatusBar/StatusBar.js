import React from "react";
import "./StatusBar.css";
import Post from "../Post/Post";
import { useState, useEffect } from "react";

function StatusBar() {
  const [photos, setPhotos] = useState([]);

  useEffect(() => {
    const url = "http://localhost:8080/photo/all"; //-------------

    const fetchData = async () => {
      try {
        const response = await fetch(url);
        const json = await response.json();
        setPhotos(json);
      } catch (error) {
        console.log("error", error);
      }
    };

    fetchData();
  }, []);
  return (
    <div className="statusbar">
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
