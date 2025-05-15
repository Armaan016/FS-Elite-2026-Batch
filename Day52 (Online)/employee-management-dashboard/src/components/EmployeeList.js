import EmployeeCard from './EmployeeCard';

const EmployeeList = ({ employees }) => {
  if (!employees || employees.length === 0) {
    return <p>No employees registered yet.</p>;
  }
  return (
    <div>
      {employees.map(emp => (
        <EmployeeCard key={emp.id} employee={emp} />
      ))}
    </div>
  );
};

export default EmployeeList;