import axios from 'axios';

interface AuthResponse {
  access_token: string;
  refresh_token: string;
  expire_time: string;
}

export interface AuthRequest {
  username: string;
  password: string;
  authenticationMethod: string; 
}

export const authService = {
  isAuthenticated: false,
  async signin(authRequest : AuthRequest) {
    
    try {
    
      const response = await axios.post<AuthResponse>('http://localhost:8080/v1/auth/login', {
        username: authRequest.username,
        password: authRequest.password,
        authenticationMethod: authRequest.authenticationMethod
      });
      
      const { access_token, refresh_token, expire_time } = response.data;
      
      localStorage.setItem('access_token', access_token);
      localStorage.setItem('refresh_token', refresh_token);
      localStorage.setItem('expire_time', expire_time);
      
      authService.isAuthenticated = true;
      return { access_token, refresh_token, expire_time };
    
    } catch (error) {
      console.error('Error occurred during signin:', error);
      throw error;
    }
  },
  async signout() {
    // Clear tokens from local storage
    localStorage.removeItem('access_token');
    localStorage.removeItem('refresh_token');
    localStorage.removeItem('expire_time');
    // Update isAuthenticated to false
    authService.isAuthenticated = false;
  },
};

