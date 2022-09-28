import { Avatar } from "@mui/material";
import React, { useContext,useRef, useState } from "react";
import "./StatusSender.css";
import PhotoLibraryIcon from "@mui/icons-material/PhotoLibrary";
import "./StatusBar.css";
import UploadIcon from "@mui/icons-material/Upload";
import HighlightOffIcon from "@mui/icons-material/HighlightOff";
import { UserContext } from "../../../context/UserContext";

function StatusSender() {
  const inputRef = useRef(null);
  const hiddenFileInput = useRef(null);
  const [photoToPost, setPhotoToPost] = useState(null);
  const [photoThumbnail, setPhotoThumbnail] = useState(null);
  const photoEndPoint = "http://localhost:8080/photo/upload";
  const {userDetail} = useContext(UserContext);

  const handleClick = () => {
    hiddenFileInput.current.click();
  };

  const addImageToPost = (e) => {
    const reader = new FileReader();
    if (e.target.files[0]) {
      setPhotoToPost(e.target.files[0]);
      reader.readAsDataURL(e.target.files[0]);
      reader.onload = (e) => {
        setPhotoThumbnail(e.target.result);
      };
    }
  };
  const removeImage = () => {
    setPhotoToPost(null);
    setPhotoThumbnail(null);
  };

  //uploads photo
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!inputRef.current.value) return;

    const formData = new FormData();

    formData.append("file", photoToPost);
    formData.append("description", inputRef.current.value);
    formData.append("tag", "meme");

    fetch(photoEndPoint, {
      method: "post",
      body: formData,
      credentials: "include",
    }).then((res) => {
      if (res.ok) {
        inputRef.current.value = "";
        removeImage();
        window.location.reload(false);
      }
    });
  };
  return (
    <div className="statusSender">
      <div className="statusSender__top">
        <Avatar src={userDetail.profilePic}/>
        <form>
          <input
            ref={inputRef}
            className="statusSender__input"
            placeholder={"Photo description"}
          />

          <div onClick={handleClick} className="statusSender__option">
            <PhotoLibraryIcon style={{ color: "green" }} />
            <h3>Photo</h3>
            <input
              type="file"
              onChange={addImageToPost}
              ref={hiddenFileInput}
              hidden
              accept="image/*"
            />
          </div>
          <div className="statusSender__option" onClick={handleSubmit}>
            <UploadIcon style={{ color: "red" }} />
            <h3>Post</h3>
          </div>
          <button onClick={handleSubmit} type="submit">
            Hidden submit
          </button>
        </form>
      </div>
      {photoThumbnail && (
        <div onClick={removeImage} className="imageThumbnailSection">
          <img
            src={photoThumbnail}
            alt="uploading_photo"
            className="imageThumbnail"
          />
          <HighlightOffIcon className="deleteImage" />
        </div>
      )}
    </div>
  );
}

export default StatusSender;
