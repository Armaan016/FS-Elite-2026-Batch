import React, { useState } from 'react';
import axios from 'axios';
import { Container, Typography, Button, Input } from '@mui/material';
import { logout } from '../utils/auth';
import LogoutIcon from '@mui/icons-material/Logout';
import { useNavigate } from 'react-router-dom';

const UploadPage = () => {
    const [file, setFile] = useState(null);
    const navigate = useNavigate(); 

    const uploadFile = async () => {
        const formData = new FormData();
        formData.append('pdf', file);

        try {
            await axios.post('http://localhost:5000/api/pdf/upload', formData, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem('token')}`
                }
            });
            alert('Upload successful');
        } catch {
            alert('Upload failed');
        }
    };

    const handleLogout = () => {
        logout();
        navigate('/login');
    };

    return (
        <Container sx={{ mt: 4 }}>
            <Button
                variant="outlined"
                color="error"
                onClick={handleLogout}
                sx={{ position: 'absolute', top: 16, right: 16 }}
                startIcon={<LogoutIcon />}
            >
                Logout
            </Button>
            <Typography
                variant="caption"
                sx={{ position: 'absolute', top: 60, right: 24 }}
            >
                Role: {localStorage.getItem('role')}
            </Typography>
            <Typography variant="h5">Upload PDF</Typography>
            <Input type="file" onChange={e => setFile(e.target.files[0])} sx={{ mt: 2 }} />
            <Button variant="contained" sx={{ mt: 2 }} onClick={uploadFile} disabled={!file}>Upload</Button>
        </Container>
    );
};

export default UploadPage;
