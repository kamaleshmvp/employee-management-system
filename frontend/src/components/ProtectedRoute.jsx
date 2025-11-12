import { Navigate } from 'react-router-dom';
import PropTypes from 'prop-types'; // Import PropTypes

const ProtectedRoute = ({ children }) => {
    // Check if the user is authenticated
    const isAuthenticated = sessionStorage.getItem('isAuthenticated');

    // If authenticated, render the children (protected component)
    // If not authenticated, redirect to the login page
    return isAuthenticated ? children : <Navigate to="/login" />;
};

// Add prop validation for the `children` prop
ProtectedRoute.propTypes = {
    children: PropTypes.node.isRequired, // `children` must be a valid React node and is required
};

export default ProtectedRoute;