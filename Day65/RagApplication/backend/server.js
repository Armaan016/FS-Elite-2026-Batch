const express = require('express');
const cors = require('cors');
const mongoose = require('mongoose');
require('dotenv').config(); 

const authRoutes = require('./routes/authRoutes');
const pdfRoutes = require('./routes/pdfRoutes');
const chatRoutes = require('./routes/chatRoutes');

const app = express();
app.use(cors());
app.use(express.json());
app.use('/uploads', express.static('uploads'));

app.use('/api/auth', authRoutes);
app.use('/api/pdf', pdfRoutes);
app.use('/api/chat', chatRoutes);

mongoose.connect(process.env.MONGO_URI).then(() => {
    app.listen(process.env.PORT, () =>
        console.log(`🚀 Server running on http://localhost:${process.env.PORT}`)
    );
});
