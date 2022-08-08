import React, { useEffect, useRef, useState } from "react";
import "./TweetBox.css";
import ModeCommentOutlinedIcon from "@mui/icons-material/ModeCommentOutlined";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteIcon from "@mui/icons-material/Favorite";
import vivek from "./vivek.png";
import post from "./post.jpg";
import { useSelector } from "react-redux";
import { axiosService } from "../../../libs/axiosService";

const TweetBox = (props) => {
  const auth = useSelector((state) => state.auth);
  const user = useSelector((state) => state.user);
  const reply = useRef();
  const [show, setShow] = useState(false);
  const [replies, setReplies] = useState([]);
  const [like, setLike] = useState({
    isLiked: false,
    likeCount: props.data.likeCount,
  });
  const [replyCount, setReplyCount] = useState(props.data.replyCount);

  useEffect(() => {
    let liked;

    const getLiked = async () => {
      liked = await axiosService.isLiked(
        auth.userName,
        { tweetId: props.data.tweetId, replyId: props.data.replyId },
        auth.token
      );
    };

    getLiked()
      .then(() => {
        setLike((like) => {
          return { ...like, isLiked: liked };
        });
      })
      .catch((error) => {
        throw error;
      });
  }, [auth, props]);

  const commentHandler = () => setShow((state) => !state);

  useEffect(() => {
    if (show) {
      const fetchReplies = () =>
        axiosService.getRepliesByTweetId(props.data.tweetId, auth.token);

      fetchReplies().then((replies) => setReplies(replies));
    }
  }, [show, like, auth, props]);

  const replyHandler = async (event) => {
    const content = reply.current.value;
    reply.current.value = "";
    const replyRes = await axiosService.replyToTweet(
      { userName: auth.userName, tweetId: props.data.tweetId },
      { content },
      auth.token
    );
    setReplies((replies) => [...replies, replyRes]);
    setReplyCount((replyCount) => replyCount + 1);
  };

  const likeHandler = async (event) => {
    await axiosService.likeContent(
      auth.userName,
      { tweetId: props.data.tweetId, replyId: props.data.replyId },
      auth.token
    );
    setLike((like) => {
      if (!like.isLiked)
        return {
          isLiked: true,
          likeCount: like.likeCount + 1,
        };
      else return { isLiked: false, likeCount: like.likeCount - 1 };
    });
  };

  return (
    <div className="tweetBox">
      <div className="tweetBox__avatar">
        <img className={props.type + "__img"} src={vivek} alt="" />
      </div>

      <div className="tweetBox__main">
        <div className="tweetBox__main--head">
          <h4>
            {props.type === "tweet"
              ? user.firstName + " " + user.lastName
              : props.data.user.firstName + " " + props.data.user.lastName}
          </h4>
          <div className="tweetBox__main--head--meta">
            <span>
              {props.type === "tweet"
                ? `@${auth.userName}`
                : `@${props.data.user.userName}`}
            </span>
            <span className="tweetBox__main--head--meta--circle"></span>
            <span>45m</span>
          </div>
        </div>

        <div className={`tweetBox__main--content ${props.type}__content`}>
          <p>{props.data.content} </p>
          {props.type === "reply" && (
            <div className="like" onClick={likeHandler}>
              {like.isLiked ? (
                <FavoriteIcon style={{ color: "#E0245E" }} />
              ) : (
                <FavoriteBorderOutlinedIcon />
              )}
              <span>{like.likeCount}</span>
            </div>
          )}
          {props.type === "tweet" && <img src={post} alt="" />}
        </div>

        {props.type === "tweet" && (
          <div className="tweetBox__main--foot">
            <div className="comment" onClick={commentHandler}>
              <ModeCommentOutlinedIcon /> <span>{replyCount}</span>
            </div>

            <div className="like" onClick={likeHandler}>
              {like.isLiked ? (
                <FavoriteIcon style={{ color: "#E0245E" }} />
              ) : (
                <FavoriteBorderOutlinedIcon />
              )}
              <span>{like.likeCount}</span>
            </div>
          </div>
        )}

        {props.type === "tweet" &&
          show &&
          replies.map((reply) => (
            <TweetBox type="reply" key={reply.replyId} data={reply} />
          ))}

        {props.type === "tweet" && show && (
          <div className="input__reply">
            <textarea
              ref={reply}
              className="input__comment"
              type="text"
              placeholder="Write a comment..."
            />
            <button className="reply__btn" onClick={replyHandler}>
              Reply
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default TweetBox;
