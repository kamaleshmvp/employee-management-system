import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const SignupComponent = () => {
    const [userName, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('ROLE_USER'); // Default role is ROLE_USER
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleSignup = async (e) => {
        e.preventDefault();
        setLoading(true);
        setError('');

        const user = {
            userName,
            password,
            role,
        };

        try {
            // Call the backend API to create a new user
            const response = await axios.post('http://localhost:8081/users', user);
            console.log('User created:', response.data);

            // Redirect to the login page after successful signup
            navigate('/login');
        } catch (error) {
            if (error.response) {
                setError(error.response.data.message || 'Failed to create user');
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

    return (
        <div className="container mt-5">
            <div className="row justify-content-center">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-body">
                            <h2 className="card-title text-center">Sign Up</h2>
                            {error && <div className="alert alert-danger">{error}</div>}
                            <form onSubmit={handleSignup}>
                                <div className="mb-3">
                                    <label htmlFor="username" className="form-label">Username</label>
                                    <input
                                        type="text"
                                        className="form-control"
                                        id="username"
                                        value={userName}
                                        onChange={(e) => setUsername(e.target.value)}
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="password" className="form-label">Password</label>
                                    <input
                                        type="password"
                                        className="form-control"
                                        id="password"
                                        value={password}
                                        onChange={(e) => setPassword(e.target.value)}
                                        required
                                    />
                                </div>
                                <div className="mb-3">
                                    <label htmlFor="role" className="form-label">Role</label>
                                    <select
                                        className="form-control"
                                        id="role"
                                        value={role}
                                        onChange={(e) => setRole(e.target.value)}
                                        required
                                    >
                                        <option value="ROLE_USER">User</option>
                                        <option value="ROLE_ADMIN">Admin</option>
                                    </select>
                                </div>
                                <button type="submit" className="btn btn-primary w-100" disabled={loading}>
                                    {loading ? (
                                        <span className="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
                                    ) : (
                                        'Sign Up'
                                    )}
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default SignupComponent;