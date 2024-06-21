import axios from "axios";
import authHeader from "../../common/auth-header";

const API_URL = "http://localhost:8080/";

export const renderDocument = (uuid : string) => {
  return axios.get(API_URL + "document/raw/" + uuid, { headers: authHeader() });
};
