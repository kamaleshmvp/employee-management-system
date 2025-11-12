import { BrowserRouter, Routes, Route } from 'react-router-dom';
import ProtectedRoute from './components/ProtectedRoute';
import LoginComponent from './components/LoginComponent';
import SignupComponent from './components/SignupComponent'; // Import the SignupComponent
import ListEmployeeComponent from './components/ListEmployeeComponent';
import EmployeeComponent from './components/EmployeeComponent';
import ListDepartmentComponent from './components/ListDepartmentComponent';
import DepartmentComponent from './components/DepartmentComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import './App.css';

function App() {
    return (
        <BrowserRouter>
            <div className="d-flex flex-column min-vh-100">
                <HeaderComponent />
                <div className="flex-grow-1">
                    <Routes>
                        <Route path='/' element={<LoginComponent />} />
                        <Route path='/login' element={<LoginComponent />} />
                        <Route path='/signup' element={<SignupComponent />} /> 
                        <Route
                            path='/employees'
                            element={
                                <ProtectedRoute>
                                    <ListEmployeeComponent />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path='/add-employee'
                            element={
                                <ProtectedRoute>
                                    <EmployeeComponent />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path='/edit-employee/:id'
                            element={
                                <ProtectedRoute>
                                    <EmployeeComponent />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path='/departments'
                            element={
                                <ProtectedRoute>
                                    <ListDepartmentComponent />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path='/add-department'
                            element={
                                <ProtectedRoute>
                                    <DepartmentComponent />
                                </ProtectedRoute>
                            }
                        />
                        <Route
                            path='/edit-department/:id'
                            element={
                                <ProtectedRoute>
                                    <DepartmentComponent />
                                </ProtectedRoute>
                            }
                        />
                    </Routes>
                </div>
                <FooterComponent />
            </div>
        </BrowserRouter>
    );
}

export default App;