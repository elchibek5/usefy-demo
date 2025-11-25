import React, { useState } from "react";
import apiClient from "../api/client";

const ChatPage = () => {
  const [input, setInput] = useState("");
  const [messages, setMessages] = useState([]); // { id, sender: 'user' | 'ai', text }

  const handleSend = async (e) => {
    e.preventDefault();
    const trimmed = input.trim();
    if (!trimmed) return;

    const userMsg = {
      id: Date.now(),
      sender: "user",
      text: trimmed,
    };

    setMessages((prev) => [...prev, userMsg]);
    setInput("");

    try {
      const response = await apiClient.post("/api/chat", {
        message: trimmed,
      });

      const replyText = response.data.reply || "(no reply field)";
      const aiMsg = {
        id: Date.now() + 1,
        sender: "ai",
        text: replyText,
      };

      setMessages((prev) => [...prev, aiMsg]);
    } catch (err) {
      console.error(err);
      const errorMsg = {
        id: Date.now() + 2,
        sender: "ai",
        text: "Error contacting AI service.",
      };
      setMessages((prev) => [...prev, errorMsg]);
    }
  };

  return (
    <div
      style={{
        maxWidth: 600,
        margin: "40px auto",
        border: "1px solid #ddd",
        borderRadius: 8,
        display: "flex",
        flexDirection: "column",
        height: "70vh",
        fontFamily: "sans-serif",
      }}
    >
      <div
        style={{
          padding: 12,
          borderBottom: "1px solid #ddd",
          fontWeight: "bold",
        }}
      >
        AiDA Chat
      </div>

      <div
        style={{
          flex: 1,
          padding: 12,
          overflowY: "auto",
          backgroundColor: "#fafafa",
        }}
      >
        {messages.map((msg) => (
          <div
            key={msg.id}
            style={{
              marginBottom: 8,
              textAlign: msg.sender === "user" ? "right" : "left",
            }}
          >
            <div
              style={{
                display: "inline-block",
                padding: "8px 12px",
                borderRadius: 16,
                backgroundColor: msg.sender === "user" ? "#007bff" : "#e4e6eb",
                color: msg.sender === "user" ? "#fff" : "#000",
              }}
            >
              {msg.text}
            </div>
          </div>
        ))}
      </div>

      <form
        onSubmit={handleSend}
        style={{
          padding: 12,
          borderTop: "1px solid #ddd",
          display: "flex",
          gap: 8,
        }}
      >
        <input
          type="text"
          placeholder="Type your message..."
          value={input}
          onChange={(e) => setInput(e.target.value)}
          style={{ flex: 1, padding: 8 }}
        />
        <button type="submit" style={{ padding: "8px 16px" }}>
          Send
        </button>
      </form>
    </div>
  );
};

export default ChatPage;
