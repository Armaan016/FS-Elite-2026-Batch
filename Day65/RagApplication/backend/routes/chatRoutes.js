const express = require('express');
const router = express.Router();
const auth = require('../middleware/authMiddleware');
const role = require('../middleware/roleMiddleware');
const { ask } = require('../controllers/chatController');

router.post('/ask', auth, role('user'), ask);

module.exports = router;
