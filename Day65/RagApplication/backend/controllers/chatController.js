const axios = require('axios');

exports.ask = async (req, res) => {
    const { question } = req.body;

    try {
        const flaskRes = await axios.post('http://localhost:8000/rag', { question });
        res.json({ answer: flaskRes.data.answer });
    } catch (err) {
        res.status(500).json({ error: 'Flask server error', detail: err.message });
    }
};
