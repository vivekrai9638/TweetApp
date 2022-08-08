import React from "react";
import {useOutletContext} from 'react-router-dom'
import "./Feed.css";
import AutoAwesomeOutlinedIcon from "@mui/icons-material/AutoAwesomeOutlined";
import TweetBox from "./TweetBox/TweetBox";

const Feed = () => {
  const tweets = useOutletContext();

  return (
    <div className="feed">
      <div className="feed__head">
        <h3>Home</h3>
        <AutoAwesomeOutlinedIcon />
      </div>

      {tweets ? (
        tweets.map((tweet) => (
          <TweetBox type="tweet" key={tweet.tweetId} data={tweet} />
        ))
      ) : (
        <p>Nothing</p>
      )}
    </div>
  );
};

export default Feed;
