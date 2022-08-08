import React from "react";
import "./Sidebar.css";
import TwitterIcon from "@mui/icons-material/Twitter";
import HomeIcon from "@mui/icons-material/Home";
// import ListAltIcon from "@mui/icons-material/ListAlt";
import PermIdentityIcon from "@mui/icons-material/PermIdentity";
// import TagIcon from "@mui/icons-material/Tag";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";
// import NotificationsNoneIcon from "@mui/icons-material/NotificationsNone";
import SidebarOption from "./SidebarOption/SidebarOption";
import { useDispatch, useSelector } from "react-redux";
import { authActions } from "../../store/auth-slice";
import { useNavigate, useLocation } from "react-router-dom";

const Sidebar = () => {
  const navigate = useNavigate();
  const location = useLocation().pathname.split('/').at(-1);
  const dispatch = useDispatch();
  const logoutHandler = (event) => {
    dispatch(authActions.logout());
    navigate("/", { replace: true });
  };
  const profileButtonHandler = (event) => navigate("profile");
  const homeButtonHandler = (event) => navigate('/');
  const auth = useSelector(state => state.auth);

  return (
    <div className="sidebar">
      {/* Home - Twitter icon */}
      <TwitterIcon className="sidebar__tweetIcon" onClick={homeButtonHandler} />

      {/* Sidebar Options */}
      <SidebarOption
        active={location === auth.userName && true}
        Icon={HomeIcon}
        text="Home"
        onClick={homeButtonHandler}
      />
      {/* <SidebarOption Icon={TagIcon} text="Explore" />
      <SidebarOption Icon={NotificationsNoneIcon} text="Notifications" /> */}
      {/* <SidebarOption Icon={ListAltIcon} text="Lists" /> */}
      <SidebarOption
        active={location === 'profile' && true}
        Icon={PermIdentityIcon}
        onClick={profileButtonHandler}
        text="Profile"
      />
      <SidebarOption Icon={MoreHorizIcon} text="More" />

      {/* Tweet */}
      <button className="sidebar__tweet">Tweet</button>
      <button className="sidebar__logout" onClick={logoutHandler}>
        Logout
      </button>
    </div>
  );
};

export default Sidebar;
