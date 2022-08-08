import {configureStore} from '@reduxjs/toolkit'

import authSlice from './auth-slice';
import tweetSlice from './tweet-slice';
import userSlice from './user-slice';

const store = configureStore({
    reducer : {
        auth : authSlice.reducer,
        tweet : tweetSlice.reducer,
        user : userSlice.reducer
    }
});

export default store;