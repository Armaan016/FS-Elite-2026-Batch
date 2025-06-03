import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import UploadPage from './pages/UploadPage';
import ChatPage from './pages/ChatPage';
import PrivateRoute from './utils/PrivateRoute';
import RegisterPage from './pages/RegisterPage';

const App = () => (
  <Router>
    <Routes>
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/admin/upload" element={<PrivateRoute role="admin"><UploadPage /></PrivateRoute>} />
      <Route path="/chat" element={<PrivateRoute role="user"><ChatPage /></PrivateRoute>} />
      <Route path="*" element={<Navigate to="/login" />} />
    </Routes>
  </Router>
);

export default App;
