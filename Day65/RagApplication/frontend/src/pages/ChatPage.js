import React, { useState } from 'react';
import axios from 'axios';
import { Container, TextField, Button, Typography, Box } from '@mui/material';
import { logout } from '../utils/auth';
import LogoutIcon from '@mui/icons-material/Logout';
import { useNavigate } from 'react-router-dom';

const ChatPage = () => {
    const [question, setQuestion] = useState('');
    const [response, setResponse] = useState('');
    const navigate = useNavigate(); // Initialize useNavigate

    const askQuestion = async () => {
        try {
            const res = await axios.post('http://localhost:5000/api/chat/ask',
                { question },
                { headers: { Authorization: `Bearer ${localStorage.getItem('token')}` } }
            );
            setResponse(res.data.answer);
        } catch {
            setResponse('Error getting answer');
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
            <Typography variant="h4">Ask a question</Typography>
            <TextField
                fullWidth
                multiline
                rows={3}
                label="Enter your question"
                value={question}
                onChange={(e) => setQuestion(e.target.value)}
                sx={{ mt: 2 }}
            />
            <Button variant="contained" sx={{ mt: 2 }} onClick={askQuestion}>Ask</Button>

            {response && (
                <Box sx={{ mt: 4, p: 2, backgroundColor: '#f5f5f5' }}>
                    <Typography variant="h6">Answer:</Typography>
                    <Typography>{response}</Typography>
                </Box>
            )}
        </Container>
    );
};

export default ChatPage;