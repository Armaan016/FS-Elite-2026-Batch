import React, { useEffect, useState } from 'react';
import axios from 'axios';
import {
    Container, Typography, Table, TableBody, TableCell,
    TableContainer, TableHead, TableRow, Paper, CircularProgress
} from '@mui/material';
import { useNavigate } from 'react-router-dom';

const ProfileList = () => {
    const [profiles, setProfiles] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get('http://localhost:5000/profiles')
            .then(res => {
                setProfiles(res.data);
                setLoading(false);
            })
            .catch(err => {
                console.error(err);
                setLoading(false);
            });
    }, []);

    if (loading) return <CircularProgress sx={{ m: 4 }} />;

    return (
        <Container sx={{ mt: 4 }}>
            <Typography variant="h4" gutterBottom>All Profiles</Typography>
            <TableContainer component={Paper}>
                <Table>
                    <TableHead>
                        <TableRow>
                            <TableCell><strong>Name</strong></TableCell>
                            <TableCell><strong>Email</strong></TableCell>
                            <TableCell><strong>Phone</strong></TableCell>
                            <TableCell><strong>Education</strong></TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {profiles.map((p) => (
                            <TableRow
                                key={p._id}
                                hover
                                onClick={() => navigate(`/profiles/${p._id}`)}
                                sx={{ cursor: 'pointer' }}
                            >
                                <TableCell>{p.name}</TableCell>
                                <TableCell>{p.email}</TableCell>
                                <TableCell>{p.phone}</TableCell>
                                <TableCell>{p.education.degree}, {p.education.institution}</TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </Container>
    );
};

export default ProfileList;
