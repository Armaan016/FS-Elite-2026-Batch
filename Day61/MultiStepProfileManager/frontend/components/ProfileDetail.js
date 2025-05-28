import React, { useEffect, useState } from 'react';
import {
    Container, Typography, List, ListItem, ListItemText,
    Divider, CircularProgress
} from '@mui/material';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const ProfileDetail = () => {
    const { id } = useParams();
    const [profile, setProfile] = useState(null);

    useEffect(() => {
        axios.get(`http://localhost:5000/profiles/${id}`)
            .then(res => setProfile(res.data))
            .catch(err => console.error(err));
    }, [id]);

    if (!profile) return <CircularProgress sx={{ m: 4 }} />;

    return (
        <Container sx={{ mt: 4 }}>
            <Typography variant="h4" gutterBottom>
                {profile.name}'s Profile
            </Typography>
            <List>
                <ListItem>
                    <ListItemText primary="Email" secondary={profile.email} />
                </ListItem>
                <ListItem>
                    <ListItemText primary="Phone" secondary={profile.phone} />
                </ListItem>
                <ListItem>
                    <ListItemText primary="Education" secondary={
                        `${profile.education.degree}, ${profile.education.institution} (${profile.education.year})`
                    } />
                </ListItem>
                <Divider />
                <ListItem>
                    <ListItemText
                        primary="Interests"
                        secondary={profile.interests.join(', ')}
                    />
                </ListItem>
                <ListItem>
                    <ListItemText
                        primary="Achievements"
                        secondary={profile.achievements.join(', ')}
                    />
                </ListItem>
            </List>
        </Container>
    );
};

export default ProfileDetail;
