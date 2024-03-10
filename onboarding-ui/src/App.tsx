import * as React from 'react';
import { Routes, Route } from "react-router-dom";
import Settings from './pages/settings/Settings';
import DashboardPage from './pages/dashboard/DashboardPage';
import MyDocuments from './pages/mydocuments/MyDocumentsPage';
import WriteDocument from './pages/writedocument/WriteDocumentPage';
import Layout from './Layout';
import { AuthProvider, RequireAuth } from './AuthProvider';
import LoginPage from './pages/login/LoginPage';



export default function App() {

  return (
    <AuthProvider>
      <Routes>
        <Route element={<Layout />}>
          <Route index element={<DashboardPage />} />
          <Route path="mydocuments" element={<MyDocuments />} />
          <Route path="settings" element={<Settings />} />
          <Route path="dashboard" element={<DashboardPage />} />
          <Route path="login" element={<LoginPage />} />
          <Route
            path="writedocument"
            element={
              <RequireAuth>
                <WriteDocument />
              </RequireAuth>
            }
          />

        </Route>
      </Routes>
    </AuthProvider>
  );
}