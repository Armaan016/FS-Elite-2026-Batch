// This is to test the WebSocket server
const WebSocket = require('ws');

const socket = new WebSocket("ws://localhost:8080");

socket.on("open", () => {
    console.log("Connected to WebSocket server");

    socket.send("INSERT Alice 50000 Developer IT 5");
    socket.send("INSERT Bob 60000 Manager IT 10");
    socket.send("RETRIEVE");
    socket.send("RETRIEVE_BY_DEPT IT");
    socket.send("INVALID");
});

socket.on("message", (message) => {
    console.log("Server:", message.toString());
});

socket.on("close", () => {
    console.log("Disconnected from server");
});
