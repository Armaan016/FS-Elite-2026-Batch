import EmployeeCard from './EmployeeCard';

const HRPanel = ({ employees }) => {
  const acceptedEmployees = employees.filter(emp => emp.status === "Accepted");

  if (!acceptedEmployees || acceptedEmployees.length === 0) {
    return <p>No accepted employees for HR.</p>;
  }

  return (
    <div>
      {acceptedEmployees.map(emp => (
        <EmployeeCard key={emp.id} employee={emp} />
      ))}
    </div>
  );
};

export default HRPanel;