import * as React from 'react';
import { Routes, Route } from "react-router-dom";
import Settings from './pages/settings/Settings';
import DashboardPage from './pages/dashboard/DashboardPage';
import MyDocuments from './pages/mydocuments/MyDocumentsPage';
import WriteDocument from './pages/writedocument/WriteDocumentPage';
import Layout from './Layout';


export default function App() {

  return (
    <Routes>
      <Route element={<Layout />}>
        <Route index element={<DashboardPage />} />
        <Route path="mydocuments" element={<MyDocuments />} />
        <Route path="settings" element={<Settings />} />
        <Route path="dashboard" element={<DashboardPage />} />
        <Route path="writedocument" element={<WriteDocument />} />
      </Route>
    </Routes>
  );
}