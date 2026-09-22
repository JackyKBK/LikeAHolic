import React, { useState } from 'react';
import './LoginPage.css';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const [userId, setUserId] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (userId && password) {
            try {
                console.log('Sending request with:', { userId, password });
                const response = await fetch('http://localhost:8080/api/v1/users/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        userId: userId,
                        password: password
                    }),
                });

                if (response.ok) {
                    const data = await response.json();

                    console.log('Backend response:', data);

                    if (data.message === "Login successful") {
                        localStorage.setItem('userId', userId);
                        localStorage.setItem('password', password);
                        navigate('/home');
                    } else {
                        setError(data.message || 'Invalid credentials');
                    }
                } else {
                    const errorData = await response.json();
                    console.error('Error response from backend:', errorData);
                    setError(errorData.message || 'Failed to log in. Please try again.');
                }
            } catch (error) {
                console.error('Login error:', error);
                setError('An error occurred. Please try again later.');
            }
        } else {
            setError('Please fill in both fields');
        }
    };

    return (
        <div className="login-page">
            <div className="login-container">
                <div className="login-form">
                    <h2>Login</h2>
                    {error && <div className="error-message">{error}</div>}
                    <form onSubmit={handleSubmit}>
                        <div>
                            <label htmlFor="userId">User ID</label>
                            <input
                                type="text"
                                id="userId"
                                value={userId}
                                onChange={(e) => setUserId(e.target.value)}
                                required
                            />
                        </div>
                        <div>
                            <label htmlFor="password">Password</label>
                            <input
                                type="password"
                                id="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                                required
                            />
                        </div>
                        <button type="submit" disabled={!userId || !password}>Login</button>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default LoginPage;
