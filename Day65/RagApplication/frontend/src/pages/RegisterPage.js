import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
    TextField, Button, Container, Typography, MenuItem
} from '@mui/material';

const RegisterPage = () => {
    const [form, setForm] = useState({
        username: '',
        password: '',
        role: 'user',
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleRegister = async () => {
        try {
            await axios.post('http://localhost:5000/api/auth/signup', form);
            alert('Registration successful!');
            navigate('/login');
        } catch (err) {
            alert('Registration failed');
        }
    };

    return (
        <Container maxWidth="sm" sx={{ mt: 8 }}>
            <Typography variant="h4" gutterBottom>Register</Typography>
            <TextField
                label="Username"
                name="username"
                fullWidth
                sx={{ mb: 2 }}
                onChange={handleChange}
            />
            <TextField
                label="Password"
                name="password"
                type="password"
                fullWidth
                sx={{ mb: 2 }}
                onChange={handleChange}
            />
            <TextField
                select
                label="Role"
                name="role"
                value={form.role}
                fullWidth
                onChange={handleChange}
                sx={{ mb: 2 }}
            >
                <MenuItem value="user">User</MenuItem>
                <MenuItem value="admin">Admin</MenuItem>
            </TextField>
            <Button variant="contained" fullWidth onClick={handleRegister}>
                Register
            </Button>
        </Container>
    );
};

export default RegisterPage;
