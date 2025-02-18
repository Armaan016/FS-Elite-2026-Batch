// To test the Socket Server...
const WebSocket = require('ws');

const socket = new WebSocket("ws://localhost:8080");

socket.on("open", () => {
    console.log("Connected to WebSocket server");

    socket.send("INSERT Alice 50000");
    socket.send("INSERT Bob 60000");
    socket.send("RETRIEVE");
    socket.send("INVALID");
});

socket.on("message", (message) => {
    console.log("Server:", message.toString());
});

socket.on("close", () => {
    console.log("Disconnected from server");
});
