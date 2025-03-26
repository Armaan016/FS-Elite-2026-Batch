const WebSocket = require('ws');

const wss = new WebSocket.Server({ port: 8085 });

let clients = new Set();

wss.on('connection', (ws) => {
    console.log('New client connected');
    clients.add(ws);

    ws.on('message', (message) => {
        const parsed = JSON.parse(message);
        const response = JSON.stringify({ text: parsed.text })
        console.log(`Received: ${response}`);

        clients.forEach(client => {
            if (client.readyState === WebSocket.OPEN) {
                client.send(response);
            }
        });
    });

    ws.on('close', () => {
        console.log('Client disconnected');
        clients.delete(ws);
    });
});

console.log('WebSocket server is running on ws://localhost:8085');
