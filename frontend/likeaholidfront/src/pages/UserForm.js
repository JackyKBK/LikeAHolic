import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";

export default function UserForm(props) {
    const { user, onSave, isAdding } = props;

    const [formData, setFormData] = useState({
        userId: '',
        name: '',
        email: '',
        password: ''
    });

    useEffect(() => {
        if (user) {
            setFormData({
                userId: user.userId || '',
                name: user.name || '',
                email: user.email || '',
                password: user.password || ''
            });
        }
        console.log("User in useEffect:", user);
    }, [user]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Form data before saving:", formData);
        const userData = {
            userId: formData.userId,
            name: formData.name,
            email: formData.email,
            password: formData.password,
        };
        onSave(userData, user ? user.userId : null);
    };

    return (
        <div className="form-container">
            <h2>{user ? 'Edit User' : 'Add User'}</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label>User ID: </label>
                    <input
                        type="text"
                        name="userId"
                        value={formData.userId}
                        onChange={handleChange}
                        readOnly={!isAdding}
                    />
                </div>
                <div className="mb-3">
                    <label>Name: </label>
                    <input
                        type="text"
                        name="name"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label>Email: </label>
                    <input
                        type="text"
                        name="email"
                        value={formData.email}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label>Password: </label>
                    <input
                        type="text"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>

                <Button variant="primary" type="submit">
                    {user ? 'Confirm Changes' : 'Save'}
                </Button>
            </form>
        </div>
    );
}
