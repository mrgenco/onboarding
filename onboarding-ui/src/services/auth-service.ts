import axios from "axios";
import { useNavigate } from "react-router-dom";
const API_URL = "http://localhost:8080/api/v1/auth/";

export const register = (username: string, email: string, password: string) => {
    return axios.post(API_URL + "register", {
        username,
        email,
        password,
    });
};

export const login = async (username: string, password: string, authmethod: string) => {
    const navigate = useNavigate();
    const response = await axios.post(API_URL + "login", {username,password,authmethod});
    if (response.data.accessToken) {
        const user = {
            username,
            ...response.data
        }
        localStorage.setItem("user", JSON.stringify(user));
        navigate("/dashboard");
    }
    return response.data;
};

export const logout = () => {
    localStorage.removeItem("user");
};

export const getCurrentUser = () => {
    const userStr = localStorage.getItem("user");
    if (userStr) 
        return JSON.parse(userStr);
    return null;
};