import React, { useEffect, useState } from 'react';
import PostCard from './PostCard';
import './PostList.css';

const PostList = ({ posts, onLike, onEdit, onDelete }) => {
    const [users, setUsers] = useState([]);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        fetch('http://localhost:8080/api/v1/users')
            .then((res) => res.json())
            .then((usersData) => {
                setUsers(usersData);
                setIsLoading(false);
            });
    }, []);

    if (isLoading) {
        return <p>Loading...</p>;
    }

    const normalizedUsers = users.map((user) => ({
        ...user,
        user_id: user.user_id || user.user_id,
    }));

    return (
        <div className="post-list">
            {posts.map((post) => {
                const user = normalizedUsers.find((user) => user.userId === post.user_id);
                return (
                    <div className="post-item" key={`${post.postId}-${post.user_id}`}>
                        <table className="post-table">
                            <tbody>
                            <tr>
                                <td className="user-info">
                                    {user ? (
                                        <div>
                                            <p>{user.user_id}</p>
                                            <p>{user.name}</p>
                                        </div>
                                    ) : (
                                        <p>User not found</p>
                                    )}
                                </td>
                            </tr>
                            <tr>
                                <td className="post-card">
                                    <PostCard
                                        post={post}
                                        onLike={onLike}
                                        onEdit={onEdit}
                                        onDelete={onDelete}
                                    />
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                );
            })}
        </div>
    );
};

export default PostList;