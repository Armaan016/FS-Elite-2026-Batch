// <!--
// Employee Management WebSocket Application

// Objective:
// -----------
// Your task is to develop a WebSocket-based Employee Management System using Node.js. 
// This system will allow clients to:
//     1. Insert Employee Records (INSERT <name> <salary>)
//     2. Retrieve Employee List (RETRIEVE)
//     3. Handle Invalid Commands (e.g., INVALID should return "Invalid command")
// Your goal is to implement and test a WebSocket-based server and client, 
// ensuring that all operations work correctly.

// Requirements:
// -------------
// 1. Implement WebSocket Server
// 	The server should:
// 		-> Accept multiple client connections.
// 		-> Process client messages and handle commands:
// 			1. INSERT <name> <salary> → Adds an employee to an in-memory array.
// 			2. RETRIEVE → Returns all stored employees.
// 			3. Any other command should return "Invalid command."
// 		-> Maintain an in-memory array of employees (no database required).
// 		-> Log each received command on the console.


// Expected Behavior
// -----------------

// ============================================================================================
// Client Command			Server Response
// ============================================================================================
// INSERT Alice 50000		"Employee inserted successfully."
// INSERT Bob 60000		"Employee inserted successfully."
// RETRIEVE				"ID: 1, Name: Alice, Salary: 50000"
//                         "ID: 2, Name: Bob, Salary: 60000"
// INVALID					"Invalid command."
// ============================================================================================

// Note: 
// -> The server should run on port 8080.
// -> The system should allow multiple clients to connect.


// EXAMPLE URL value=>   ws://10.11.xx.xx:8080
// -->
// <config>
//     <url value="ws://10.11.7.229:8080"></url>
// </config>

const WebSocket = require('ws');

const server = new WebSocket.Server({ port: 8080 });

let idx = 1;
let employees = [];

server.on('connection', (ws) => {
    console.log("Client connected!");

    ws.on('message', (message) => {
        console.log("Received:", message);
        const parts = message.trim().split(" ");

        if (parts[0] === "INSERT" && parts.length === 3) {
            const name = parts[1];
            const salary = parseFloat(parts[2]);

            employees.push({ ID: idx++, Name: name, Salary: salary });
            ws.send("Employee inserted successfully.");
        }
        else if (parts[0] === "RETRIEVE") {
            const response = employees.map(emp => `ID: ${emp.ID}, Name: ${emp.Name}, Salary: ${emp.Salary}`).join("\n");
            ws.send(response);
        }
        else {
            ws.send("Invalid command.");
        }
    });

    ws.on('close', () => {
        console.log("Client disconnected.");
    });
});

console.log("WebSocket server is running on ws://localhost:8080");
