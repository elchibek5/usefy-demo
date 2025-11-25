import axios from "axios";

const apiClient = axios.create({
    baseURL: "http://localhost:8080";
});

// Attack token automatically if it exists
apiClient.interceptors.request.use({config} => {
    const token = localStorage.getItem("token");
    if (token) {
        config.headers.Authorization = 'Bearer ${token}';
    }
    return config;
});

export default apiClient;