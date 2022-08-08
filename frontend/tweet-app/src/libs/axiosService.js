import axios from "axios";

const AUTH_URL = "http://localhost:8082/token";
const USER_URL = "http://localhost:8081/users/";
const TWEET_URL = "http://localhost:8081/tweets/";

export const axiosService = {
  generateToken: async (body) => {
    try {
      const res = await axios.post(AUTH_URL, body);
      //   console.log(res);
      if (res.status === 200) return res.data;
      else throw new Error("Invalid Credentials");
    } catch (error) {
      throw error;
    }
  },

  createUser: async (body) => {
    try {
      const res = await axios.post(USER_URL + "register", body);
      console.log(res);
      if (res.status !== 200) throw new Error("Something went wrong !");
    } catch (error) {
      throw error;
    }
  },

  getTweetsByUserName: async (userName, token) => {
    try {
      const res = await axios.get(TWEET_URL + userName, {
        headers: { Authorization: `Bearer ${token}` },
      });

      if (res.status === 200) return res.data;
      else throw new Error("Something went wrong !");
    } catch (error) {
      throw error;
    }
  },

  loginUser: async (body) => {
    try {
      const res = await axios.post(USER_URL + "login", body);
      if (res.status === 200) return res.data;
      else throw new Error("Something went wrong !");
    } catch (error) {
      throw error;
    }
  },

  getRepliesByTweetId: async (tweetId, token) => {
    try {
      const res = await axios.get(TWEET_URL + "replies/" + tweetId, {
        headers: { Authorization: `Bearer ${token}` },
      });

      console.log(res);
      if (res.status === 200) return res.data;
      else throw new Error("Something went wrong");
    } catch (error) {
      throw error;
    }
  },

  likeContent: async (userName, { tweetId, replyId }, token) => {
    try {
      const url = `${TWEET_URL}${userName}/${
        tweetId ? "tweet" : "reply"
      }/like/${tweetId || replyId}`;
      const res = await axios.put(url, null, {
        headers: { Authorization: `Bearer ${token}` },
      });

      if (res.status === 200) return res.data;
      else throw new Error("Something went wrong !");
    } catch (error) {
      throw error;
    }
  },

  isLiked: async (userName, { tweetId, replyId }, token) => {
    try {
      const url = `${TWEET_URL}${userName}/${
        tweetId ? "tweet" : "reply"
      }/like/${tweetId || replyId}`;
      const res = await axios.get(url, {
        headers: { Authorization: `Bearer ${token}` },
      });
      if (res.status === 200) return res.data;
      else throw new Error("Something went wrong !");
    } catch (error) {
      throw error;
    }
  },

  replyToTweet: async ({ userName, tweetId }, body, token) => {
    try {
      const res = await axios.post(
        TWEET_URL + userName + "/reply/" + tweetId,
        body,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      if(res.status===200) return res.data;
      else throw new Error('Something went wrong !')
    } catch (error) {
      throw error;
    }
  },
};
