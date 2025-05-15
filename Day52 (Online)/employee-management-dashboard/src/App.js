import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { useState } from 'react';
import EmployeeForm from './components/EmployeeForm';
import EmployeeList from './components/EmployeeList';
import AdminPanel from './components/AdminPanel';
import HRPanel from './components/HRPanel';

function App() {
  const [employees, setEmployees] = useState([]);

  const addEmployee = (employeeData) => {
    employeeData.id = Date.now();
    employeeData.status = "Pending";
    setEmployees([...employees, employeeData]);
  };

  const updateEmployeeStatus = (id, newStatus) => {
    setEmployees(
      employees.map(emp => emp.id === id ? { ...emp, status: newStatus } : emp)
    );
  };

  return (
    <Router>
      <div>
        <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/register">Register</Link>
            </li>
            <li>
              <Link to="/employees">Employees</Link>
            </li>
            <li>
              <Link to="/admin">Admin</Link>
            </li>
            <li>
              <Link to="/hr">HR</Link>
            </li>
          </ul>
        </nav>

        <Routes>
          <Route path="/" element={<h1>Welcome to the Employee Management Dashboard</h1>} />
          <Route path="/register" element={<EmployeeForm addEmployee={addEmployee} />} />
          <Route path="/employees" element={<EmployeeList employees={employees} />} />
          <Route path="/admin" element={<AdminPanel employees={employees} updateEmployeeStatus={updateEmployeeStatus} />} />
          <Route path="/hr" element={<HRPanel employees={employees} />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;