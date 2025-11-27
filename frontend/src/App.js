// frontend/src/App.js
import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import ProtectedRoute from "./components/ProtectedRoute";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import ChatPage from "./pages/ChatPage";
import { useAuth } from "./context/AuthContext";

function App() {
  const { isAuthenticated, logout } = useAuth();

  return (
    <div>
      {/* Simple top navigation */}
      <nav
        style={{
          padding: "8px 16px",
          borderBottom: "1px solid #ddd",
          marginBottom: 16,
        }}
      >
        <Link to="/chat" style={{ marginRight: 16 }}>
          Chat
        </Link>
        <Link to="/login" style={{ marginRight: 16 }}>
          Login
        </Link>
        <Link to="/register" style={{ marginRight: 16 }}>
          Register
        </Link>

        {isAuthenticated && (
          <button onClick={logout} style={{ padding: "4px 8px" }}>
            Logout
          </button>
        )}
      </nav>

      {/* Routes */}
      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />

        <Route
          path="/chat"
          element={
            <ProtectedRoute>
              <ChatPage />
            </ProtectedRoute>
          }
        />

        {/* Default: go to login */}
        <Route path="*" element={<LoginPage />} />
      </Routes>
    </div>
  );
}

export default App;
