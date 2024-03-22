import axios from "axios";
import authHeader from "../../../common/auth-header";

const API_URL = "http://localhost:8080/";

export const saveDocument = (documentData : any) => {
  return axios.post(API_URL + "document/save", documentData, { headers: authHeader() });
};
