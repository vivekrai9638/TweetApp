import React, { useRef } from "react";
import { useNavigate } from "react-router-dom";
import { axiosService } from "../../../libs/axiosService";
import "./Form.css";

const Form = () => {
  const navigate = useNavigate();

  const enteredUserName = useRef();
  const enteredFirstName = useRef();
  const enteredLastName = useRef();
  const enteredContact = useRef();
  const enteredEmail = useRef();
  const enteredPassword = useRef();

  const submitForm = async (event) => {
    event.preventDefault();

    const body = {
      userName: enteredUserName.current.value,
      firstName: enteredFirstName.current.value,
      lastName: enteredLastName.current.value,
      contact: enteredContact.current.value,
      email: enteredEmail.current.value,
      password: enteredPassword.current.value,
    };

    try {
      await axiosService.createUser(body);
      navigate("/");
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <form className="form" onSubmit={submitForm} autoComplete="off">
      <input
        type="text"
        name="userName"
        placeholder="Username"
        required
        ref={enteredUserName}
      />

      <input
        type="text"
        name="firstName"
        placeholder="First Name"
        required
        ref={enteredFirstName}
      />

      <input
        type="text"
        name="lastName"
        placeholder="Last Name"
        required
        ref={enteredLastName}
      />

      <input
        type="number"
        name="contact"
        placeholder="Contact"
        required
        ref={enteredContact}
      />

      <input
        type="email"
        name="email"
        placeholder="Email "
        required
        ref={enteredEmail}
      />

      <input
        type="password"
        name="password"
        placeholder="Password"
        required
        ref={enteredPassword}
      />

      <input type="submit" value="Sign Up" />
    </form>
  );
};

export default Form;
