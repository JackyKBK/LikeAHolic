import { useEffect, useState } from "react";
import {API_URL, API_URL_USERS} from "./HomePage.js";
import { getData, postData, putData, deleteData } from "../services/api-services.js";
import { Button } from "react-bootstrap";
import UserForm from "./UserForm.js";
import UserList from "./UserList.js";

export default function UserPage() {
    const [users, setUsers] = useState([]);
    const [mode, setMode] = useState('list');
    const [selectedUser, setSelectedUser] = useState(null);
    const [error, setError] = useState(null);

    const fetchUsers = async () => {
        try {
            const data = await getData(API_URL_USERS);
            const transformedData = data.map(user => ({
                ...user,
                user_id: user.user_id,
            }));

            setUsers(transformedData);
        } catch (error) {
            console.error('Error fetching users:', error);
            setError(error.message);
        }
    };

    useEffect(() => {
        fetchUsers();
    }, []);

    function handleDelete(userId) {
        console.log("Deleting user with ID:", userId);
        if (!userId) {
            console.error("No userId provided.");
            return;
        }
        (async () => {
            try {
                const deleteUrl = `${API_URL_USERS}/${userId}`;
                console.log("DELETE request URL:", deleteUrl);
                await deleteData(deleteUrl);
                await fetchUsers();
                setMode("list");
            } catch (error) {
                console.error("Error deleting:", error);
                setError(error.message);
            }
        })();
    }
    const handleEditUsers = (user) => {
        setSelectedUser(user);
        setMode('edit');
    };

    const handleAddNew = () => {
        setSelectedUser(null);
        setMode('create');
    };

    const handleSaveUser = async (data, id) => {
        try {
            if (id) {
                const updatedUserData = { ...data, user_id: id };
                await putData(`http://localhost:8080/api/v1/user/${id}`, updatedUserData);
            } else {
                await postData(API_URL_USERS, data);
            }
            fetchUsers();
            setMode('list');
        } catch (error) {
            console.error('Error saving user:', error);
            alert('There was an error saving the user. Please try again later.');
        }
    };
    function handleLike(user_id) {
        console.log("Liked user with ID: " + user_id);
        const updatedUser = users.find(user => user.user_id === user_id);
        updatedUser.isLiked = !updatedUser.isLiked;
        const updatedUserData = { ...updatedUser };
        putData(`${API_URL_USERS}/${user_id}`, updatedUserData).then(() => {
            fetchUsers();
        }).catch(error => {
            console.error("Error updating like status:", error);
        });
    }
    if (error) return <p>Error: {error}</p>;
    if (!users) return <p>Loading...</p>;

    return (
        <div className="container mt-4">
            <Button variant="success" onClick={handleAddNew} className="mb-3">
                Add New User
            </Button>

            {mode === 'list' && (
                <UserList
                    users={users}
                    onLike={handleLike}
                    onDelete={handleDelete}
                    onEdit={handleEditUsers}
                    onCreate={handleAddNew}
                />
            )}

            {mode === 'create' && (
                <UserForm onSave={handleSaveUser} isAdding={true} />
            )}

            {mode === 'edit' && selectedUser && (
                <UserForm onSave={handleSaveUser} user={selectedUser} isAdding={false} />
            )}
        </div>
    );
}
