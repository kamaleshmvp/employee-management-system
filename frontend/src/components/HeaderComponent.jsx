import { NavLink, useNavigate } from 'react-router-dom';

const HeaderComponent = () => {
    const navigate = useNavigate();
    const isAuthenticated = sessionStorage.getItem('isAuthenticated');

    const handleLogout = () => {
        
        sessionStorage.removeItem('isAuthenticated');
        sessionStorage.removeItem('username');
        sessionStorage.removeItem('password');

        
        navigate('/login');
    };

    return (
        <div className='p-3 container-fluid header-nav'>
            <header>
                <nav className='navbar navbar-expand-lg'>
                    <a className="navbar-brand" href="/employees">Employee Management System</a>

                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <NavLink className='nav-link' to='/employees'>Employees</NavLink>
                            </li>

                            <li className="nav-item">
                                <NavLink className='nav-link' to='/departments'>Departments</NavLink>
                            </li>
                            
                        </ul>

                        {/* Conditionally render the logout button */}
                        {isAuthenticated && (
                            <ul className="navbar-nav ms-auto">
                                <li className="nav-item">
                                    <button className="btn btn-primary" onClick={handleLogout}>
                                        Logout
                                    </button>
                                </li>
                            </ul>
                        )}
                    </div>
                </nav>
            </header>
        </div>
    );
};

export default HeaderComponent;