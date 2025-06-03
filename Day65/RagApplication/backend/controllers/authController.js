const User = require('../models/user');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');

exports.signup = async (req, res) => {
    try {
        const { username, password, role } = req.body;
        console.log('Signup request received:', { username, role });

        const hashed = await bcrypt.hash(password, 10);
        const user = await User.create({ username, password: hashed, role });

        console.log('User created successfully:', user);
        res.status(201).json({ message: 'User created' });
    } catch (error) {
        console.error('Error during signup:', error.message);
        res.status(500).json({ error: 'Internal server error' });
    }
};

exports.login = async (req, res) => {
    try {
        const { username, password } = req.body;
        console.log('Login request received:', { username });

        const user = await User.findOne({ username });
        if (!user) {
            console.warn('User not found:', username);
            return res.status(401).json({ error: 'Invalid credentials' });
        }

        const isPasswordValid = await bcrypt.compare(password, user.password);
        if (!isPasswordValid) {
            console.warn('Invalid password for user:', username);
            return res.status(401).json({ error: 'Invalid credentials' });
        }

        const token = jwt.sign(
            { id: user._id, role: user.role },
            process.env.JWT_SECRET,
            { expiresIn: '1d' }
        );

        console.log('Login successful for user:', username);
        res.json({ token, role: user.role });
    } catch (error) {
        console.error('Error during login:', error.message);
        res.status(500).json({ error: 'Internal server error' });
    }
};