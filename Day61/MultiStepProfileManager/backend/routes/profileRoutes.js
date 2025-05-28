const express = require('express');
const router = express.Router();
const {
    createProfile,
    getAllProfiles,
    getProfileById
} = require('../controllers/profileController');

router.post('/', createProfile);
router.get('/', getAllProfiles);
router.get('/:id', getProfileById);

module.exports = router;
