import React, { useEffect } from "react";
import {Outlet} from 'react-router-dom'
import { useDispatch, useSelector } from "react-redux";
import Sidebar from "../../components/Sidebar/Sidebar";
import Widget from "../../components/Widget/Widget";
import { axiosService } from "../../libs/axiosService";
import { tweetActions } from "../../store/tweet-slice";
import { userActions } from "../../store/user-slice";
import "./HomeScreen.css";

const HomeScreen = () => {
  const auth = useSelector((state) => state.auth);
  const tweet = useSelector((state) => state.tweet);
  const dispatch = useDispatch();

  useEffect(() => {
    const fetchTweets = async () => {
      const tweets = await axiosService.getTweetsByUserName(auth.userName, auth.token);
      const userDetails = await axiosService.loginUser({
        userName: auth.userName,
      });
      dispatch(userActions.setUser({ ...userDetails }));
      dispatch(tweetActions.setTweets({tweets}));
    };
    fetchTweets().catch((error) => alert("Something went wrong !"));
  }, [auth, dispatch]);

  return (
    <div className="homeScreen">
      <Sidebar />
      <Outlet context={tweet.tweets}/>
      {/* <Feed tweets={tweet.tweets} /> */}
      <Widget />
    </div>
  );
};

export default HomeScreen;
