const mongoose = require('mongoose');

const profileSchema = new mongoose.Schema({
    name: { type: String, required: true },
    email: { type: String, required: true },
    phone: { type: String, required: true },
    education: {
        degree: String,
        institution: String,
        year: Number
    },
    interests: [String],
    achievements: [String]
});

module.exports = mongoose.model('Profile', profileSchema);
