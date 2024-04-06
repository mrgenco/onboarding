import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/";

export const getUserInfo = () => {
  return axios.get(API_URL + "user/info", { headers: authHeader() });
};
