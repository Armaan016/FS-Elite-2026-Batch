require('dotenv').config();
const mongoose = require('mongoose');
const Product = require('./models/Product');

const categories = ['Electronics', 'Clothing', 'Books', 'Food', 'Toys'];

const generateProducts = () => {
    return Array.from({ length: 100 }, (_, i) => ({
        name: `Product ${i + 1}`,
        price: parseFloat((Math.random() * 100).toFixed(2)),
        category: categories[Math.floor(Math.random() * categories.length)],
        inStock: Math.random() > 0.3
    }));
};

mongoose.connect(process.env.MONGO_URI)
    .then(async () => {
        await Product.deleteMany();
        await Product.insertMany(generateProducts());
        console.log('Database seeded!');
        mongoose.disconnect();
    })
    .catch(err => console.error(err));
