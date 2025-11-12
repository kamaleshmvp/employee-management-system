import  { useEffect, useState } from 'react'
import { deleteDepartment, getAllDepartments } from '../sevices/DepartmentService';
import { Link, useNavigate } from 'react-router-dom';


const ListDepartmentComponent = () => {

    const [departments, setDepartments] = useState([]);

    const navigator = useNavigate();
    const role = sessionStorage.getItem('role');

    useEffect( () => {
       listOfDepartments();
    }, [])

    function listOfDepartments(){
        getAllDepartments().then((response) => {
            console.log(response.data);
            setDepartments(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function updateDepartment(id){
        navigator(`/edit-department/${id}`)
    }


    function removeDepartment(id){
        deleteDepartment(id).then((response) => {
            console.log(response.data);
            listOfDepartments();
           
        }).catch(error => {
            console.error(error);
        })
    }
  return (
    <div className='container'>
        <h2 className='text-center text-light mt-4' >List of Departments</h2>
        {/* Conditionally render the "Add Department" button */}
        {role === 'ROLE_ADMIN' && (
                <Link to='/add-department' className='btn btn-primary mb-2'>Add Department</Link>
            )
        }
        <table className='table table-striped table-bordered mb-4'>
            <thead>
                <tr>
                    <th>Department Id</th>
                    <th>Department Name</th>
                    <th>Department Description</th>
                    {role === 'ROLE_ADMIN'  &&<th>Actions</th>}
                </tr>
            </thead>
            <tbody>
                {
                    departments.map( department => 
                            <tr key={department.id}>
                                <td> {department.id} </td>
                                <td> {department.departmentName} </td>
                                <td> {department.departmentDescription} </td>
                                {role === 'ROLE_ADMIN' && (
                                <td>
                                    <button onClick={() => updateDepartment(department.id)} className='btn btn-info'>Update</button>
                                    <button onClick={() => removeDepartment(department.id)} className='btn btn-danger'
                                        style={{marginLeft: "30px"}}
                                    >Delete</button>
                                </td>
                                )}
                            </tr>
                        )
                }
            </tbody>
        </table>

    </div>
  )
}

export default ListDepartmentComponent