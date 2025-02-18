const express = require('express');

const app = express();
app.use(express.json());

let orders = [];

app.post('/orders', (req, res) => {
    try {
        let { name, price } = req.body;

        let order = { id: orders.length + 1, customerName: name, totalPrice: price };

        orders.push(order);

        res.status(201).send(order);
    } catch (error) {
        console.log(error);
    }
});

app.get('/orders', (req, res) => {
    try {
        res.status(200).send(orders);
    } catch (error) {
        console.log(error);
    }
})

app.get('/orders/:id', (req, res) => {
    let id = parseInt(req.params.id);

    try {
        let order = orders.find((o) => o.id === id);
        if (order) {
            res.status(200).send(order);
        } else {
            res.status(404).send();
        }
    } catch (error) {
        console.log(error);
    }
})

app.put('/orders/:id', (req, res) => {
    let id = parseInt(req.params.id);
    let { name, price } = req.body;

    try {
        let order = orders.find((o) => o.id === id);

        if (!order) res.status(404).send();

        order.customerName = name;
        order.totalPrice = price;

        res.status(200).send(order);
    } catch (error) {
        console.log(error);
    }
})

app.delete('/orders/:id', (req, res) => {
    let id = parseInt(req.params.id);

    try {
        let order = orders.find((o) => o.id === id);
        if (!order) res.status(404).send();
        
        orders = orders.filter((o) => o.id !== id);

        res.status(200).send();
    } catch (error) {
        console.log(error);
    }
})

app.listen(4000, () => {
    console.log("Server running!");
})