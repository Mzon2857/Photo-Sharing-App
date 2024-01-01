import { useAuth0 } from "@auth0/auth0-react";
import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./userProfile.scss";
import axios from "axios";

const UserProfile: React.FC = () => {
  let { username } = useParams<{ username: string }>();
  const { user } = useAuth0();
  const [userId, setUserId] = useState("");

  const [images, setImages] = useState([]);

  const handleImageClick = () => {
    //add image click logic here
  }

  //Retrieves userID from the email attached in auth0 token
  useEffect(() => {
    const fetchUserIdByEmail = async (email: string) => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/users/by-email/${email}`
        );
        setUserId(response.data);
      } catch (error) {
        console.error("Error fetching user id", error);
      }
    };

    if (user && user.email) {
      fetchUserIdByEmail(user.email);
    }
  }, [user]);

  useEffect(() => {
    const fetchImages = async () => {
      if (userId) {
        try {
          const response = await fetch(
            `http://localhost:8080/api/images/${userId}/get-url`
          );
          const data = await response.json();
          setImages(data);
        } catch (error) {
          console.error("Error fetching images:", error);
        }
      }
    };
    fetchImages();
  }, [userId]);

  return (
    <div className="UserProfile-container">
      <header className="UserProfile-header">
        <img src={user.picture} alt="User" className="UserProfile-picture" />
        <div className="UserProfile-username">{username}</div>
      </header>
      <div className="UserProfile-imageGrid">
        {images.map((img, index) => (
          <img key={index} src={img} alt={`Gallery ${index}`} onClick={handleImageClick} className="UserProfile-image" />
        ))}
      </div>
    </div>
  );
};

export default UserProfile;
