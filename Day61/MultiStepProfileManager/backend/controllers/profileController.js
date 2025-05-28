const Profile = require('../models/Profile');

exports.createProfile = async (req, res) => {
    try {
        const profile = new Profile(req.body);
        await profile.save();
        res.status(201).json(profile);
    } catch (err) {
        res.status(400).json({ error: err.message });
    }
};

exports.getAllProfiles = async (req, res) => {
    try {
        const profiles = await Profile.find().sort({ name: 1 });
        res.json(profiles);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
};

exports.getProfileById = async (req, res) => {
    try {
        const profile = await Profile.findById(req.params.id);
        if (!profile) return res.status(404).json({ message: 'Not found' });
        res.json(profile);
    } catch (err) {
        res.status(500).json({ error: err.message });
    }
};
