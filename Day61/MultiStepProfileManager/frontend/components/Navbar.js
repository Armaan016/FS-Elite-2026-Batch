import React from 'react';
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { Link } from 'react-router-dom';

const Navbar = () => (
    <AppBar position="static">
        <Toolbar>
            <Typography variant="h6" sx={{ flexGrow: 1 }}>
                Profile Manager
            </Typography>
            <Button color="inherit" component={Link} to="/new-profile">New Profile</Button>
            <Button color="inherit" component={Link} to="/profiles">All Profiles</Button>
        </Toolbar>
    </AppBar>
);

export default Navbar;
