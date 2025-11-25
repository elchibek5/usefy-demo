import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import apiClient from "../api/client";
import { useAuth } from "../context/AuthContext";

const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const { login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();

  const infoMessage = location.state?.message;

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      // adjust property names if your backend expects different fields
      const response = await apiClient.post("/api/auth/login", {
        username,
        password,
      });

      // adjust if your JwtAuthResponse has different structure
      const token = response.data.accessToken || response.data.token || response.data.jwt;
      if (!token) {
        setError("Login succeeded but no token was returned.");
        return;
      }

      login(token);

      const redirectTo = location.state?.from?.pathname || "/chat";
      navigate(redirectTo, { replace: true });
    } catch (err) {
      console.error(err);
      setError("Invalid username or password.");
    }
  };

  return (
    <div style={{ maxWidth: 400, margin: "40px auto", fontFamily: "sans-serif" }}>
      <h2>Login</h2>

      {infoMessage && (
        <div
          style={{
            marginBottom: 12,
            padding: 8,
            borderRadius: 4,
            backgroundColor: "#fff3cd",
            border: "1px solid #ffeeba",
            fontSize: 14,
          }}
        >
          {infoMessage}
        </div>
      )}

      {error && (
        <div
          style={{
            marginBottom: 12,
            padding: 8,
            borderRadius: 4,
            backgroundColor: "#f8d7da",
            border: "1px solid #f5c6cb",
            fontSize: 14,
          }}
        >
          {error}
        </div>
      )}

      <form onSubmit={handleSubmit}>
        <div style={{ marginBottom: 12 }}>
          <label style={{ display: "block", marginBottom: 4 }}>Username</label>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            style={{ width: "100%", padding: 8 }}
            required
          />
        </div>

        <div style={{ marginBottom: 12 }}>
          <label style={{ display: "block", marginBottom: 4 }}>Password</label>
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            style={{ width: "100%", padding: 8 }}
            required
          />
        </div>

        <button type="submit" style={{ padding: "8px 16px" }}>
          Login
        </button>
      </form>
    </div>
  );
};

export default LoginPage;
