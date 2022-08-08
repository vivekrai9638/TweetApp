import { createSlice } from "@reduxjs/toolkit";

const tweetSlice = createSlice({
    name: 'tweets',
    initialState : {
        tweets : [],
        avatar: ''
    },
    reducers : {
        setTweets(state, action) {
            state.tweets = action.payload.tweets
        }
    }
})


export const tweetActions =  tweetSlice.actions;

export default tweetSlice;