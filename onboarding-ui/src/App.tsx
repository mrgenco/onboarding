import * as React from 'react';
import { Routes, Route } from "react-router-dom";
import Settings from './pages/settings/Settings';
import DashboardPage from './pages/dashboard/DashboardPage';
import MyDocuments from './pages/mydocuments/MyDocumentsPage';
import WriteDocument from './pages/writedocument/WriteDocumentPage';
import Layout from './Layout';
import { AuthProvider, RequireAuth } from './AuthProvider';
import LoginPage from './pages/login/LoginPage';
import ReadDocumentPage from './pages/read/ReadDocumentPage';



export default function App() {

  return (
    <AuthProvider>
      <Routes>
        <Route element={<Layout />}>
          <Route path="login" element={<LoginPage />} />
          <Route index path="dashboard" element={<RequireAuth><DashboardPage /></RequireAuth>} />
          <Route index element={<RequireAuth><DashboardPage /></RequireAuth>} />
          <Route path="mydocuments" element={<RequireAuth><MyDocuments /></RequireAuth>} />
          <Route path="settings" element={<RequireAuth><Settings /></RequireAuth>} />
          <Route path="writedocument" element={<RequireAuth><WriteDocument /></RequireAuth>} />
          <Route path="document/:uuid" element={<RequireAuth><ReadDocumentPage /></RequireAuth>}
        />
        </Route>
      </Routes>
    </AuthProvider>
  );
}