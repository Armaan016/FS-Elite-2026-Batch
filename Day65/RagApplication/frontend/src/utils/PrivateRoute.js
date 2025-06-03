import React from 'react';
import { Navigate } from 'react-router-dom';
import { isAuthenticated, getRole } from './auth';

const PrivateRoute = ({ children, role }) => {
    if (!isAuthenticated()) return <Navigate to="/login" />;
    if (role && getRole() !== role) return <Navigate to="/login" />;
    return children;
};

export default PrivateRoute;
