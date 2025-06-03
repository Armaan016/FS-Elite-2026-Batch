const multer = require('multer');
const path = require('path');

const storage = multer.diskStorage({
    destination: 'uploads/',
    filename: (_, file, cb) => cb(null, Date.now() + '-' + file.originalname)
});

const upload = multer({ storage }).single('pdf');

exports.uploadPDF = (req, res) => {
    upload(req, res, (err) => {
        if (err) return res.status(500).json({ error: err.message });
        res.json({ filename: req.file.filename });
    });
};
