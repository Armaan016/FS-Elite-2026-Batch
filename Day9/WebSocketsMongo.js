// <!--
// Employee Management WebSocket Application with MongoDB

// Objective:
// ----------
// Your task is to develop a WebSocket-based Employee Management System using Node.js and MongoDB. 
// The system should allow multiple clients to interact with a database to perform the following operations:
// 	1. Insert Employee Records (INSERT <name> <salary> <role> <department> <experience>)
// 	2. Retrieve Employee List (RETRIEVE)
// 	3. Retrieve Employee List who belongs to a department (RETRIEVE_BY_DEPT <department>)

// The WebSocket server should be capable of handling multiple concurrent clients and persist employee data in MongoDB.


// // MongoDB Employee Schema
// const employeeSchema = new mongoose.Schema({
//     name: String,
//     salary: Number,
//     role: String,
//     department: String,
//     experience: Number
// });

// Requirements:
// -------------
// Implement WebSocket Server
// 	The server should:
// 		-> Accept multiple client connections. (give a response as "Connected" )
// 		-> Process incoming commands from clients as discussed above.
// 		-> Log each received command on the console.
// 		-> Ensure proper error handling (e.g., invalid salary, missing name, etc.).

// Expected Behavior
// -----------------

// ============================================================================================
// Client Command			                Server Response
// ============================================================================================
// INSERT Alice 50000 Developer IT 5	    "Employee inserted successfully."
// INSERT Bob 60000 Manager IT 5	        "Employee inserted successfully."

// RETRIEVE				                "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
//                                         "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"

// RETRIEVE_BY_DEPT IT                     "ID: 1, Name: Alice, Salary: 50000, Role: Developer, Department: IT, Experience: 5 years"
//                                         "ID: 2, Name: Bob, Salary: 60000, Role: Manager, Department: IT, Experience: 5 years"


// INVALID					                "Invalid command."
// ============================================================================================

// Note: 
// -> Your implementation must use MongoDB for data persistence.
// -> The server should run on port 8080.
// -> The system should allow multiple clients to connect.


// EXAMPLE URL value=>   ws://10.11.xx.xx:8080

const WebSocket = require('ws');
const mongoose = require('mongoose');

mongoose.connect("mongodb://127.0.0.1:27017/employeesDB")
    .then(() => console.log("Connected to MongoDB"))
    .catch(err => console.error("MongoDB Connection Error:", err));

const employeeSchema = new mongoose.Schema({
    name: String,
    salary: Number,
    role: String,
    department: String,
    experience: Number
});
const Employee = mongoose.model("Employee", employeeSchema);

const server = new WebSocket.Server({ port: 8080 });

server.on('connection', (ws) => {
    console.log("Client connected!");
    ws.send("Connected");

    ws.on('message', async (message) => {
        console.log("Received:", message.toString());
        const parts = message.toString().trim().split(" ");

        if (parts[0] === "INSERT") {
            const name = parts[1];
            const salary = parseFloat(parts[2]);
            const role = parts[3];
            const department = parts[4];
            const experience = parseInt(parts[5]);

            const newEmployee = new Employee({ name, salary, role, department, experience });
            await newEmployee.save();
            ws.send("Employee inserted successfully.");

        } else if (parts[0] === "RETRIEVE") {
            const employees = await Employee.find();

            const response = employees.map(emp =>
                `ID: ${emp._id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`
            ).join("\n");
            ws.send(response);

        } else if (parts[0] === "RETRIEVE_BY_DEPT") {
            const department = parts[1];
            const employees = await Employee.find({ department });

            const response = employees.map(emp =>
                `ID: ${emp._id}, Name: ${emp.name}, Salary: ${emp.salary}, Role: ${emp.role}, Department: ${emp.department}, Experience: ${emp.experience} years`
            ).join("\n");
            
            ws.send(response);
        } else {
            ws.send("Invalid command.");
        }
    });

    ws.on('close', () => {
        console.log("Client disconnected.");
    });
});

console.log("WebSocket server is running on ws://localhost:8080");
