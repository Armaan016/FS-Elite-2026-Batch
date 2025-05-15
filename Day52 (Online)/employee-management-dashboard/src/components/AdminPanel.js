import EmployeeCard from './EmployeeCard';

const AdminPanel = ({ employees, updateEmployeeStatus }) => {
    const pendingEmployees = employees.filter(emp => emp.status === "Pending");

    if (!pendingEmployees || pendingEmployees.length === 0) {
        return <p>No pending employees for approval.</p>;
    }

    return (
        <div>
            {pendingEmployees.map(emp => (
                <div key={emp.id}>
                    <EmployeeCard employee={emp} />
                    <button onClick={() => updateEmployeeStatus(emp.id, "Accepted")}>Approve</button>
                </div>
            ))}
        </div>
    );
};

export default AdminPanel;