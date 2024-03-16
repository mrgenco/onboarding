import axios from "axios";

const API_URL = "http://localhost:8080/api/";

export const getDocumentList = () => {
  return axios.get(API_URL + "document/list", { headers: authHeader() });
};


function authHeader() {
    const accessToken = localStorage.getItem("access_token");
    if (accessToken) {
      console.log("Access Token : ", accessToken);
      return { Authorization: 'Bearer ' + accessToken };
    } 
    return { Authorization: '' }; 
  }