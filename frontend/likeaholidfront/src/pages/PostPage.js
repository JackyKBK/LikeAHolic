import { useEffect, useState } from "react";
import { API_URL } from "./HomePage.js";
import { getData, postData, putData, deleteData } from "../services/api-services.js";
import { Button } from "react-bootstrap";
import PostForm from "./PostForm.js";
import PostList from "./PostList.js";
import { useNavigate } from "react-router-dom";

export default function PostPage() {
    const [posts, setPosts] = useState([]);
    const [mode, setMode] = useState('list');
    const [selectedPost, setSelectedPost] = useState(null);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const fetchPosts = async () => {
        try {
            const data = await getData(API_URL);
            console.log('Fetched posts:', data);
            const sortedData = data.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
            setPosts(sortedData);
        } catch (error) {
            console.error('Error fetching posts:', error);
            setError(error.message);
        }
    };

    useEffect(() => {
        fetchPosts();
    }, []);

    function handleDelete(postId) {
        console.log("Deleting post with ID:", postId);
        if (!postId) {
            console.error("No postId provided.");
            return;
        }
        (async () => {
            try {
                const deleteUrl = `${API_URL}/${postId}`;
                console.log("DELETE request URL:", deleteUrl);
                await deleteData(deleteUrl);
                await fetchPosts();
                setMode("list");
            } catch (error) {
                console.error("Error deleting:", error);
                setError(error.message);
            }
        })();
    }

    function handleEditPost(post) {
        console.log("Selected post for editing:", post);
        setSelectedPost(post);
        setMode('edit');
    }

    function handleAddNew() {
        setSelectedPost(null);
        setMode('create');
    }

    const handleSavePost = async (data, id) => {
        console.log("Saving post data:", data);
        if (!data.postId || data.postId.trim() === '') {
            alert('Post ID is required!');
            return;
        }
        try {
            if (id) {
                const updatedPostData = { ...data, postId: id };
                const API_URL_WITH_ID = `http://localhost:8080/api/v1/posts/${id}`;
                await putData(API_URL_WITH_ID, updatedPostData);
                setPosts((prevPosts) =>
                    prevPosts.map((post) =>
                        post.postId === id ? { ...post, ...data } : post
                    )
                );
                navigate('/posts');
            } else {
                await postData(API_URL, data);

                await fetchPosts();
            }
            setMode('list');
        } catch (error) {
            console.error('Error saving post:', error);
            alert(error.message || 'There was an error saving the post.');
        }
    };

    if (error) return <p>Error: {error}</p>;
    if (!posts) return <p>Loading...</p>;
    const handleLike = async (post) => {
        const postId = post.id || post.postId;
        const currentUserId = localStorage.getItem('userId') || localStorage.getItem('user_id') || '1';

        const payload = {
            userId: currentUserId,
            postId: postId
        };

        try {
            let response = await fetch('http://localhost:8080/api/v1/likes', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            let actionType = 'liked';

            if (!response.ok) {
                const errorText = await response.text();
                if (errorText.includes("already liked")) {
                    // Already liked, so unlike it via DELETE
                    response = await fetch('http://localhost:8080/api/v1/likes', {
                        method: 'DELETE',
                        headers: { 'Content-Type': 'application/json' },
                        body: JSON.stringify(payload)
                    });
                    actionType = 'unliked';
                } else {
                    throw new Error(errorText || 'Failed to process like');
                }
            }

            if (!response.ok) {
                throw new Error('Failed to process like/unlike');
            }

            // Instantly update the state so the color and counter change right away
            setPosts((prevPosts) =>
                prevPosts.map((p) => {
                    if (p.postId === postId || p.id === postId) {
                        const currentCount = p.likesCount || 0;
                        return {
                            ...p,
                            isLiked: actionType === 'liked',
                            likesCount: actionType === 'liked' ? currentCount + 1 : Math.max(0, currentCount - 1)
                        };
                    }
                    return p;
                })
            );

        } catch (error) {
            console.error('Error handling like:', error);
            alert('Could not process like: ' + error.message);
        }
    };
    const handleSignOut = () => {
        localStorage.removeItem('user_id');
        localStorage.removeItem('password');
        navigate('/login');
    };

    return (
        <div className="container mt-4">
            <Button variant="success" onClick={handleAddNew} className="mb-3">
                Add New Post
            </Button>

            {mode === 'list' && (
                <div className="list-group">
                    <PostList
                        posts={posts}
                        onLike={handleLike}
                        onDelete={handleDelete}
                        onEdit={handleEditPost}
                        onCreate={handleAddNew}
                    />
                </div>
            )}

            {mode === 'create' && (
                <PostForm onSave={handleSavePost} isAdding={true} />
            )}

            {mode === 'edit' && selectedPost && (
                <PostForm onSave={handleSavePost} post={selectedPost} isAdding={false} />
            )}

            <Button variant="danger" onClick={handleSignOut} className="mt-3">
                Sign Out
            </Button>
        </div>
    );
}