

export default function authHeader() {
  const accessToken = localStorage.getItem("access_token");
  if (accessToken) {
    console.log("Access Token : ", accessToken);
    return { Authorization: 'Bearer ' + accessToken };
  }
  return { Authorization: '' };
}