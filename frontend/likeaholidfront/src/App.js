import { BrowserRouter as Router, Routes, Route, Navigate, useLocation } from 'react-router-dom';
import MyNavBar from './NavBar';
import HomePage from './pages/HomePage.js';
import PostPage from './pages/PostPage';
import "bootstrap/dist/css/bootstrap.min.css";
import LoginPage from './pages/LoginPage.js';
import UserPage from './pages/UserPage.js';
import './pages/HomePage.css';

// 1. Create an inner component to handle the conditional logic
function AppContent() {
    const location = useLocation();

    // Check if the current URL path is exactly '/login'
    const isLoginPage = location.pathname === '/login';

    return (
        <div className="App">
            {/* 2. Only render MyNavBar if we are NOT on the login page */}
            {!isLoginPage && <MyNavBar />}

            <Routes>
                <Route path="/" element={<Navigate to="/home" />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/home" element={<HomePage />}/>
                <Route path="/posts" element={<PostPage />}/>
                <Route path="/users" element={<UserPage />}/>
            </Routes>
        </div>
    );
}

function App() {
    return (
        <Router>
            <AppContent />
        </Router>
    );
}

export default App;