import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import ProtectedRoute from "./components/ProtectedRoute";
import LoginPage from "./pages/LoginPage";
import ChatPage from "./pages/ChatPage";
import { useAuth } from "./context/AuthContext";

const App = () => {
  const { isAuthenticated, logout } = useAuth();

  return (
    <div>
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
        {isAuthenticated && (
          <button onClick={logout} style={{ padding: "4px 8px" }}>
            Logout
          </button>
        )}
      </nav>

      <Routes>
        <Route path="/login" element={<LoginPage />} />
        <Route
          path="/chat"
          element={
            <ProtectedRoute>
              <ChatPage />
            </ProtectedRoute>
          }
        />
        <Route path="*" element={<LoginPage />} />
      </Routes>
    </div>
  );
};

export default App;
