import React, { useEffect, useState } from "react";
import {
    useLocation,
    useNavigate,
    Navigate,
  } from "react-router-dom";
import { authService, AuthRequest } from "./common/auth-service"

interface AuthContextType {
    isAuthenticated: any;
    signin: (credentials: AuthRequest) => Promise<void>;
    signout: () => Promise<void>;
}

let AuthContext = React.createContext<AuthContextType>(null!);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
  let [isAuthenticated, setIsAuthenticated] = useState<boolean>(() => {
    // Initialize isAuthenticated from localStorage on component mount
    const storedAuth = localStorage.getItem("isAuthenticated");
    return storedAuth ? JSON.parse(storedAuth) : false;
  });
  console.log("AuthProvider isAuthenticated : ", isAuthenticated);

  useEffect(() => {
    // Store isAuthenticated in localStorage whenever it changes
    localStorage.setItem("isAuthenticated", JSON.stringify(isAuthenticated));
  }, [isAuthenticated]);

  let signin = async (credentials: AuthRequest) => {
    await authService.signin(credentials);
    setIsAuthenticated(authService.isAuthenticated);
  };

  let signout = async () => {
    await authService.signout();
    setIsAuthenticated(authService.isAuthenticated);
  };

  let value = { isAuthenticated, signin, signout };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export const useAuth = () => {
    return React.useContext(AuthContext);
}

export const RequireAuth = ({ children }: { children: JSX.Element }) => {
    let auth = useAuth();
    let location = useLocation();

    console.log("Require isAuthenticated : ", auth.isAuthenticated);
    if (!auth.isAuthenticated) {  
      // Redirecting user to the /login page, but saving the current location they were
      // trying to go to when they were redirected. 
      // This allows us to send them along to that page after they login, 
      // which is a nicer user experience than dropping them off on the home page.
      return <Navigate to="/login" state={{ from: location }} replace />;
    }
  
    return children;
}
