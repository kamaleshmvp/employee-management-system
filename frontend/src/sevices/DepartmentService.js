import axios from 'axios';

const DEPARTMENT_REST_API_BASE_URL = 'http://localhost:8081/api/departments';

// Define your credentials
const username = sessionStorage.getItem('username');
const password = sessionStorage.getItem('password');

// Create an auth object
const auth = {
    username,
    password
};

// Add auth to all requests
export const getAllDepartments = () => axios.get(DEPARTMENT_REST_API_BASE_URL, { auth });

export const createDepartment = (department) => axios.post(DEPARTMENT_REST_API_BASE_URL, department, { auth });

export const getDepartmentById = (departmentId) => axios.get(`${DEPARTMENT_REST_API_BASE_URL}/${departmentId}`, { auth });

export const updateDepartment = (departmentId, department) => axios.put(`${DEPARTMENT_REST_API_BASE_URL}/${departmentId}`, department, { auth });

export const deleteDepartment = (departmentId) => axios.delete(`${DEPARTMENT_REST_API_BASE_URL}/${departmentId}`, { auth });