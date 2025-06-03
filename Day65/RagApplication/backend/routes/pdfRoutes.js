const express = require('express');
const router = express.Router();
const auth = require('../middleware/authMiddleware');
const role = require('../middleware/roleMiddleware');
const { uploadPDF } = require('../controllers/pdfController');

router.post('/upload', auth, role('admin'), uploadPDF);

module.exports = router;
