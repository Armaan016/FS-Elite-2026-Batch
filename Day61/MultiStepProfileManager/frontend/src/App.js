import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import MultiStepForm from './components/MultiStepForm';
import ProfileList from './components/ProfileList';
import ProfileDetail from './components/ProfileDetail';
import Navbar from './components/Navbar';

const App = () => {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<MultiStepForm />} />
        <Route path="/new-profile" element={<MultiStepForm />} />
        <Route path="/profiles" element={<ProfileList />} />
        <Route path="/profiles/:id" element={<ProfileDetail />} />
      </Routes>
    </Router>
  );
};

export default App;
