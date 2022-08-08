import { createSlice } from "@reduxjs/toolkit";

const userSlice = createSlice({
    name: 'user',
    initialState:{
        firstName: '',
        lastName: '',
        avatar: ''
    },
    reducers : {
        setUser(state,action) {
            state.firstName = action.payload.firstName;
            state.lastName = action.payload.lastName;
        }
    }
})

export const userActions = userSlice.actions;

export default userSlice;