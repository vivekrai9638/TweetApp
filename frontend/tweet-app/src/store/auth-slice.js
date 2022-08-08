import { createSlice } from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: "auth",
  initialState: {
    token: localStorage.getItem("token") ?? "",
    isLoggedIn: !!localStorage.getItem("token"),
    userName: localStorage.getItem("userName") ?? "",
  },
  reducers: {
    login(state, action) {
      state.userName = action.payload.userName;
      state.token = action.payload.token;
      state.isLoggedIn = true;
      localStorage.setItem("token", state.token);
      localStorage.setItem("userName", state.userName);
    },
    logout(state) {
      state.token = "";
      state.isLoggedIn = false;
      localStorage.removeItem("token");
      localStorage.removeItem("userName");
    },
  },
});

export const authActions = authSlice.actions;

export default authSlice;
