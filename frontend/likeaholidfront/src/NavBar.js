import { Nav, Navbar } from 'react-bootstrap';
import Container from 'react-bootstrap/Container';
import { Link } from 'react-router-dom';
import './pages/HomePage.css';
import {useEffect, useState} from "react";
export default function MyNavBar() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);

    useEffect(() => {
        const userToken = localStorage.getItem('userToken');
        if (userToken) {
            setIsAuthenticated(true);
        }
    }, []);
    const links = [
        {
            to: "posts",
            title: "Posts",
        },
        {
            to: "users",
            title: "Users"
        }
    ];
    return (
        <Navbar bg="primary" data-bs-theme="darknpm uninstall react-router-bootstrap
">
            <Container>
                <Link to="/posts" style={{ cursor: 'pointer' }}>
                    <Navbar.Brand className="fs-2">
                        <img src="/images/facebook-logo.png" alt="Logo" style={{ height: '40px', width: 'auto' }}/>
                    </Navbar.Brand>
                </Link>
                <Nav className="me-auto fs-4">
                    {
                        links.map((link) => (
                            <Nav.Item key={link.to}>
                                <Link to={`/${link.to}`} className="nav-link">{link.title}</Link>
                            </Nav.Item>
                        ))
                    }
                </Nav>
            </Container>
        </Navbar>
    );
}