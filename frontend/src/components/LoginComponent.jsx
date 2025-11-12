import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import { FaEye, FaEyeSlash } from 'react-icons/fa'; // Import eye icons
import './LoginComponent.css';

const LoginComponent = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const [showPassword, setShowPassword] = useState(false); 
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError('');

        try {
            // Call the backend API to authenticate the user
            const response = await axios.post(
                'http://localhost:8081/api/auth/login',
                { userName: username, password: password },
                {
                    auth: {
                        username: username,
                        password: password,
                    },
                }
            );

            if (response.status === 200) {
                // Save authentication state
                sessionStorage.setItem('isAuthenticated', 'true');
                sessionStorage.setItem('username', username);
                sessionStorage.setItem('password', password);
                sessionStorage.setItem('role', response.data.role);
                // Redirect to the employees page
                navigate('/employees');
            }
        } catch (error) {
            if (error.response) {
                setError(error.response.data.message || 'Invalid username or password');
            } else if (error.request) {
                setError('No response from the server. Please try again later.');
            } else {
                setError('An error occurred. Please try again.');
            }
            console.error(error);
        } finally {
            setLoading(false);
        }
    };

    // Toggle password visibility
    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-body">
                            <h2 className="card-title text-center">Login</h2>
                            {error && <div className="alert alert-danger">{error}</div>}
                            <form onSubmit={handleLogin}>
                                <div className="mb-3">
                                    <label htmlFor="username" className="form-label">Username</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="username"
                                        value={username}
                                        onChange={(e) => setUsername(e.target.value)}
                                        required
                                    />
                                </div>
                                <div className="mb-3 password-input-container">
                                    <label htmlFor="password" className="form-label">Password</label>
                                    
                                    <input
                                        type={showPassword ? 'text' : 'password'} // Toggle input type
                                        className="form-control"
                                        id="password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        required
                                    />
                                    <span
                                        className="password-toggle-icon"
                                        onClick={togglePasswordVisibility}
                                    >
                                        {showPassword ? <FaEyeSlash /> : <FaEye />} {/* Toggle eye icon */}
                                    </span>
                                </div>
                                <button type="submit" className="btn btn-primary w-100" disabled={loading}>
                                    {loading ? (
                                        <span className="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                                    ) : (
                                        'Login'
                                    )}
                                </button>
                                <p className="text-center mt-3">
    Dont have an account? <a href="/signup">Sign Up</a>
</p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginComponent;