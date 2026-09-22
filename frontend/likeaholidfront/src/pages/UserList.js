import {Button, Table} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import './PostList.css';
export default function UserList({ users, onEdit, onDelete }) {
    return (
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>User ID</th>
                    <th>User Name</th>
                    <th>Email</th>
                    <th>Password</th>
                </tr>
                </thead>
                <tbody>
                {users.map((user) => (
                    <tr key={user.userId}>
                        <td>{user.userId}</td>
                        <td>{user.name}</td>
                        <td>{user.email}</td>
                        <td>{user.password}</td>

                        <td>
                            <Button variant="info" onClick={() => onEdit(user)}>Edit</Button>
                            <Button variant="danger" onClick={() => onDelete(user.userId)}>Delete</Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>

    );
}