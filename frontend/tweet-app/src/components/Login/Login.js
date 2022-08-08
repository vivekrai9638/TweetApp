import React, { useState, useRef, useCallback } from "react";
import "./Login.css";
import login from "./login.png";
import TwitterIcon from "@mui/icons-material/Twitter";
import { useDispatch } from "react-redux";
import { axiosService } from "../../libs/axiosService";
import { authActions } from "../../store/auth-slice";
import { Link } from "react-router-dom";

const Login = (props) => {
  const enteredUserName = useRef();
  const enteredPassword = useRef();
  const [error, setError] = useState(false);
  const dispatch = useDispatch();

  const changeHandler = useCallback(() => {
    if (error) setError(false);
  }, [error]);

  const submitForm = async (event) => {
    event.preventDefault();
    const body = {
      username: enteredUserName.current.value,
      password: enteredPassword.current.value,
    };

    try {
      const response = await axiosService.generateToken(body);
      dispatch(
        authActions.login({ userName: body.username, token: response.token })
      );
      window.history.push(null,null,'/')
    } catch (error) {
      setError(true);
      enteredUserName.current.value = "";
      enteredPassword.current.value = "";
      enteredUserName.current.focus();
    }
  };

  return (
    <div className="login">
      <div className="login__banner">
        <img src={login} alt="" />
        <TwitterIcon />
      </div>

      <div className="login__main">
        <TwitterIcon />
        <h1>Happening now</h1>
        <div className="login__main--error">
          {error && "Invalid Credentials"}
        </div>
        <form
          className="login__main--form"
          onSubmit={submitForm}
          autoComplete="off"
        >
          <input
            id="userName"
            type="text"
            name="userName"
            placeholder="Username"
            required
            ref={enteredUserName}
            onChange={changeHandler}
          />

          <input
            id="password"
            type="password"
            name="password"
            placeholder="Password"
            required
            ref={enteredPassword}
          />

          <input type="submit" value="Sign in" />
        </form>

        <div className="login__main--signup">
          Don't have an account?&nbsp;
          <Link to="/signup">Sign Up</Link>
        </div>
      </div>
    </div>
  );
};

export default Login;
