import React, { useReducer, useState } from 'react';

import {
    Stepper, Step, StepLabel,
    Button, Box, TextField, Grid
} from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const steps = ['Personal Info', 'Education', 'Interests', 'Achievements'];

const initialState = {
    name: '', email: '', phone: '',
    education: { degree: '', institution: '', year: '' },
    interests: '',
    achievements: ''
};

function reducer(state, action) {
    switch (action.type) {
        case 'UPDATE_FIELD':
            return { ...state, [action.field]: action.value };
        case 'UPDATE_EDUCATION':
            return {
                ...state,
                education: { ...state.education, [action.field]: action.value }
            };
        default:
            return state;
    }
}


const MultiStepForm = () => {
    const [activeStep, setActiveStep] = useState(0);
    const navigate = useNavigate();

    const [form, dispatch] = useReducer(reducer, initialState);


    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name.startsWith('education.')) {
            const field = name.split('.')[1];
            dispatch({ type: 'UPDATE_EDUCATION', field, value });
        } else {
            dispatch({ type: 'UPDATE_FIELD', field: name, value });
        }
    };


    const handleNext = () => setActiveStep(prev => prev + 1);
    const handleBack = () => setActiveStep(prev => prev - 1);

    const handleSubmit = async () => {
        const data = {
            ...form,
            interests: form.interests.split(',').map(i => i.trim()),
            achievements: form.achievements.split(',').map(a => a.trim()),
            education: {
                ...form.education,
                year: Number(form.education.year)
            }
        };
        await axios.post('http://localhost:5000/profiles', data);
        navigate('/profiles');
    };

    const renderStepContent = (step) => {
        switch (step) {
            case 0:
                return (
                    <Grid container spacing={2}>
                        <Grid item xs={12}><TextField label="Name" name="name" fullWidth value={form.name} onChange={handleChange} /></Grid>
                        <Grid item xs={12}><TextField label="Email" name="email" fullWidth value={form.email} onChange={handleChange} /></Grid>
                        <Grid item xs={12}><TextField label="Phone" name="phone" fullWidth value={form.phone} onChange={handleChange} /></Grid>
                    </Grid>
                );
            case 1:
                return (
                    <Grid container spacing={2}>
                        <Grid item xs={12}><TextField label="Degree" name="education.degree" fullWidth value={form.education.degree} onChange={handleChange} /></Grid>
                        <Grid item xs={12}><TextField label="Institution" name="education.institution" fullWidth value={form.education.institution} onChange={handleChange} /></Grid>
                        <Grid item xs={12}><TextField label="Year" name="education.year" fullWidth type="number" value={form.education.year} onChange={handleChange} /></Grid>
                    </Grid>
                );
            case 2:
                return (
                    <TextField
                        label="Interests (comma-separated)"
                        name="interests"
                        fullWidth multiline
                        value={form.interests}
                        onChange={handleChange}
                    />
                );
            case 3:
                return (
                    <TextField
                        label="Achievements (comma-separated)"
                        name="achievements"
                        fullWidth multiline
                        value={form.achievements}
                        onChange={handleChange}
                    />
                );
            default:
                return 'Unknown step';
        }
    };

    return (
        <Box sx={{ width: '70%', margin: '2rem auto' }}>
            <Stepper activeStep={activeStep} alternativeLabel>
                {steps.map(label => <Step key={label}><StepLabel>{label}</StepLabel></Step>)}
            </Stepper>

            <Box sx={{ mt: 3 }}>
                {renderStepContent(activeStep)}
            </Box>

            <Box sx={{ display: 'flex', justifyContent: 'space-between', mt: 3 }}>
                <Button disabled={activeStep === 0} onClick={handleBack}>Back</Button>
                {activeStep === steps.length - 1 ? (
                    <Button variant="contained" onClick={handleSubmit}>Submit</Button>
                ) : (
                    <Button variant="contained" onClick={handleNext}>Next</Button>
                )}
            </Box>
        </Box>
    );
};

export default MultiStepForm;
