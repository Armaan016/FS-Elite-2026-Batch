import React from 'react';

const EmployeeCard = ({ employee }) => {
    const cardStyle = {
        backgroundColor: employee.status === "Accepted" ? "lightgreen" : "lightcoral",
        border: "1px solid #000",
        padding: "10px",
        margin: "10px 0"
    };

    return (
        <div style={cardStyle}>
            <h3>{employee.fullName}</h3>
            <p>Email: {employee.email}</p>
            <p>Mobile: {employee.mobile}</p>
            <p>Status: {employee.status}</p>
        </div>
    );
};

export default EmployeeCard;