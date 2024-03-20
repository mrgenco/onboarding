import axios from "axios";
import authHeader from "../../../common/auth-header";

const API_URL = "http://localhost:8080/";

export const getDocumentList = () => {
  return axios.get(API_URL + "document/list", { headers: authHeader() });
};
