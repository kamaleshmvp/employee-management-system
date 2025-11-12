import axios from "axios";

const REST_API_BASE_URL = 'http://localhost:8081/api/employees';
const username = sessionStorage.getItem('username');
const password = sessionStorage.getItem('password');
const auth = {
    username,
    password
};


export const listEmployees = () => axios.get(REST_API_BASE_URL,{auth});

export const createEmployee = (employee) => axios.post(REST_API_BASE_URL, employee,{auth});

export const getEmployee = (employeeId) => axios.get(REST_API_BASE_URL + '/' + employeeId,{auth});

export const updateEmployee = (employeeId, employee) => axios.put(REST_API_BASE_URL + '/' + employeeId, employee,{auth});

export const deleteEmployee = (employeeId) => axios.delete(REST_API_BASE_URL + '/' + employeeId,{auth});