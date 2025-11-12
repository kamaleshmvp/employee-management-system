import { useEffect, useState } from 'react';
import { deleteEmployee, listEmployees } from '../sevices/EmployeeService';
import { getAllDepartments } from '../sevices/DepartmentService'; 
import { useNavigate } from 'react-router-dom';

const ListEmployeeComponent = () => {
    const [employees, setEmployees] = useState([]);
    const [departments, setDepartments] = useState([]); 
    const navigator = useNavigate();
    const role = sessionStorage.getItem('role');

    const fetchAll = async()=>{ 
        await getAllDepartments().then(response => {
             setDepartments(response.data);
         }).catch(error => {
             console.error('Error fetching departments:', error);
         });
      }
      
    useEffect(() => {
         getAllEmployees();
         fetchAll();
    }, []);

    function getAllEmployees() {
        listEmployees().then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        });
    }

    function addNewEmployee() {
        navigator('/add-employee');
    }

    function updateEmployee(id) {
        navigator(`/edit-employee/${id}`);
    }

    function removeEmployee(id) {
        console.log(id);

        deleteEmployee(id).then(() => {
            getAllEmployees();
        }).catch(error => {
            console.error(error);
        });
    }

    // Function to get department name by departmentId
    const getDepartmentName = (departmentId) => {
        const department = departments.find(dept => dept.id === departmentId);
        return department ? department.departmentName : 'No Department';
    };

    return (
        <div className='container mt-4 mb-4'>
            <h2 className='text-center text-light'>List of Employees</h2>
            {role === 'ROLE_ADMIN' && (
                <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
            )}
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Employee Id</th>
                        <th>Employee First Name</th>
                        <th>Employee Last Name</th>
                        <th>Employee Email Id</th>
                        <th>Department</th>
                        {role === 'ROLE_ADMIN' && <th>Actions</th>}
                    </tr>
                </thead>
                <tbody>
                    {employees.map(employee => (
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.email}</td>
                            <td>{getDepartmentName(employee.departmentId)}</td>
                            {role === 'ROLE_ADMIN' && (
                                <td>
                                    <button className='btn btn-info' onClick={() => updateEmployee(employee.id)}>Update</button>
                                    <button className='btn btn-danger' onClick={() => removeEmployee(employee.id)}
                                        style={{ marginLeft: '30px' }}
                                    >Delete</button>
                                </td>
                            )}
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListEmployeeComponent;