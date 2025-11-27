import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import apiClient from "../api/client";

const RegisterPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError("");
        setSuccess("");

        try {
            await apiClient.post("/api/auth/register", {
                username,
                password,
            });

            setSuccess("Registration successful! You can now log in.");
            setTimeout(() => navigate("/login"), 1000);
        } catch (err) {
            setError(err.response?.data?.message || "Registration failed");
        }
    };

    return (
        <div style={{ maxWidth: "400px", margin: "40px auto" }}>
            <h2>Register</h2>

            {error && <div style={{ color: "red", marginBottom: "8px" }}>{error}</div>}
            {success && <div style={{ color: "green", marginBottom: "8px" }}>{success}</div>}

            <form onSubmit={handleSubmit}>
                <div style={{ marginBottom: "8px" }}>
                    <label>Username</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        required
                        style={{ width: "100%" }}
                    />
                </div>
                <div style={{ marginBottom: "8px" }}>
                    <label>Password</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={{ width: "100%" }}
                    />
                </div>
                <button type="submit">Register</button>
            </form>

            <p style={{ marginTop: "12px" }}>
                Already have an account? <Link to="/login">Login</Link>
            </p>
        </div>
    );
};

export default RegisterPage;
