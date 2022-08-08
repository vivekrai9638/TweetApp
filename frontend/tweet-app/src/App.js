import { Navigate, Route, Routes } from "react-router-dom";
import HomeScreen from "./screens/HomeScreen/HomeScreen";
import SignUpScreen from "./screens/SignUpScreen/SignUpScreen";
import "./App.css";
import LoginScreen from "./screens/LoginScreen/LoginScreen";
import { useSelector } from "react-redux";
import Profile from "./components/Profile/Profile";
import Feed from "./components/Feed/Feed";

function App() {
  const auth = useSelector((state) => state.auth);

  return (
    <Routes>
      <Route path="/" element={<Navigate replace to="login" />} />
      <Route
        path="/login"
        element={
          !auth.isLoggedIn ? (
            <LoginScreen />
          ) : (
            <Navigate replace to={`/${auth.userName}`} />
          )
        }
      />
      <Route path="/signup" element={<SignUpScreen />} />
      {auth.isLoggedIn && (
        <Route path={"/" + auth.userName} element={<HomeScreen />}>
          <Route index element={<Feed />}/>
          <Route path="profile" element={<Profile />} />
        </Route>
      )}
    </Routes>
  );
}

export default App;
