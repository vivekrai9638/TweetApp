import React from "react";
import { Link } from "react-router-dom";
import Form from "./Form/Form";
import "./SignUp.css";

const SignUp = () => {
  return (
    <div className="signUp__background">
      <div className="signUp">
        <div className="signUp__head">
          <h1>Create your account</h1>
        </div>

        <div className="signUp__form">
          <Form />
        </div>
        <div className="signUp__login">
          Already have an account?&nbsp;
          <Link to="/">Sign In</Link>
        </div>
      </div>
    </div>
  );
};

export default SignUp;
