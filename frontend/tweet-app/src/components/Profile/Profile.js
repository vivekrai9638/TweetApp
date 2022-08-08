import { useNavigate } from "react-router-dom";
import React from "react";
import WestIcon from "@mui/icons-material/West";
import './Profile.css'
import vivek from '../Feed/TweetBox/vivek.png'
import bg from './bg.jpg'

const Profile = () => {
    const navigate = useNavigate();
    const backButtonHandler = (event) => {
        return navigate(-1);
    }

  return <div className="profile">
    <div className="profile__navBar">
        <div className="profile__navBar--backBtn" >
            <WestIcon onClick={backButtonHandler}/>
        </div>
        <div className="profile__navBar--meta">
            <h2>Vivek Rai</h2>
            <div>5 Tweets</div>
        </div>
    </div>

    <div className="profile__background">
        <img src={bg} alt="" />
    </div>

    <div className="profile__display">
        <img src={vivek} alt="" />
        <button>Edit profile</button>
    </div>

    <div className="profile__meta">
        <h2>Vivek Rai</h2>
        <p>@vivekrai9638</p>
        <div className="profile__meta--followers">
            <p><span>67</span>Following</p>
            <p><span>67</span>Followers</p>
        </div>
    </div>

    <div className="profile__navigation">
        {/* <Link>Tweets</Link>
        <Link>Tweets & replies</Link>
        <Link>Likes</Link> */}
    </div>
  </div>;
};

export default Profile;
