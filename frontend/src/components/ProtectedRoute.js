import React from "react";
import { Navigate, useLocation } from "react-router-dom";

const ProtectedRoute = ({ children }) => {
    const token = localStorage.getItem("token");
    const location = useLocation();

    if (!token) {
        // Redirect to login and pass a message
        return (
            <Navigate
                to="/login"
                replace
                state={{ from: location, message: "You must be logged in to access the chat." }}
            />
        );
    }

    return children;
};

export default ProtectedRoute;
