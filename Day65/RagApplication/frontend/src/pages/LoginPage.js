import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { TextField, Button, Container, Typography } from '@mui/material';

const LoginPage = () => {
    const [form, setForm] = useState({ username: '', password: '' });
    const navigate = useNavigate();

    const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

    const handleLogin = async () => {
        try {
            const res = await axios.post('http://localhost:5000/api/auth/login', form);
            localStorage.setItem('token', res.data.token);
            localStorage.setItem('role', res.data.role);

            if (res.data.role === 'admin') navigate('/admin/upload');
            else navigate('/chat');
        } catch (err) {
            alert('Login failed');
        }
    };

    return (
        <Container maxWidth="sm" sx={{ mt: 8 }}>
            <Typography variant="h4" gutterBottom>Login</Typography>
            <TextField label="Username" name="username" fullWidth sx={{ mb: 2 }} onChange={handleChange} />
            <TextField label="Password" name="password" type="password" fullWidth sx={{ mb: 2 }} onChange={handleChange} />
            <Button variant="contained" fullWidth onClick={handleLogin}>Login</Button>
            <Button onClick={() => navigate('/register')} sx={{ mt: 1 }}>
                Don't have an account? Register here
            </Button>

        </Container>
    );
};

export default LoginPage;
