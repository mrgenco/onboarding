import axios from "axios";

const API_URL = "http://localhost:8080/api/";

export const getDocumentList = () => {
  return axios.get(API_URL + "document/list", { headers: authHeader() });
};


function authHeader() {
    const userStr = localStorage.getItem("user");
    let user = null;
    if (userStr) 
        user = JSON.parse(userStr);
    
    if (user && user.accessToken) {
      return { Authorization: 'Bearer ' + user.accessToken };
    } 
    return { Authorization: '' }; 
  }