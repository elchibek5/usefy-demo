// frontend/src/pages/ChatPage.js
import React, { useState } from "react";
import apiClient from "../api/client";

function ChatPage() {
  const [input, setInput] = useState("");
  const [messages, setMessages] = useState([
    { id: 1, sender: "ai", text: "Hi! I’m your study assistant 🤖" },
  ]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const handleSend = async (e) => {
    e.preventDefault();
    setError("");

    const trimmed = input.trim();
    if (!trimmed) return;

    // Add user message immediately
    const userMsg = {
      id: Date.now(),
      sender: "user",
      text: trimmed,
    };
    setMessages((prev) => [...prev, userMsg]);
    setInput("");
    setLoading(true);

    try {
      const response = await apiClient.post("/api/chat", {
        message: trimmed,
      });

      const aiText = response.data.reply || "No reply";
      const aiMsg = {
        id: Date.now() + 1,
        sender: "ai",
        text: aiText,
      };
      setMessages((prev) => [...prev, aiMsg]);
    } catch (err) {
      console.error(err);
      setError("Failed to contact chat server.");
      const errMsg = {
        id: Date.now() + 2,
        sender: "ai",
        text: "Oops, something went wrong talking to the server.",
      };
      setMessages((prev) => [...prev, errMsg]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 600, margin: "0 auto" }}>
      <h2>AI Chat</h2>

      <div
        style={{
          border: "1px solid #ddd",
          borderRadius: 8,
          padding: 12,
          minHeight: 200,
          marginBottom: 12,
          overflowY: "auto",
          maxHeight: 400,
        }}
      >
        {messages.map((m) => (
          <div
            key={m.id}
            style={{
              textAlign: m.sender === "user" ? "right" : "left",
              marginBottom: 8,
            }}
          >
            <div
              style={{
                display: "inline-block",
                padding: "6px 10px",
                borderRadius: 12,
                backgroundColor:
                  m.sender === "user" ? "#DCF8C6" : "#F1F0F0",
              }}
            >
              <strong>
                {m.sender === "user" ? "You: " : "AI: "}
              </strong>
              {m.text}
            </div>
          </div>
        ))}
      </div>

      {error && (
        <div style={{ color: "red", marginBottom: 8 }}>{error}</div>
      )}

      <form onSubmit={handleSend} style={{ display: "flex", gap: 8 }}>
        <input
          type="text"
          value={input}
          onChange={(e) => setInput(e.target.value)}
          placeholder="Type your message..."
          style={{ flex: 1, padding: 8 }}
        />
        <button type="submit" disabled={loading}>
          {loading ? "Sending..." : "Send"}
        </button>
      </form>
    </div>
  );
}

export default ChatPage;
