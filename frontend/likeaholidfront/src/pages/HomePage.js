import './HomePage.css';
import React, { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";

export const API_URL = 'http://localhost:8080/api/v1/posts';
export const API_URL_USERS = 'http://localhost:8080/api/v1/users';

const HomePage = () => {
    const [userData, setUserData] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const storedUserId = localStorage.getItem('userId');
        const storedPassword = localStorage.getItem('password');

        if (storedUserId && storedPassword) {
            setUserData({ userId: storedUserId });
            // Redirect after 5 seconds if logged in
            const timer = setTimeout(() => {
                navigate('/posts');
            }, 2000);
            return () => clearTimeout(timer); // Cleanup timer
        } else {
            navigate('/login');
        }
    }, [navigate]);

    return (
        <div className="homepage">
            <div className="center">
                {userData ? (
                    <div>
                        <h1>Welcome back, User {userData.userId}!</h1>
                        <p>Redirecting to posts page in 2 seconds...</p>
                    </div>
                ) : (
                    <p>Loading...</p>
                )}
            </div>
        </div>
    );
};

export default HomePage;
